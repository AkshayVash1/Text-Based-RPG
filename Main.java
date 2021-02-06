import java.util.Scanner;
/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean nextRoom = false;
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

        GAME:
        while(running){
            map.getCurrentRoom().enterRoom();
            while(!nextRoom){
                System.out.println("Around are several doorways, which do you wish to go through? \n\t North \n\t East \n\t South \n\t West");

                direction = scanner.nextLine();
                System.out.println(direction);

                if(newRoom(map, direction)){
                    nextRoom = true;
                }
            }

            running = false;
        }


    }

    private static boolean newRoom(Map map, String direction){
        boolean move = false;
        if(direction.equalsIgnoreCase("north")){
            if(map.getCurr_x() - 1 > 0){
                map.moveRooms(0);
                move = true;
            }
            else{
                System.out.println("Cannot move in this direction");
            }
        }
        else if(direction.equalsIgnoreCase("east")){
            if(map.getCurr_y() + 1 > 2){
                map.moveRooms(1);
                move = true;
            }
            else{
                System.out.println("Cannot move in this direction");
            }
        }
        else if(direction.equalsIgnoreCase("south")){
            if(map.getCurr_x() + 1 > 2){
                map.moveRooms(2);
                move = true;
            }
            else{
                System.out.println("Cannot move in this direction");
            }
        }
        else if(direction.equalsIgnoreCase("west")){
            if(map.getCurr_y() - 1 > 0){
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