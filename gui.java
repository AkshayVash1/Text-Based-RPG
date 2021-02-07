import javax.swing.*;
import javax.swing.border.Border;

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
    private JLabel titleCard,back,stats,floor,armor,weapon;
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
    ImageIcon startIn = new ImageIcon("Icons/start_in.jpg");
    ImageIcon startout = new ImageIcon("Icons/start_out.jpg");
    ImageIcon startIcon = new ImageIcon("Icons/start.jpg");
    ImageIcon helpIcon = new ImageIcon("Icons/help.jpg");




    public gui(){
        JFrame frame = new JFrame("Dungeon Dwellers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        miniMap = new JPanel();
        playerDetail = new JPanel();

        panel.setBounds(0,0,1200,750);
        panel.setBackground(Color.black);
        miniMap.setBackground(Color.BLACK);
        playerDetail.setBounds(0,0,350,400);
        playerDetail.setBackground(Color.black);
        miniMap.setBounds(662,75,239,264);

        input = new JTextArea();
        output = new JTextArea();
        output.setBounds(0,400,1184,245);
        input.setBounds(0,655,1184,95);
        input.setBackground(Color.black);
        input.setForeground(Color.white);
        output.setBackground(Color.black);
        output.setForeground(Color.white);
        output.setEditable(false);


        //adding borders
        Border gray = BorderFactory.createLineBorder(Color.gray);
        //miniMap.setBorder(gray);
        input.setBorder(gray);
        output.setBorder(gray);
        playerDetail.setBorder(gray);

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

        start = new JButton("Start", startIcon);
        help = new JButton("Help", helpIcon);
        stats = new JLabel("HP,XP,LEVEL");
        armor = new JLabel("Head,Chest,Legs,Boots,Shield", SwingConstants.CENTER);
        weapon = new JLabel("Weapon", SwingConstants.CENTER);
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
        titleCard.setForeground(Color.white);
        stats.setFont(new Font("Verdana", Font.BOLD, 12));
        stats.setForeground(Color.white);
        armor.setFont(new Font("Verdana", Font.BOLD, 12));
        armor.setForeground(Color.white);
        weapon.setFont(new Font("Verdana", Font.BOLD, 12));
        weapon.setForeground(Color.white);


        help.addActionListener(this);
        help.setActionCommand(Actions.HELP.name());
        start.addActionListener(this);
        start.setActionCommand(Actions.START.name());

        playerDetail.add(stats);
        playerDetail.add(armor);
        playerDetail.add(weapon);

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
            titleCard.setVisible(false);
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
        stats.setText("<html><font color=green>Name: </font>" + player.getName() +" <font color=green>Level:</font> "+player.getLevel() + "<font color=yellow>(" + player.getXp() +"/"+ (250 + player.getLevel()*250) +
        ")</font> <font color=red>HP: </font>" + player.getHp()+" / "+player.getMaxHP());
    }

    public void updateArmor(player player){
        armor.setText("<html><br/>Head Piece: " + player.armorSlots[0][1] + "<br/> <br/>Chest Armor: " + player.armorSlots[1][1] + "<br/> <br/>Leg Armor: " + player.armorSlots[2][1] +
        "<br/><br/> Boots: " + player.armorSlots[3][1] + "<br/><br/> Shield: " + player.armorSlots[4][1] + "</html>");
    }

    public void updateWeapon(player player){
        weapon.setText("<html> <br/>Weapon: " + player.weapon[0] + "</html>");
    }

    public void newMap(Map newMap){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                String type = newMap.getLayout()[i][j].getType();

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
                else if(type.equalsIgnoreCase("start") || type.equalsIgnoreCase("boss start")){
                    map[i][j].setIcon(startIn);
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

    public void setUserString(String input){
        userString = input;
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
