import javax.swing.*;
import java.awt.*;

public class gui extends JFrame{ 
    
    public gui(){
        JFrame frame = new JFrame("Dungeon Dwellers");
        JPanel panel = new JPanel();
        panel.setBounds(0,0,1200,750);
        panel.setBackground(Color.gray);

        JButton start = new JButton("Start");
        JButton help = new JButton("Help");
        JLabel title = new JLabel("Dungeon Dwellers");
        title.setBounds(400,100,400,200);
        start.setBounds(500,275,200,100);
        help.setBounds(500,400,200,100);

        panel.add(start);
        panel.add(help);
        panel.add(title);
        panel.setLayout(null);


        frame.add(panel);
        frame.setLayout(null);
        frame.setBackground(Color.gray);
        frame.setSize(1200,750);
        frame.setVisible(true);
    }

    public static void main(String args[]){
        new gui();
    }



}
