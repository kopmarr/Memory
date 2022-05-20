import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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

    private static final int CARD = 16;
    int[][] cardNum = new int[4][4];
    JButton[][] cards = new JButton[4][4];
    JFrame frame = new JFrame("MEMORY MATCH");
    Container center = new Container();
    Container west = new Container();
        JButton clear = new JButton();
    int selected = 2;

    public static void main(String[] args) {new Memory();}

    public Memory(){
        frame.setSize(1000,1000);

        west.setLayout(new BorderLayout());
        center.setLayout(new GridLayout(4,4));
        for (int row = 0; row < cardNum.length; row++) {
            for (int column = 0; column < cardNum[0].length; column++) {
                cards[column][row] = new JButton();
                center.add(cards[column][row]);
                cards[column][row].addActionListener(this);
            }
        }
        frame.add(center, BorderLayout.CENTER);
        frame.add(west, BorderLayout.WEST);
        clear.addActionListener(this);
        //clear.setIcon(sickGreen);
        west.add(clear);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //card is each assigned number 1-8 each number corresponds with a card image
    //there can only be 2 of each number, if both equal the same number it's a match

    int w;
    int o;
    int ye;
    int b;
    int g;
    int r;
    int gray;
    int p;

    Random randNum = new Random();
    public void randomizedCard(){

        for (int x = 0; x < cards.length; x++) {
            for (int y = 0; y < cards[0].length; y++) {
                int randNum = (int) (Math.random() * 8);

                cardNum[x][y] = randNum;
                
                if(cardNum[x][y] == 0 && w < 2){
                    cards[x][y].setIcon(tiredWhite);
                    cards[x][y].setBackground(Color.WHITE);
                    w++;
                }
                if(cardNum[x][y] == 1 && o < 2){
                    cards[x][y].setIcon(happyOrange);
                    cards[x][y].setBackground(Color.ORANGE);
                    o++;
                }
                if(cardNum[x][y] == 2 && ye < 2){
                    cards[x][y].setIcon(happyYellow);
                    cards[x][y].setBackground(Color.YELLOW);
                    ye++;
                }
                if(cardNum[x][y] == 3 && b < 2){
                    cards[x][y].setIcon(sadBlue);
                    cards[x][y].setBackground(Color.BLUE);
                    b++;
                }
                if(cardNum[x][y] == 4 && g < 2){
                    cards[x][y].setIcon(sickGreen);
                    cards[x][y].setBackground(Color.GREEN);
                    g++;
                }
                if(cardNum[x][y] == 5 && r < 2){
                    cards[x][y].setIcon(angryRed);
                    cards[x][y].setBackground(Color.RED);
                    r++;
                }
                if(cardNum[x][y] == 6 && gray < 2){
                    cards[x][y].setIcon(scaredGrey);
                    cards[x][y].setBackground(Color.GRAY);
                    gray++;
                }
                if(cardNum[x][y] == 7 && p < 2){
                    Color purple = new Color(138,43,226);
                    cards[x][y].setIcon(embarrassedPurple);
                    cards[x][y].setBackground(purple);
                    p++;
                }
            }
        }
        System.out.println(w + "," + o + "," + ye + "," + b + "," + g + "," + r + "," + gray + "," + p);
    }

    //rules
    //when clicked the card is disabled and image is shown
        //if the 2 cards flipped are the same the card stays disabled
        //if the 2 cards flipped aren't the same the two selected cards get flipped over and images are hidden

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(clear)){
            System.out.println("clear");
            randomizedCard();

        }
        else{
            for (int x = 0; x < cards.length; x++) {
                for (int y = 0; y < cards[0].length; y++) {
                    if(e.getSource().equals(cards[x][y])) {

                        //cards[x][y].setIcon(happyOrange);
                            if (selected == 2) {
                                System.out.println(selected);
                                cards[x][y].setEnabled(true);
                                selected = 0;
                            }
                        cards[x][y].setEnabled(false);
                        selected++;
                    }
                    }
                    //if(cards[x][y]!= cards[x][y]){
                    //    cards[x][y].setEnabled(true);
                    //}
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
