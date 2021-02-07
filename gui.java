import javax.swing.*;

//import jdk.javadoc.internal.doclets.formats.html.resources.standard;

import java.awt.*;
import java.awt.event.*;


public class gui extends JFrame implements ActionListener {

    private enum Actions {
        HELP,
        START
      }

    private String userString;

    private JButton start,help;
    private JButton s;
    private JLabel titleCard,back,stats,floor;
    private JTextArea input,output;
    private JPanel miniMap,playerDetail;
    private JLabel[][] map = new JLabel[3][3];
    ImageIcon startBack = new ImageIcon("Icons/start_menu.jpg");
    ImageIcon treasureIn = new ImageIcon("Icons/treasure_in.jpg");
    ImageIcon treasureOut = new ImageIcon("Icons/treasure_out.jpg");
    ImageIcon combatIn = new ImageIcon("Icons/combat_in.jpg");
    ImageIcon combatOut = new ImageIcon("Icons/combat_out.jpg");
    ImageIcon ascendIn = new ImageIcon("Icons/ascend_in.jpg");
    ImageIcon ascendOut = new ImageIcon("Icons/ascend_out.jpg");
    ImageIcon boss = new ImageIcon("Icons/boss.jpg");



    public gui(){
        JFrame frame = new JFrame("Dungeon Dwellers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        miniMap = new JPanel();
        playerDetail = new JPanel();

        panel.setBounds(0,0,1200,750);
        panel.setBackground(Color.white);
        playerDetail.setBounds(0,0,400,400);
        playerDetail.setBackground(Color.black);
        miniMap.setBounds(400,0,800,400);

        input = new JTextArea();
        output = new JTextArea();
        output.setBounds(0,400,1200,245);
        input.setBounds(0,655,1200,95);
        input.setBackground(Color.black);
        input.setForeground(Color.white);
        output.setBackground(Color.black);
        output.setForeground(Color.white);
        output.setEditable(false);

        input.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    userString = input.getText();
                }
            }
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        start = new JButton("Start");
        help = new JButton("Help");
        stats = new JLabel("HP,XP,LEVEL");
        floor = new JLabel("Floor 1");
        titleCard = new JLabel("Dungeon Dwellers");
        back = new JLabel(startBack);

        miniMap.setLayout(new GridLayout(3, 3));
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    map[i][j] = new JLabel();
                    miniMap.add(map[i][j]);
                }
            }

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

        playerDetail.add(stats);

        panel.setLayout(null);
        panel.add(start);
        panel.add(help);
        panel.add(titleCard);
        panel.add(input);
        panel.add(output);
        panel.add(back);

        frame.setLayout(null);
        frame.add(miniMap);
        frame.add(playerDetail);
        frame.add(panel);
        frame.setBackground(Color.gray);
        frame.setSize(1200,750);
        frame.setVisible(true);
        frame.setResizable(false);
        input.setVisible(false);
        output.setVisible(false);
        miniMap.setVisible(false);
        playerDetail.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == Actions.HELP.name()){
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(null,"Dungeon Dwellers is a text based dungeon crawling game.\nYou are trapped in the bottom of a dark dungeon, with no exit in sight...\n"+
            "How far will you make it...\n\nDesigned By: Akshay Vashisht, Bilal Chaudhry, Kousha Motazedian, Matthew Parker ","Help",JOptionPane.INFORMATION_MESSAGE);
        }
        else if(e.getActionCommand() == Actions.START.name()){
            start.setVisible(false);
            help.setVisible(false);
            input.setVisible(true);
            output.setVisible(true);
            back.setIcon(null);
            miniMap.setVisible(true);
            playerDetail.setVisible(true);
        }
    }


    public void updateStats(player player){
        stats.setText("HP: "+player.getHp()+"/"+player.getMaxHP()+"\nXP: "+player.getXp()+"\nLevel: "+player.getLevel());
    }

    public void newMap(Map newMap){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                String type = newMap.getCurrentRoom().getType();
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

                if(type.equalsIgnoreCase("combat")){
                    map[i][j].setIcon(combatOut);
                }
                else if(type.equalsIgnoreCase("treasure")){
                    map[i][j].setIcon(treasureOut);
                }
                else if(type.equalsIgnoreCase("boss")){
                    map[i][j].setIcon(boss);
                }
                else if(type.equalsIgnoreCase("ascend")){
                    map[i][j].setIcon(ascendOut);
                }
                map[i][j].setVisible(true);
            }
        }
    }

    public void moveRoom(Map newMap){
        int x = newMap.getCurr_x();
        int y = newMap.getCurr_y();
        String type = newMap.getCurrentRoom().getType();

        if(type.equalsIgnoreCase("combat")){
            map[x][y].setIcon(combatIn);
        }
        else if(type.equalsIgnoreCase("treasure")){
            map[x][y].setIcon(treasureIn);
        }
        else if(type.equalsIgnoreCase("boss")){
            map[x][y].setIcon(boss);
        }
        else if(type.equalsIgnoreCase("ascend")){
            map[x][y].setIcon(ascendIn);
        }

    }

    public String submitAction(JTextArea input){
        userString = input.getText();
        return userString;
    }

    public String getUserString(){
        return userString;
    }

    public JTextArea getOutput(){
        return output;
    }

    public JTextArea getInput(){
        return input;
    }

    public static void main(String args[]){
        new gui();
    }



}
