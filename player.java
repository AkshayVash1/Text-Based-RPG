import java.util.*;

public class player {
    
    private String name;
    private int hp = 50;
    private int dmgMod = 0;
    private int hitMod = 0;
    private int hpMod = 0;
    private int xp = 0;
    private int armor = 10;
    Random rand = new Random();
    Scanner myObj = new Scanner(System.in);
    HashMap<String, Integer> armorSlots= new HashMap<>();
    HashMap<String, String> potions= new HashMap<>();


    public player(String name){
        this.name = name;
        armorSlots.put("head",0);
        armorSlots.put("chest",0);
        armorSlots.put("leg",0);
        armorSlots.put("boot",0);
        armorSlots.put("offhand",0);

    }



    
    public int dice(int size){
        return rand.nextInt(size)+1; 
    }
    
    public void attack(){
        if(dice(20) > monster.getArmor()){
            int damage = dice(20);
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
    
    public void addArmor(String armor,int value){
        boolean cycle = true;
        if(armorSlots.get(armor) == 0){
            armorSlots.put(armor,value);
            System.out.println("Your "+armor+"-piece has been replaced");
        }
        else{
            do{
                System.out.println("Would you like to replace your current item: "+armor+", Armor:"+armorSlots.get(armor));
                System.out.println("Yes or No:");
                String choice = myObj.nextLine();
                if(choice.equals("Y") || choice.equals("y") || choice.equals("Yes") || choice.equals("yes")){
                    armorSlots.put(armor,value);
                    System.out.println("Your "+armor+"-piece has been replaced");
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


    public String getName() {
        return this.name;
    }


    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDmgMod() {
        return this.dmgMod;
    }

    public void setDmgMod(int dmgMod) {
        this.dmgMod = dmgMod;
    }

    public int getHitMod() {
        return this.hitMod;
    }

    public void setHitMod(int hitMod) {
        this.hitMod = hitMod;
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
    }

    public int getArmor() {
        return this.armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
    


    

}
