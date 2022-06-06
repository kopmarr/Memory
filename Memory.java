import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.Timer;

public class Memory implements MouseListener, ActionListener, Runnable {

    ImageIcon scaredGrey = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0.png").getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
    ImageIcon happyYellow = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (1).png").getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
    ImageIcon sadBlue = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (2).png").getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
    ImageIcon sickGreen = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (3).png").getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
    ImageIcon happyOrange = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (4).png").getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
    ImageIcon angryRed = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (5).png").getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
    ImageIcon tiredWhite = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (6).png").getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
    ImageIcon embarrassedPurple = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (7).png").getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
    ImageIcon cardBack = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (9).png").getImage().getScaledInstance(220,220, Image.SCALE_DEFAULT));
    ImageIcon restartIcon = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (10).png").getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));

    int[][] cardNum = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};
    JButton[][] cards = new JButton[4][4];
    JFrame frame = new JFrame("MEMORY MATCH");
    Container center = new Container();
    Container west = new Container();
        JButton clear = new JButton();

    public static void main(String[] args) {new Memory();}
    Color darkOrange = new Color(250, 192, 128);

    public Memory(){
        frame.setSize(1050,1000);

        west.setLayout(new BorderLayout());
        center.setLayout(new GridLayout(4,4));
        for (int row = 0; row < cardNum.length; row++) {
            for (int column = 0; column < cardNum[0].length; column++) {
                cards[column][row] = new JButton();
                center.add(cards[column][row]);
                cards[column][row].addActionListener(this);
                cards[column][row].setIcon(cardBack);
                cards[column][row].setBackground(darkOrange);
            }
        }
        frame.add(center, BorderLayout.CENTER);
        frame.add(west, BorderLayout.WEST);
        clear.addActionListener(this);
        west.add(clear);
        clear.setIcon(restartIcon);
        clear.setBackground(darkOrange);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //card is each assigned number 0-15 each number corresponds with a card image

    public void randomizedCard(){

        for (int x = 0; x < cardNum.length; x++) {
            for (int y = 0; y < cardNum[0].length; y++) {
                int x1 = (int)(Math.random() * cardNum.length);
                int y1 = (int)(Math.random() * cardNum[0].length);

                int temp = cardNum[x][y];
                cardNum[x][y] = cardNum[x1][y1];
                cardNum[x1][y1] = temp;
                //int randNum = (int) (Math.random() * 8);
                //cardNum[x][y] = randNum;

            }
        }
        //System.out.println(cardNum[0][0] + "," + cardNum[0][1] + "," + cardNum[0][2] + "," + cardNum[0][3]);
        //System.out.println(cardNum[1][0] + "," + cardNum[1][1] + "," + cardNum[1][2] + "," + cardNum[1][3]);
        //System.out.println(cardNum[2][0] + "," + cardNum[2][1] + "," + cardNum[2][2] + "," + cardNum[2][3]);
        //System.out.println(cardNum[3][0] + "," + cardNum[3][1] + "," + cardNum[3][2] + "," + cardNum[3][3]);
        //System.out.println("");



    }

    //rules
    //when clicked the card is disabled and image is shown
        //if the 2 cards flipped are the same the cards stay disabled, and there is a got a match popup
        //if the 2 cards flipped aren't the same waits 2 seconds then the two selected cards get flipped over
        //and images are set to card backs
        //when all cards are flipped a popup tell you that you won and cards are all reshuffled and flipped

            //restart button to reshuffle the cards if you want to start a new game early

    int start = 0;
    int chosen = 0;
    int selected = 1;
    int numCardsSelected = 0;
    int currentIndex;
    int oddClickIndex;
    Timer myTimer;
    int delay = 1000;
    int cardNumX;
    int cardNumY;
    int[][] cardNum1 = new int[4][4];



    @Override
    public void actionPerformed(ActionEvent e) {
        myTimer = new Timer(5, this);

        if(start==0){

            randomizedCard();
            start++;
        }
        if(e.getSource().equals(clear)){
            randomizedCard();
            for (int x = 0; x < cards.length; x++) {
                for (int y = 0; y < cards[0].length; y++) {
                    selected = 1;
                    numCardsSelected = 0;
                    chosen = 0;
                    cards[x][y].setEnabled(true);
                    cards[x][y].setIcon(cardBack);
                    cards[x][y].setBackground(darkOrange);
                }
            }
        }
        else{
            for (int x = 0; x < cards.length; x++) {
                for (int y = 0; y < cards[0].length; y++) {
                    if(e.getSource().equals(cards[x][y])) {
                        numCardsSelected++;
                        if (cardNum[x][y] == 0) { //101
                            cards[x][y].setIcon(tiredWhite);
                            cards[x][y].setBackground(Color.WHITE);
                            chosen += 100;
                        }
                        if (cardNum[x][y] == 1) {
                            cards[x][y].setIcon(tiredWhite);
                            cards[x][y].setBackground(Color.WHITE);
                            chosen += 1;
                        }
                        if (cardNum[x][y] == 2) { //203
                            cards[x][y].setIcon(happyOrange);
                            cards[x][y].setBackground(Color.ORANGE);
                            chosen += 200;
                        }
                        if (cardNum[x][y] == 3) {
                            cards[x][y].setIcon(happyOrange);
                            cards[x][y].setBackground(Color.ORANGE);
                            chosen += 3;
                        }
                        if (cardNum[x][y] == 4) { //405
                            cards[x][y].setIcon(happyYellow);
                            cards[x][y].setBackground(Color.YELLOW);
                            chosen += 400;
                        }
                        if (cardNum[x][y] == 5) {
                            cards[x][y].setIcon(happyYellow);
                            cards[x][y].setBackground(Color.YELLOW);
                            chosen += 5;
                        }
                        if (cardNum[x][y] == 6) { //607
                            cards[x][y].setIcon(sadBlue);
                            cards[x][y].setBackground(Color.BLUE);
                            chosen += 600;
                        }
                        if (cardNum[x][y] == 7) {
                            cards[x][y].setIcon(sadBlue);
                            cards[x][y].setBackground(Color.BLUE);
                            chosen += 7;
                        }
                        if (cardNum[x][y] == 8) { //809
                            cards[x][y].setIcon(sickGreen);
                            cards[x][y].setBackground(Color.GREEN);
                            chosen += 800;
                        }
                        if (cardNum[x][y] == 9) {
                            cards[x][y].setIcon(sickGreen);
                            cards[x][y].setBackground(Color.GREEN);
                            chosen += 9;
                        }
                        if (cardNum[x][y] == 10) { //1011
                            cards[x][y].setIcon(angryRed);
                            cards[x][y].setBackground(Color.RED);
                            chosen += 1000;
                        }
                        if (cardNum[x][y] == 11) { //21
                            cards[x][y].setIcon(angryRed);
                            cards[x][y].setBackground(Color.RED);
                            chosen += 11;
                        }
                        if (cardNum[x][y] == 12) { //1213
                            cards[x][y].setIcon(scaredGrey);
                            cards[x][y].setBackground(Color.GRAY);
                            chosen += 1200;
                        }
                        if (cardNum[x][y] == 13) {
                            cards[x][y].setIcon(scaredGrey);
                            cards[x][y].setBackground(Color.GRAY);
                            chosen += 13;
                        }
                        if (cardNum[x][y] == 14) { //1415
                            Color purple = new Color(138, 43, 226);
                            cards[x][y].setIcon(embarrassedPurple);
                            cards[x][y].setBackground(purple);
                            chosen += 1400;
                        }
                        if(cardNum[x][y] == 15){
                            Color purple = new Color(138, 43, 226);
                            cards[x][y].setIcon(embarrassedPurple);
                            cards[x][y].setBackground(purple);
                            chosen += 15;
                        }
                        if(selected == 1){
                            //cardNum1 = cardNum;
                            //for (int i = 0; i < cardNum.length; i++) {
                            //    for (int j = 0; j < cardNum[0].length; j++) {
                            //        if(cardNum[i][j] == cardNum1[i][j]){
                            //            i = cardNumX;
                            //            j = cardNumY;
                            //        }
                            //    }
                            //}

                            System.out.println("i=" + cardNumX + " j=" + cardNumY);
                        }

                        cards[x][y].setEnabled(false);
                        if (selected == 2) {
                            cards[x][y].setEnabled(false);

                            if(chosen == 101 || chosen == 203 || chosen ==405 || chosen == 607 || chosen == 809 || chosen == 1011 || chosen == 1213 || chosen == 1415){
                                JOptionPane.showMessageDialog(frame,
                                        "You got a match",
                                        "MATCH MESSAGE",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                            else {
                                JOptionPane.showMessageDialog(frame,
                                        "No match",
                                        "NO MATCH MESSAGE",
                                        JOptionPane.INFORMATION_MESSAGE);

                                    numCardsSelected--;
                                    cards[x][y].setIcon(cardBack);
                                    cards[x][y].setBackground(darkOrange);
                                    cards[x][y].setEnabled(true);

                                    cards[cardNumX][cardNumY].setIcon(cardBack);
                                    cards[cardNumX][cardNumY].setBackground(darkOrange);
                                    cards[cardNumX][cardNumY].setEnabled(true);
                            }
                            selected = 0;
                            chosen = 0;
                        }
                        if(numCardsSelected == 16){
                            JOptionPane.showMessageDialog(frame,
                                    "You win",
                                    "WIN MESSAGE",
                                    JOptionPane.INFORMATION_MESSAGE);
                            for (int i = 0; i < cards.length; i++) {
                                for (int j = 0; j < cards[0].length; j++) {
                                    numCardsSelected = 0;
                                    cards[i][j].setEnabled(true);
                                    cards[i][j].setIcon(cardBack);
                                    cards[i][j].setBackground(darkOrange);
                                    randomizedCard();
                                    selected = 0;
                                    chosen = 0;
                                    //matches = 0;
                                }
                            }

                        }

                        selected++;
                    }
                }
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void run() {

    }
}
