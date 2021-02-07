import java.util.Scanner;
import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * Main
 */
public class Main {

    String userInput = "";
    public static void main(String[] args) {

        gui g = new gui();
        Main main = new Main();
        
        JTextArea myOutput = g.getOutput();
        JTextArea myInput = g.getInput();

        Scanner scanner = new Scanner(System.in);
        String userInput;
        String userName;
        int floor = 1;
        boolean running = false;

        myOutput.append("Enter a username: \n");

        userInput = main.getUserInput(g);

        player p = new player(userInput, g);

        myOutput.append("Welcome to the dungeon " + userInput +"\n");

        Map map = new Map(floor, g);
        map.printList();
        g.newMap(map);
        g.updateStats(p);

        myOutput.append("\n\n");

        map.getCurrentRoom().enterRoom();

        GAME:
        do{
            boolean nextRoom = false;
            while(!nextRoom){
                if(map.getCurrentRoom().getType().equalsIgnoreCase("Boss Start")){
                    do{
                        map.getLayout()[2][2].enterRoom();
                        startCombat(map.getLayout()[2][2].getMonster(), p, g);
                        g.getOutput().append("The follower of Demunes falls before you... As you continue towards the stairs at the end of the room");
                        floor ++;
                        map = new Map(floor, g);
                        map.getCurrentRoom().enterRoom();
                        g.newMap(map);

                        continue GAME;
                    }while(p.getHp()>0);

                }else{
                    myOutput.append("\nAround are several doorways, which do you wish to do? \n\t Go North \n\t Go East \n\t Go South \n\t Go West \n\t Check Inventory\n");

                    String direction = getUserInput(g);

                    if (direction.equalsIgnoreCase("north") || direction.equalsIgnoreCase("east")|| direction.equalsIgnoreCase("south") || direction.equalsIgnoreCase("west")) {
                        g.getOutput().setText(null);
                        if (newRoom(map, direction, g)) {
                            g.moveRoom(map);
                            nextRoom = true;
                        }
                    } else if (direction.equalsIgnoreCase("check inventory")) {
                        g.getOutput().setText(null);
                        p.printInventory();
                    } else {
                        myOutput.append("Invalid input... please try again.");
                    }
                }

                if (map.getCurrentRoom().getType().equalsIgnoreCase("combat")) {
                    g.getOutput().append("The monster readies its weapon towards you...\n\n");
                    startCombat(map.getCurrentRoom().getMonster(), p, g);
                    map.getCurrentRoom().setDescription(
                            "Before you lies the corpse of a slain " + map.getCurrentRoom().getMonster().getName());

                } else if (map.getCurrentRoom().getType().equalsIgnoreCase("treasure")) {
                    if (!map.getCurrentRoom().hasVistited()) {
                        g.getOutput().setText(null);
                        giveItem(p, g);
                        map.getCurrentRoom().setVisited();

                    } else {
                        g.getOutput().append("You have already visited this room, there is no more loot to be obtained");
                    }
                } else if (map.getCurrentRoom().getType().equalsIgnoreCase("ascend")) {
                    boolean cycle = true;
                    do {
                        myOutput.append(
                                "Are you sure you want to ascend to the next floor? There is no turning back... (Yes/No)");
                        String choice = getUserInput(g);

                        if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                            floor++;
                            map = new Map(floor, g);
                            map.getCurrentRoom().enterRoom();
                            g.newMap(map);
                            continue GAME;
                        } else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                            continue GAME;
                        } else {
                            myOutput.append("Invalid Input, try again...");
                        }
                    } while (cycle);
                }
            }
        } while (p.getHp() > 0);

    }

    public void Game(gui g) {

    }

    private static void giveItem(player p, gui g) {
        Random rand = new Random();
        String[] itemTypes = { "Sword", "Greatsword", "Head Guard", "Chest Armor", "Leg Armor", "Boots", "Shield" };
        String[] quality = { "Worn", "Normal", "Pristine" };
        String[] quality_p = { "Lesser", "Normal", "Greater" };

        int rand_item = rand.nextInt(itemTypes.length);
        int rand_quality = rand.nextInt(quality.length);
        int rand_potion = rand.nextInt(quality_p.length);

        g.getOutput().append("You apprach the chest and slowly open it to reveal some equipment\n");
        g.getOutput().append("\tYou found : " + quality[rand_quality] + " " + itemTypes[rand_item] + "!\n");
        g.getOutput().append("\tYou found : " + quality_p[rand_potion] + " Healing potion" + "!\n\n");

        Item main_drop = new Item(quality[rand_quality] + " " + itemTypes[rand_item], itemTypes[rand_item],
                quality[rand_quality]);
        Item sub_drop = new Item(quality_p[rand_potion] + " Healing potion", quality_p[rand_potion]);

        p.addPotions(sub_drop, p.getPotionSlots().size());

        if (main_drop.getType().equalsIgnoreCase("sword") || main_drop.getType().equalsIgnoreCase("greatsword")) {
            p.addWeapon(main_drop.getName(), main_drop.getDamageMod());
        } else {
            p.addArmor(main_drop.getName(), main_drop.getSlot(), main_drop.getAC());
        }
    }

    private static void startCombat(Monster monster, player p, gui g) {
        if (monster.getHP() <= 0) {
            return;
        }
        String action;
        Scanner scanner = new Scanner(System.in);

        do {
            g.getOutput().append("What will you do? \n    Attack \n    Use Item");

            action = getUserInput(g);
            if (action.equalsIgnoreCase("attack")) {
                g.getOutput().setText(null);
                p.attack(monster);
                if (monster.getHP() > 0) {
                    monster.attack(p);
                }
            } else if (action.equalsIgnoreCase("use item")) {
                g.getOutput().setText(null);
                p.useHealthPotion();
            } else {
                g.getOutput().setText(null);
                g.getOutput().append("Invalid input, try again...\n");
            }

            if (p.getHp() <= 0) {
                g.getOutput().setText(null);
                g.getOutput().append("You Died...");
                System.exit(0);
            }

            g.updateStats(p);

        } while (monster.getHP() > 0 && p.getHp() > 0);

        g.getOutput().append(
                "You have slain the " + monster.getName() + " You are awarded with " + monster.getXP() + " XP!");
        p.setXp(monster.getXP());

        g.updateStats(p);
    }

    private static boolean newRoom(Map map, String direction, gui g) {
        boolean move = false;
        if (direction.equalsIgnoreCase("north")) {
            if (map.getCurr_x() - 1 >= 0) {
                map.moveRooms(0);
                move = true;
            } else {
                g.getOutput().append("Cannot move in this direction");
            }
        } else if (direction.equalsIgnoreCase("east")) {
            if (map.getCurr_y() + 1 <= 2) {
                map.moveRooms(1);
                move = true;
            } else {
                g.getOutput().append("Cannot move in this direction");
            }
        } else if (direction.equalsIgnoreCase("south")) {
            if (map.getCurr_x() + 1 <= 2) {
                map.moveRooms(2);
                move = true;
            } else {
                g.getOutput().append("Cannot move in this direction");
            }
        } else if (direction.equalsIgnoreCase("west")) {
            if (map.getCurr_y() - 1 >= 0) {
                map.moveRooms(3);
                move = true;
            } else {
                g.getOutput().append("Cannot move in this direction");
            }
        }
        return move;
    }

    private static String getUserInput(gui g) {
        String input;
        do{
            input = g.getUserString();
            g.getOutput().append(input);
        }while(input == null);

        g.setUserString(null);
        g.getOutput().append("\n");
        g.getInput().setText("");

        return input;
    }
}
