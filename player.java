import java.util.*;

public class player {
    
    private String name;
    private int hp = 50;
    private int dmgMod = 0;
    private int hitMod = 0;
    private int hpMod = 0;
    private int xp = 0;
    private int armor = 10;
    private int level = 1;
    private Monster monster;
    Random rand = new Random();
    Scanner myObj = new Scanner(System.in);
    String[][] armorSlots = {
        {"head","None","0"},    
        {"chest","None","0"}, 
        {"legs","None","0"}, 
        {"boot","None","0"},
        {"offhand","None","0"} 
    };
    HashMap<Integer, String> potionSlots = new HashMap<>();

    String[] weapon = {"Broken Sword","0"};
    

    public player(){}

    public player(String name){
        this.name = name;
    }

    
    public int dice(int size){
        return rand.nextInt(size)+1; 
    }
    
    public void attack(){
        if(dice(20)+hitMod > monster.getArmor()){
            int damage = dice(20)+dmgMod;
            System.out.println("You struck the "+monster.getName()+" for "+damage+" damage!");
            monster.takeDamage(damage);
        }
        else{
            System.out.println("The monster evaded your attack!");
        }

    }
    
    public void takeDamage(int dmg){
        System.out.println(name+" was hit for "+dmg+" damage!");
        this.hp = hp - dmg;

    }
    
    public void addArmor(String armorName,int row, int value){
        boolean cycle = true;
        if(armorSlots[row][2].equals("0")){
            armorSlots[row][1] = armorName;
            armorSlots[row][2] = Integer.toString(value);
            armor = armor + value;
            System.out.println("Your '"+armorSlots[row][1]+"' has been replaced with '"+armorName+"'");
        }
        else{
            do{
                System.out.println("Would you like to replace your current item: "+armorSlots[row][1]+",(Armor Value: "+armorSlots[row][2]+")");
                System.out.println("Yes or No:");
                String choice = myObj.nextLine();
                if(choice.equals("Y") || choice.equals("y") || choice.equals("Yes") || choice.equals("yes")){
                    armor = armor - Integer.valueOf(armorSlots[row][2]);
                    armorSlots[row][1] = armorName;
                    armorSlots[row][2] = Integer.toString(value);
                    armor = armor + value;
                    System.out.println("Your "+armorSlots[row][1]+"-piece has been replaced with"+armorName);
                    cycle = false;
                }
                else if(choice.equals("N") || choice.equals("n") || choice.equals("No") || choice.equals("no")){
                    System.out.println("Your "+armor+"-piece has not been replaced");
                    cycle = false;
                }
                else{
                    System.out.println("Invalid input... Try again");
                }
            }while(cycle == true);
            
        }

    }

    public void addPotions(String potionName, int val){
        boolean cycle = true;
        if(potionSlots.size() < 5){
            potionSlots.put(val,potionName);
            System.out.println(potionName+" was added to your inventory!");
        }
        else{
            do{
                System.out.println("Would you like to replace one of your potions?");
                int j = 0;
                for (String i : potionSlots.values()) {
                    System.out.print(j+"."+i+"\n");
                    j++;
                }
                System.out.println("Potion 1,2,3,4,5 or none");
                String choice = myObj.nextLine();
                if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5")){
                    potionSlots.remove(Integer.valueOf(choice));
                }
                else if(choice.equals("none") || choice.equals("None")){
                    System.out.println("No potion was replaced...");
                }
                else{
                    System.out.println("Invalid input... Try again");
                }

            }while(cycle == true);
        }

    }

    public void addWeapon(String weaponName, int weaponDmg){
        boolean cycle = true;
        do{
            System.out.println("Would you like to replace your "+weapon[0]+"?");
            System.out.println("Yes or No?");
            String choice = myObj.nextLine();
            if(choice.equals("y") || choice.equals("Y") || choice.equals("yes") || choice.equals("Yes")){
                System.out.println("Your "+weapon[0]+" was replaced with "+weaponName);
                dmgMod = dmgMod - Integer.valueOf(weapon[1]);
                weapon[0] = weaponName;
                weapon[1] = Integer.toString(weaponDmg);
                dmgMod = dmgMod + weaponDmg;
            }
            else if(choice.equals("n") || choice.equals("N") || choice.equals("No") || choice.equals("no")){
                System.out.println("Your weapon was not replaced...");
            }
            else{
                System.out.println("Invalid input... Try again");
            }

        }while(cycle == true);
    }


    public String getName() {
        return this.name;
    }


    public int getHp() {
        return this.hp;
    }

    public int getArmor(){
        return this.armor;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }


    public int getHpMod() {
        return this.hpMod;
    }

    public void setHpMod(int hpMod) {
        this.hpMod = hpMod;
    }

    public int getXp() {
        return this.xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
        if(this.xp >= 1000 + level*500){
            this.xp = this.xp -1000;
            levelUp();
        }
    }

    public void levelUp(){
        hp += 10;
    }
    
    
    

}
