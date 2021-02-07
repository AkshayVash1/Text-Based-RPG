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
    gui g;

    String[] weapon = {"Broken Sword","0"};

    public player(){}

    public player(String name, gui g){
        this.name = name;
        this.g = g;
    }

    public int dice(int size){
        return rand.nextInt(size)+1;
    }

    public void attack(Monster monster){
        if(dice(20)+hitMod > monster.getArmor()){
            int roll = dice(20);
            int damage = roll+dmgMod;
            g.getOutput().append("You struck the "+monster.getName()+" for "+damage+" damage!\n\n");
            monster.takeDamage(damage);
        }
        else{
            g.getOutput().append("The monster evaded your attack!\n\n");
        }

    }
    public void takeDamage(int dmg){
        g.getOutput().append("You took "+dmg+" damage!\n\n");
        this.hp = hp - dmg;

    }

    public void addArmor(String armorName,int row, int value){
        boolean cycle = true;
        if(armorSlots[row][2].equals("0")){
            g.getOutput().append("Your '"+armorSlots[row][1]+"' has been replaced with '"+armorName+"'");

            armorSlots[row][1] = armorName;
            armorSlots[row][2] = Integer.toString(value);
            armor = armor + value;
        }
        else{
            do{
                g.getOutput().append("Would you like to replace your current item: "+armorSlots[row][1]+",(Armor Value: "+armorSlots[row][2]+") (Yes or No)");
                String choice = getUserInput(g);
                if(choice.equals("Y") || choice.equals("y") || choice.equals("Yes") || choice.equals("yes")){
                    armor = armor - Integer.valueOf(armorSlots[row][2]);
                    armorSlots[row][1] = armorName;
                    armorSlots[row][2] = Integer.toString(value);
                    armor = armor + value;
                    g.getOutput().append("Your "+armorSlots[row][1]+"-piece has been replaced with"+armorName);
                    cycle = false;
                }
                else if(choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("No")){
                    g.getOutput().append("Your "+armorSlots[row][1]+" has not been replaced");
                    cycle = false;
                }
                else{
                    g.getOutput().append("Invalid input... Try again");
                }
            }while(cycle == true);
        }

    }

    public void addPotions(Item potion, int val){
        boolean cycle = true;
        if(potionSlots.size() < 5){
            potionSlots.add(potion);
        }
        else{
            do{
                g.getOutput().append("Would you like to replace one of your potions?");
                int j = 1;
                for (Item i : potionSlots){
                    g.getOutput().append(j+"."+i.getName()+"\n");
                    j++;
                }
                g.getOutput().append("Potion 1,2,3,4,5 or none");
                String choice = getUserInput(g);
                if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5")){
                    potionSlots.remove(Integer.valueOf(choice)-1);
                    potionSlots.add(potion);
                    cycle = false;
                }
                else if(choice.equals("none") || choice.equals("None")){
                    g.getOutput().append("No potion was replaced...");
                    cycle = false;
                }
                else{
                    g.getOutput().append("Invalid input... Try again");
                }

            }while(cycle == true);
        }

    }

    public void addWeapon(String weaponName, int weaponDmg){
        boolean cycle = true;
        do{
            g.getOutput().append("Would you like to replace your "+weapon[0]+"?   ");
            g.getOutput().append("(Yes or No)\n");
            String choice = getUserInput(g);
            if(choice.equals("y") || choice.equals("Y") || choice.equals("yes") || choice.equals("Yes")){
                g.getOutput().append("Your "+weapon[0]+" was replaced with "+weaponName);
                dmgMod = dmgMod - Integer.valueOf(weapon[1]);
                weapon[0] = weaponName;
                weapon[1] = Integer.toString(weaponDmg);
                dmgMod = dmgMod + weaponDmg;
                cycle = false;
            }
            else if(choice.equals("n") || choice.equals("N") || choice.equals("No") || choice.equals("no")){
                g.getOutput().append("Your weapon was not replaced...");
                cycle = false;
            }
            else{
                g.getOutput().append("Invalid input... Try again");
            }

        }while(cycle == true);
    }

    public void useHealthPotion(){
        boolean cycle = true;
        do{
            int pos = 1;
            for(Item i : potionSlots){
                g.getOutput().append(pos+". "+ i.getName()+ "\n\n");
                pos++;
            }
            g.getOutput().append(pos + ". back\n\n");
            g.getOutput().append("Which potion would you like to use?\n");
            String choice = getUserInput(g);
            if(Integer.valueOf(choice) <= potionSlots.size() && Integer.valueOf(choice) > 0){
                int healing = potionSlots.get(Integer.valueOf(choice)-1).getHealing();
                this.setHp(healing);
                g.getOutput().append("You healed for " + healing + " HP!\n");
                potionSlots.remove(Integer.valueOf(choice)-1);
                cycle = false;
            }
            else if (Integer.valueOf(choice) == potionSlots.size()+1){
                cycle = false;
            }
            else{
                g.getOutput().append("Invalid selection... try again.\n");
            }
        }while(cycle);
    }

    public ArrayList<Item> getPotionSlots(){
        return potionSlots;
    }

    public void printInventory(){
        int j = 1;
        if(potionSlots.size() == 0){
            g.getOutput().append("Your bag is empty...\n");
        }
        else{
            g.getOutput().append("You search through your bag...\n");
            for(Item i : potionSlots){
                g.getOutput().append(j+"."+i.getName()+"\n");
                j++;
            }
        }
    }
    private String getUserInput(gui g) {
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

    public int getLevel(){
        return level;
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
            this.hp = this.hp + hp;
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

        g.getOutput().append("You leveled up!\n");
        g.getOutput().append("New Level: " + level +"\n\t Hp: " + hp + "\n\t hitMod: " + hitMod+"\n");
    }

}
