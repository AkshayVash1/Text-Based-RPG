import java.util.Random;
/**
 * Room
 */
public class Room {

    private int travelTable[];
    private String[] monsterTypes = {"Goblin", "Orc", "Ogre"};
    private int N, E, S, W;
    private String description;
    private String type;
    private Monster monster;
    private int floor;
    private Random rand = new Random();


    public Room(String type, int row, int collumn, int floor){
        if(row == 0){
            N = -1;
            S = 1;
        }
        else if(row == 1){
            N = 1;
            S = 1;
        }
        else if(row == 2){
            N = 1;
            S = -1;
        }
        if(collumn == 0){
            E = 1;
            W = -1;
        }
        else if(collumn == 1){
            E = 1;
            W = 1;
        }
        else if(collumn == 2){
            E = -1;
            W = 1;
        }

        this.travelTable = new int [] {N, E, S, W};
        this.type = type;
        this.floor = floor;

        switch (type){
            case "Combat":
                String monsterType = monsterTypes[rand.nextInt(monsterTypes.length)] ;
                monster = new Monster(monsterType, floor);
                description = "You enter the room and before you lies a resting" + monsterType + ".";
                break;
            case "Treasure":
                description = "You enter a brightly lit room. There are not any immediate threats nearby, but in the center of the room lies a battered wooden chest";
                break;
            case "Ascend":
                description = "As you enter the room a spiriling staircase leads into a dark room above you... Do you wish to ascend or continue expoling the floor?";
                break;
            case "Start":
                description = "You find yourself in a dark room, with nothing but crackde walls around you";
                break;
            default:
                break;
        }
    }

    public void enterRoom(){
        System.out.println(description);
    }
    public String getType(){
        return type;
    }

    public Monster getMonster(){
        return monster;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int[] getCardinal(){
        return travelTable;
    }

}

