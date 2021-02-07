import java.util.*;

public class player {
    
    private String name;
    private int hp = 50;
    private int dmgMod = 0;
    private int hitMod = 0;
    private int maxHp = 50;
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
    ArrayList<Item> potionSlots = new ArrayList<>();

    String[] weapon = {"Broken Sword","0"};

    public player(){}

    public player(String name){
        this.name = name;
    }

    public int dice(int size){
        return rand.nextInt(size)+1;
    }

    public void attack(Monster monster){
        System.out.println("TO HIT: " + hitMod);
        if(dice(20)+hitMod > monster.getArmor()){
            int roll = dice(20);
            int damage = roll+dmgMod;
            System.out.println("ROLL: " + roll + "| MOD: " + dmgMod);
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
            System.out.println("Your '"+armorSlots[row][1]+"' has been replaced with '"+armorName+"'");

            armorSlots[row][1] = armorName;
            armorSlots[row][2] = Integer.toString(value);
            armor = armor + value;
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
                else if(choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("No")){
                    System.out.println("Your "+armor+"-piece has not been replaced");
                    cycle = false;
                }
                else{
                    System.out.println("Invalid input... Try again");
                }
            }while(cycle == true);
        }

    }

    public void addPotions(Item potion, int val){
        boolean cycle = true;
        if(potionSlots.size() < 5){
            potionSlots.add(potion);
            //System.out.println(potion.getName() + " was added to your inventory!");
        }
        else{
            do{
                System.out.println("Would you like to replace one of your potions?");
                int j = 1;
                for (Item i : potionSlots){
                    System.out.print(j+"."+i.getName()+"\n");
                    j++;
                }
                System.out.println("Potion 1,2,3,4,5 or none");
                String choice = myObj.nextLine();
                if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5")){
                    potionSlots.remove(Integer.valueOf(choice)-1);
                    potionSlots.add(potion);
                    cycle = false;
                }
                else if(choice.equals("none") || choice.equals("None")){
                    System.out.println("No potion was replaced...");
                    cycle = false;
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
                cycle = false;
            }
            else if(choice.equals("n") || choice.equals("N") || choice.equals("No") || choice.equals("no")){
                System.out.println("Your weapon was not replaced...");
                cycle = false;
            }
            else{
                System.out.println("Invalid input... Try again");
            }

        }while(cycle == true);
    }

    public void useHealthPotion(){
        boolean cycle = true;
        do{
            int pos = 1;
            for(Item i : potionSlots){
                System.out.print(pos+". "+ i.getName()+ "\n");
                pos++;
            }
            System.out.println(pos + ". back");
            System.out.println("Which potion would you like to use?");
            String choice = myObj.nextLine();
            if(Integer.valueOf(choice) <= potionSlots.size() && Integer.valueOf(choice) > 0){
                int healing = potionSlots.get(Integer.valueOf(choice)-1).getHealing();
                this.setHp(this.getHp() + healing);
                System.out.println("You healed " + healing + " HP!");
                potionSlots.remove(Integer.valueOf(choice)-1);
                cycle = false;
            }
            else if (Integer.valueOf(choice) == potionSlots.size()+1){
                cycle = false;
            }
            else{
                System.out.println("Invalid selection... try again.");
            }
        }while(cycle);
    }

    public ArrayList<Item> getPotionSlots(){
        return potionSlots;
    }

    public void printInventory(){
        int j = 1;
        if(potionSlots.size() == 0){
            System.out.println("Your bag is empty...");
        }
        else{
            System.out.println("You search through your bag...");
            for(Item i : potionSlots){
                System.out.println(j+"."+i.getName());
                j++;
            }
        }
    }

    public int getDamageMod(){
        return dmgMod;
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
        if(this.hp + hp <= maxHp){
            this.hp = hp;
        }
        else{
            this.hp = maxHp;
        }
        if(this.hp <= 0){
            System.out.println("You died...");
        }
    }


    public int getMaxHP() {
        return this.maxHp;
    }

    public void setMaxHP(int hpMod) {
        this.maxHp += hpMod;
    }

    public int getXp() {
        return this.xp;
    }

    public void setXp(int xp) {
        this.xp += xp;
        if(this.xp >= 250 + level*250){
            this.xp = this.xp - (250 + level*250);
            levelUp();
        }
    }

    public void levelUp(){
        level++;
        maxHp += 10;
        hp = maxHp;
        hitMod+=1;

        System.out.println("You leveled up!");
        System.out.println("New Level: " + level +"\n\t Hp: " + hp + "\n\t hitMod: " + hitMod);
    }

}
