import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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

    private static final int CARD = 16;
    int[][] cardNum = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};
    JButton[][] cards = new JButton[4][4];
    JFrame frame = new JFrame("MEMORY MATCH");
    Container center = new Container();
    Container west = new Container();
        JButton clear = new JButton();
    int selected = 2;

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

    //card is each assigned number 1-8 each number corresponds with a card image
    //there can only be 2 of each number, if both equal the same number it's a match


    //Random randNum = new Random();
    public void randomizedCard2(){

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
        System.out.println(cardNum[0][0] + "," + cardNum[0][1] + "," + cardNum[0][2] + "," + cardNum[0][3]);
        System.out.println(cardNum[1][0] + "," + cardNum[1][1] + "," + cardNum[1][2] + "," + cardNum[1][3]);
        System.out.println(cardNum[2][0] + "," + cardNum[2][1] + "," + cardNum[2][2] + "," + cardNum[2][3]);
        System.out.println(cardNum[3][0] + "," + cardNum[3][1] + "," + cardNum[3][2] + "," + cardNum[3][3]);
        System.out.println("");


        //System.out.println(w + "," + o + "," + ye + "," + b + "," + g + "," + r + "," + gray + "," + p);

    }
    int[][] cardType = new int [4][4];

    public void randomizedCard(){

        ArrayList<Integer>randCard = new ArrayList<>();
        randCard.add(0);
        randCard.add(1);
        randCard.add(2);
        randCard.add(3);
        randCard.add(4);
        randCard.add(5);
        randCard.add(6);
        randCard.add(7);
        randCard.add(8);
        randCard.add(9);
        randCard.add(10);
        randCard.add(11);
        randCard.add(12);
        randCard.add(13);
        randCard.add(14);
        randCard.add(15);

        Collections.shuffle(randCard);
        for (int x = 0; x < cardNum.length; x++) {
            for (int y = 0; y < cardNum[0].length; y++) {
                cardType[x][y] = randCard.get(x);
                cardType[x][y] = randCard.get(y);
            }
        }
        System.out.println(randCard);
    }

    //rules
    //when clicked the card is disabled and image is shown
        //if the 2 cards flipped are the same the card stays disabled
        //if the 2 cards flipped aren't the same the two selected cards get flipped over and images are hidden

    int start = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(start==0){
            randomizedCard2();
            start++;
        }
        if(e.getSource().equals(clear)){
            randomizedCard2();
            for (int x = 0; x < cards.length; x++) {
                for (int y = 0; y < cards[0].length; y++) {
                    cards[x][y].setEnabled(true);
                    cards[x][y].setIcon(cardBack);
                    cards[x][y].setBackground(darkOrange);
                }
            }

        }
        else{
            int revealed = 0;
            for (int i = 0; i < cards.length; i++) {
                for (int j = 0; j < cards[0].length; j++) {
                    if(!cards[i][j].isEnabled()){
                        revealed++;
                    }

                }
            }
            //System.out.println("revealed: " + revealed);
            if(revealed == 15){
                //System.out.println("win");
                revealed = 0;
            }
            for (int x = 0; x < cards.length; x++) {
                for (int y = 0; y < cards[0].length; y++) {
                    if(e.getSource().equals(cards[x][y])) {
                        if (cardNum[x][y] == 0 || cardNum[x][y] == 1) {
                            cards[x][y].setIcon(tiredWhite);
                            cards[x][y].setBackground(Color.WHITE);
                        }
                        if (cardNum[x][y] == 2 || cardNum[x][y] == 3) {
                            cards[x][y].setIcon(happyOrange);
                            cards[x][y].setBackground(Color.ORANGE);
                        }
                        if (cardNum[x][y] == 4 || cardNum[x][y] == 5) {
                            cards[x][y].setIcon(happyYellow);
                            cards[x][y].setBackground(Color.YELLOW);
                        }
                        if (cardNum[x][y] == 6 || cardNum[x][y] == 7) {
                            cards[x][y].setIcon(sadBlue);
                            cards[x][y].setBackground(Color.BLUE);
                        }
                        if (cardNum[x][y] == 8 || cardNum[x][y] == 9) {
                            cards[x][y].setIcon(sickGreen);
                            cards[x][y].setBackground(Color.GREEN);
                        }
                        if (cardNum[x][y] == 10 || cardNum[x][y] == 11) {
                            cards[x][y].setIcon(angryRed);
                            cards[x][y].setBackground(Color.RED);
                        }
                        if (cardNum[x][y] == 12 || cardNum[x][y] == 13) {
                            cards[x][y].setIcon(scaredGrey);
                            cards[x][y].setBackground(Color.GRAY);
                        }
                        if (cardNum[x][y] == 14 || cardNum[x][y] == 15) {
                            Color purple = new Color(138, 43, 226);
                            cards[x][y].setIcon(embarrassedPurple);
                            cards[x][y].setBackground(purple);
                        }
                            if (selected == 2) {
                                System.out.println(selected);
                                cards[x][y].setEnabled(false);
                                selected = 0;
                            }

                            else if(selected==91247932) {
                                cards[x][y].setEnabled(true);
                                cards[x][y].setBackground(null);
                                cards[x][y].setIcon(null);
                            }
                        cards[x][y].setEnabled(false);
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
