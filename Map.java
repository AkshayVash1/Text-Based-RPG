import java.util.Random;

/**
 * Map
 */
public class Map {

    private Room[][] layout = new Room[3][3];
    private String[] roomTypes = {"Combat", "Treasure", "Ascend", "Start"};
    private Room currentRoom = null;
    private int size = 3;
    private int current_x;
    private int current_y;
    private int floor;
    private Random rand = new Random();

    public Map(int floor){
        int random_int;
        String type;
        int num_combat = 0;
        int num_treasure = 0;
        int num_ascend = 0;
        int num_start = 0;

        for(int r = 0; r < size; r++){

            for(int c = 0; c < size; c++){

                while(layout[r][c] == null){

                    random_int = rand.nextInt(4);
                    type = roomTypes[random_int];

                    if(type == "Ascend" && num_ascend != 1){
                        layout[r][c] = new Room("Ascend", r, c, size);
                        num_ascend += 1;
                    }
                    else if(type == "Start" && num_start != 1){
                        layout[r][c] = new Room("Start", r, c, size);
                        currentRoom = layout[r][c];
                        current_x = r;
                        current_y = c;
                        num_start += 1;
                    }
                    else if(type == "Combat" && num_combat != 4){
                        layout[r][c] = new Room("Combat", r, c, size);
                        num_combat += 1;
                    }
                    else if(type == "Treasure" && num_treasure != 3){
                        layout[r][c] = new Room("Treasure", r, c, size);
                        num_treasure += 1;
                    }
                }
            }
        }
}

    public Room getCurrentRoom(){
        return currentRoom;
    }
    public int getCurr_x(){
        return current_x;
    }
    public int getCurr_y(){
        return current_y;
    }

    public int getFloor(){
        return floor;
    }

    public Room moveRooms(int direction){
        if(currentRoom.getCardinal()[direction] == 1){
            if(direction == 0){
                currentRoom = layout[current_x][current_y];
                current_x -= 1;
                currentRoom.enterRoom();
            }
            else if(direction == 1){
                 currentRoom = layout[current_x][current_y];
                 current_y += 1;
                currentRoom.enterRoom();
                }
            else if(direction == 2){
                currentRoom = layout[current_x][current_y];
                current_x += 1;
                currentRoom.enterRoom();
            }
            else if(direction == 3){
                currentRoom = layout[current_x][current_y];
                current_y -= 1;
                currentRoom.enterRoom();
            }
        }

        return currentRoom;
    }

    public void printList(){
        for(int r = 0; r < size; r++){
            for(int c = 0; c < size; c++){
                System.out.println(layout[r][c].getType());
                layout[r][c].getCardinal();
            }

        }
    }

    public static void main(String[] args) {
        System.out.println("hello");
        Map map = new Map(1);

        map.printList();
        System.out.println(""+map.current_x + ","+ map.current_y);

        map.moveRooms(0);

        System.out.println(""+map.current_x + ","+ map.current_y);
    }
}