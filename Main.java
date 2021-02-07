import java.util.Scanner;
import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {

        gui g = new gui();

        JTextArea myOutput = g.getOutput();
        JTextArea myInput = g.getInput();

        Scanner scanner = new Scanner(System.in);
        String userInput;
        String userName;
        String direction;
        int floor = 1;
        boolean running = false;

        myOutput.append("Enter a username: \n");

        userName = getUserInput(g);

        player p = new player(userName);

        myOutput.append("Welcome to the dungeon " + userName +"\n");

        Map map = new Map(floor);
        map.printList();
        g.newMap(map);

        myOutput.append("\n\n");

        System.out.println("----------------");
        System.out.println(map.getCurr_x() + ", " + map.getCurr_y());

        map.getCurrentRoom().enterRoom();


        GAME:
        do{
            boolean nextRoom = false;
            while(!nextRoom){
                if(map.getCurrentRoom().getType().equalsIgnoreCase("boss")){
                    do{
                        startCombat(map.getCurrentRoom().getMonster(), p);
                        System.out.println("ASCENDING FLOOR");
                        floor ++;
                        map = new Map(floor);
                        map.getCurrentRoom().enterRoom();
                        continue GAME;

                    }while(p.getHp()>0);
                }else{

                    System.out.println("-----------------------------------");
                    System.out.println("Around are several doorways, which do you wish to do? \n\t Go North \n\t Go East \n\t Go South \n\t Go West \n\t Check Inventory");
                    System.out.println(p.getArmor() + "AC");
                    System.out.println(p.getDamageMod());

                    direction = scanner.nextLine();

                    if(direction.equalsIgnoreCase("north") || direction.equalsIgnoreCase("east") || direction.equalsIgnoreCase("south") || direction.equalsIgnoreCase("west") ){
                        if(newRoom(map, direction)){
                            nextRoom = true;
                        }
                    }
                    else if(direction.equalsIgnoreCase("check inventory")){
                        p.printInventory();
                    }
                    else{
                        System.out.println("Invalid input... please try again.");
                    }
                }

                if(map.getCurrentRoom().getType().equalsIgnoreCase("combat")){
                    startCombat(map.getCurrentRoom().getMonster(), p);
                    map.getCurrentRoom().setDescription("Before you lies the corpse of a slain " + map.getCurrentRoom().getMonster().getName());
                }
                else if(map.getCurrentRoom().getType().equalsIgnoreCase("treasure")){
                    if(!map.getCurrentRoom().hasVistited()){
                        giveItem(p);
                        map.getCurrentRoom().setVisited();
                    }
                    else{
                        System.out.println("You have already visited this room, there is no more loot to be obtained");
                    }
                }
                else if(map.getCurrentRoom().getType().equalsIgnoreCase("ascend")){
                    boolean cycle = true;
                    do{
                        System.out.println("Are you sure you want to ascend to the next floor? There is no turning back... (Yes/No)");
                        String choice = scanner.nextLine();

                        if(choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")){
                            floor ++;
                            map = new Map(floor);
                            map.getCurrentRoom().enterRoom();
                            //map.printList();
                            continue GAME;
                        }
                        else if(choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")){
                            continue GAME;
                        }
                        else{ System.out.println("Invalid Input, try again..."); }
                    }while(cycle);
                }
            }
        }while(p.getHp()>0);

    }

    private static void giveItem(player p){
        Random rand = new Random();
        String[] itemTypes = {"Sword", "Greatsword", "Head", "Chest", "Legs", "Boots", "Shield"};
        String[] quality = {"Worn", "Normal", "Pristine"};
        String[] quality_p = {"Lesser", "Normal", "Greater"};

        int rand_item = rand.nextInt(itemTypes.length);
        int rand_quality = rand.nextInt(quality.length);
        int rand_potion = rand.nextInt(quality_p.length);

        System.out.println("--------------------------------------");
        System.out.println("You apprach the chest and slowly open it to reveal some equipment");
        System.out.println("\tYou found : " + quality[rand_quality] +" " + itemTypes[rand_item] + "!");
        System.out.println("\tYou found : " + quality_p[rand_potion] + " Healing potion" + "!");
        System.out.println("--------------------------------------");

        Item main_drop = new Item(quality[rand_quality] +" " + itemTypes[rand_item], itemTypes[rand_item], quality[rand_quality]);
        Item sub_drop = new Item(quality_p[rand_potion] + " Healing potion", quality_p[rand_potion]);

        p.addPotions(sub_drop, p.getPotionSlots().size());

        if(main_drop.getType().equalsIgnoreCase("sword") || main_drop.getType().equalsIgnoreCase("greatsword")){
            p.addWeapon(main_drop.getName(), main_drop.getDamageMod());
            System.out.println("DAMAGE MOD: "+ main_drop.getDamageMod());
        }
        else{
            p.addArmor(main_drop.getName(), main_drop.getSlot(), main_drop.getAC());
            System.out.println("AC MOD: " + main_drop.getAC());
        }
    }

    private static void startCombat(Monster monster, player p){
        if(monster.getHP() <= 0){
            return;
        }
        System.out.println("The monster readies its weapon towards you...");
        String action;
        Scanner scanner = new Scanner(System.in);

        do{
            System.out.println(p.getHp() +", " + monster.getHP());
            System.out.println("What will you do? \n\t Attack \n\t Use Item");

            action = scanner.nextLine();
            if(action.equalsIgnoreCase("attack")){
                p.attack(monster);
                if(monster.getHP()>0){
                    monster.attack(p);
                }
            }
            else if(action.equalsIgnoreCase("use item")){
                p.useHealthPotion();
            }
            else{
                System.out.println("Invalid input, try again...");
            }

            if(p.getHp() <= 0){
                System.out.println("You Died...");
                System.exit(0);
            }
        }while(monster.getHP() > 0 && p.getHp() > 0 );

        System.out.println("You have slain the " + monster.getName() + " You are awarded with " + monster.getXP() + " XP!");
        p.setXp(monster.getXP());
    }

    private static boolean newRoom(Map map, String direction){
        boolean move = false;
        if(direction.equalsIgnoreCase("north")){
            if(map.getCurr_x() - 1 >= 0){
                map.moveRooms(0);
                move = true;
            }
            else{
                System.out.println("Cannot move in this direction");
            }
        }
        else if(direction.equalsIgnoreCase("east")){
            if(map.getCurr_y() + 1 <= 2){
                map.moveRooms(1);
                move = true;
            }
            else{
                System.out.println("Cannot move in this direction");
            }
        }
        else if(direction.equalsIgnoreCase("south")){
            if(map.getCurr_x() + 1 <= 2){
                map.moveRooms(2);
                move = true;
            }
            else{
                System.out.println("Cannot move in this direction");
            }
        }
        else if(direction.equalsIgnoreCase("west")){
            if(map.getCurr_y() - 1 >= 0){
                map.moveRooms(3);
                move = true;
            }
            else{
                System.out.println("Cannot move in this direction");
            }
        }
        return move;
    }

    private static String getUserInput(gui g){
        String input;
        do{
            input = g.getUserString();
            g.getOutput().append(input);
        }while(input == null);

        g.getOutput().append("\n");

        return input;
    }
}
