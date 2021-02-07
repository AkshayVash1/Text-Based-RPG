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
    private boolean visited = false;
    private Random rand = new Random();
    private gui g;


    public Room(String type, int row, int collumn, int floor, gui g){
        this.g = g;

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
                monster = new Monster(monsterType, floor, g);
                description = "You enter the room and before you lies a resting " + monsterType + "...";
                break;
            case "Treasure":
                description = "You enter a brightly lit room. There are not any immediate threats nearby, but in the center of the room lies a battered wooden chest...";
                break;
            case "Ascend":
                description = "As you enter the room a spiriling staircase leads into a dark room above you... Do you wish to ascend or continue exploring the floor?";
                break;
            case "Start":
                description = "You find yourself in a dark room, with nothing but cracked walls around you...";
                break;
            case "Boss":
                monster = new Monster("Follower of Demunes", floor, g);
                description = "As you enter the large room, in the center stands a " + monster.getName() + " as he slowly turns to face you...";
                break;
            case "Boss Start":
                description = "As you arrive at the new floor, you notice a significant change in atmostphere... Ahead of you is a single door that seems to lead into a large room";
                break;
            default:
                break;
        }
    }

    public void enterRoom(){
        g.getOutput().append(description + "\n\n");
    }

    public void setVisited(){
        this.visited = true;
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

    public boolean hasVistited(){
        return visited;
    }

}

