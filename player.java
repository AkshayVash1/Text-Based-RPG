import java.util.*;

public class player {
    
    private String name;
    private int hp = 50;
    private int dmgMod = 0;
    private int hitMod = 0;
    private int hpMod = 0;
    private int xp = 0;
    private int armor = 10;
    
   
    
    public int d20(){
        return (int)Math.random() * (20-1+1)+1;
    }
    
    public void attack(){
        if(d20() > monster.getArmor()){
            int damage = d20();
            System.out.println("You struck the "+monster.getName()+" for "+damage+" damage!");
            monster.takeDamage(damage);
        }
        else{
            System.out.println("The monster evaded your attack!");
        }

    }
    
    public void takeDamage(int dmg){
        this.hp = hp - dmg;

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

    public player(String name){
        this.name = name;


    }

    public int getArmor() {
        return this.armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
    


    

}
