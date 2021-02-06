import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class gui extends JFrame implements ActionListener{ 
    
    private enum Actions {
        HELP,
        START
      }

    private JButton start,help;
    private JLabel titleCard,back;
    private JTextField input,output;

    public gui(){
        JFrame frame = new JFrame("Dungeon Dwellers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        ImageIcon startBack = new ImageIcon("Icons/start_menu.jpg");
        
        panel.setBounds(0,0,1200,750);
        panel.setBackground(Color.gray);

        input = new JTextField();
        output = new JTextField();
        input.setBounds(0,450,600,300);
        output.setBounds(600,450,600,300);
        input.setBackground(Color.black);
        input.setForeground(Color.white);
        output.setBackground(Color.black);
        output.setForeground(Color.white);
        output.setEditable(false);
        input.setHorizontalAlignment(JTextField.LEFT);
        
        
        start = new JButton("Start");
        help = new JButton("Help");
        titleCard = new JLabel("Dungeon Dwellers");
        back = new JLabel(startBack);
        
        start.setBounds(350,500,200,100);
        help.setBounds(650,500,200,100);
        titleCard.setBounds(375,50,450,300);
        back.setBounds(0,0,1200,750);
        titleCard.setFont(new Font("Verdana", Font.BOLD, 43));
        titleCard.setForeground(Color.black);

        help.addActionListener(this);
        help.setActionCommand(Actions.HELP.name());
        start.addActionListener(this);
        start.setActionCommand(Actions.START.name());

        panel.setLayout(null);
        panel.add(start);
        panel.add(help);
        panel.add(titleCard);
        panel.add(input);
        panel.add(output);
        panel.add(back);
        

        frame.setLayout(null);
        frame.add(panel);
        frame.setBackground(Color.gray);
        frame.setSize(1200,750);
        frame.setVisible(true);
        frame.setResizable(false);
        input.setVisible(false);
        output.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == Actions.HELP.name()){    
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f,"Help");
        }
        else if(e.getActionCommand() == Actions.START.name()){    
            start.setVisible(false);
            help.setVisible(false);
            input.setVisible(true);
            output.setVisible(true);
        }


    }

    public static void main(String args[]){
        new gui();
    }



}
