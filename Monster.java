import java.util.Random;

public class Monster{
    private String name;
    private int xp;
    private int attack;
    private int level;
    private int hp;
    private int armour = 10;
    private int hitMod;
    Random rand = new Random();
    private player player;
    
    private int d(int size){return rand.nextInt(size) + 1;}


    public Monster(){}

    public Monster(String name, int level){
        this.name = name;
        this.level = level;

        switch (name){
            case "Goblin":
                this.hp = rand.nextInt(5) + 15;
                this.xp = 100;
                this.attack = d(8);
                this.hitMod = 2;
                for(int i = 1; i < level; ++i){
                    this.hp = this.hp + rand.nextInt(5)+1;
                    this.xp = this.xp + 50;
                    this.armour = this.armour + 1;
                    this.attack = this.attack + 2;
                }
                break;
            
            case "Orc":
                this.hp = rand.nextInt(5) + 20;
                this.xp = 200;
                this.attack = d(10);
                this.hitMod = 3;
                for(int i = 1; i < level; ++i){
                    this.hp = this.hp + rand.nextInt(5)+1;
                    this.xp = this.xp +50;
                    this.armour = this.armour +1;
                    this.attack = this.attack +2;
                }
                break;
            
            case "Ogre":
                this.hp = rand.nextInt(5) + 25;
                this.xp = 300;
                this.hitMod = 3;
                this.attack = d(12);
                for(int i = 1; i < level; ++i){
                    this.hp = this.hp + rand.nextInt(5)+1;
                    this.xp = this.xp +50;
                    this.armour = this.armour + 1;
                    this.attack = this.attack +2;
                }   
                break;     
            default:
                break;       
        }
    }

    public int getHP() {return this.hp;}

    public String getName() {return this.name;}

    public int getXP() {return this.xp;}

    public int getLevel() {return this.level;}

    public int getArmor() {return this.armour;}

    public void setHP(int hp) {this.hp = hp;}

    public void attack(){
        if(d(20)+this.hitMod > player.getArmor()){
            
        }
    }




}