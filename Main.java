import java.util.Scanner;
/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userName;
        String direction;
        int floor = 1;
        boolean running = true;

        System.out.println("Enter a Username:");
        userName = scanner.nextLine();

        player p = new player(userName);

        System.out.println("Welcome to the dungeon " + userName);

        Map map = new Map(floor);
        map.printList();
        System.out.println("----------------");
        System.out.println(map.getCurr_x() + ", " + map.getCurr_y());

        map.getCurrentRoom().enterRoom();

        GAME:
        do{
            boolean nextRoom = false;
            while(!nextRoom){
                System.out.println("Around are several doorways, which do you wish to go through? \n\t North \n\t East \n\t South \n\t West");

                direction = scanner.nextLine();
                System.out.println(direction);

                if(newRoom(map, direction)){
                    nextRoom = true;
                }
            }

            if(map.getCurrentRoom().getType().equalsIgnoreCase("Combat")){
                startCombat(map.getCurrentRoom().getMonster(), p);
                map.getCurrentRoom().setDescription("Before you lies the corpse of a slain " + map.getCurrentRoom().getMonster().getName());
            }
            continue GAME;
        }while(p.getHp()>0);


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

        }while(monster.getHP() > 0 && p.getHp() > 0 );


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
}