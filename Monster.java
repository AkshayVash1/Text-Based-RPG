import java.util.Random;

public class Monster{
    private String name;
    private int xp;
    private int attack;
    private int level;
    private int hp;
    private int armour = 10;
    private int toHit;
    
    private int d(int size){return Random().nextint(size) + 1;}


    public Monster(String name, int level){
        this.name = name;
        this.level = level;

        switch (name){
            case "Goblin":
                this.hp = Random().nextInt(5) + 15;
                this.xp = 100;
                this.attack = d(8);
                this.toHit = 2;
                for(int i = 1; i < level; ++i){
                    this.hp = this.hp + Random().nextInt(5)+1;
                    this.xp = this.xp + 50;
                    this.armour = this.armour + 1;
                    this.attack = this.attack + 2;
                }
            break;
            
            case "Orc":
                this.hp = Random().nextInt(5) + 20;
                this.xp = 200;
                this.attack = d(10);
                this.toHit = 3;
                for(int i = 1; i < level; ++i){
                    this.hp = this.hp +Random().nextInt(5)+1;
                    this.xp = this.xp +50;
                    this.armour = this.armour +1;
                    this.attack = this.attack +2;
                }
                break;
            
            case "Ogre":
                this.hp = Random().nextInt(5) + 25;
                this.xp = 300;
                this.toHit = 3;
                this.attack = d(12);
                for(int i = 1; i < level; ++i){
                    this.hp = this.hp + Random().nextInt(5)+1;
                    this.xp = this.xp +50;
                    this.armour = this.armour + 1;
                    this.attack = this.attack +2;
                }                   
        }
    }

    public int getHP() {return this.hp;}

    public String getName() {return this.name;}

    public int getXP() {return this.xp;}

    public int getLevel() {return this.level;}

    public int getArmor() {return this.armour;}

    public void setHP(int hp) {this.hp = hp;}

    




}