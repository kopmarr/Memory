import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class Memory implements MouseListener, ActionListener, Runnable {

    ImageIcon scaredGrey = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0.png").getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
    ImageIcon happyYellow = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (1).png").getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
    ImageIcon sadBlue = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (2).png").getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
    ImageIcon sickGreen = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (3).png").getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
    ImageIcon happyOrange = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (4).png").getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
    ImageIcon angryRed = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (5).png").getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
    ImageIcon tiredWhite = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (6).png").getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
    ImageIcon embarrasedPurple = new ImageIcon(new ImageIcon("c:/Users/kpearson2789/Downloads/pixil-frame-0 (7).png").getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));

    private static final int CARD = 16;
    int[][] counts = new int[4][4];
    JButton[][] cards = new JButton[4][4];
    JFrame frame = new JFrame("MEMORY MATCH");
    Container center = new Container();
    Container west = new Container();
        JButton clear = new JButton();
    int selected = 0;

    public static void main(String[] args) {new Memory();}

    public Memory(){
        frame.setSize(1000,1000);

        west.setLayout(new BorderLayout());
        center.setLayout(new GridLayout(4,4));
        for (int row = 0; row < counts.length; row++) {
            for (int column = 0; column < counts[0].length; column++) {
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

    Random randNum = new Random();
    public void randomizedCard(){
        ArrayList<Integer> list = new ArrayList<Integer>(400);
        for (int x = 0; x < counts.length; x++) {
            for (int y = 0; y < counts[0].length; y++) {
                list.add(x*100+y);
            }

        }
        counts = new int[4][4];

        for (int x = 0; x < CARD; x++) {
            int choice = (randNum.nextInt(list.size()));
            counts[list.get(choice) / 100][list.get(choice) % 100] = CARD;
            list.remove(choice);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(clear)){
            System.out.println("clear");
        }
        else{
            for (int x = 0; x < cards.length; x++) {
                for (int y = 0; y < cards[0].length; y++) {
                    if(e.getSource().equals(cards[x][y])) {
                        if(selected == 2){
                            selected = 0;
                            cards[x][y].setEnabled(true);

                        }
                        selected++;
                        System.out.println(selected);
                        cards[x][y].setEnabled(false);

                    }
                    if(cards[x][y]!= cards[x][y]){
                        cards[x][y].setEnabled(true);
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
