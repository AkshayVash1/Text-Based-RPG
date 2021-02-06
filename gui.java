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
        start.setBounds(500,275,200,100);
        help.setBounds(500,400,200,100);
        title.setBounds(375,50,450,300);
        title.setFont(new Font("Verdana", Font.PLAIN, 47));

        panel.setLayout(null);
        panel.add(start);
        panel.add(help);
        panel.add(title);
        

        frame.setLayout(null);
        frame.add(panel);
        frame.setBackground(Color.gray);
        frame.setSize(1200,750);
        frame.setVisible(true);
    }

    public static void main(String args[]){
        new gui();
    }



}
