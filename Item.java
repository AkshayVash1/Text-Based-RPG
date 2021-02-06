/*A*/
import java.util.Random;

public class Item {

    Random rand = new Random();
    private String name;
    private String description;
    private int damage;
    private int damageMod;
    private int ACMod;
    private int healing;
    private int slot;
    private int AC;


    private String type;

    private String quality; /*{"Lesser", "", "Greater" };*/


    /*
    public String weapon_type; /*{"Sword", "Greatsword"};

    public String magic_type; {"Potion of Healing"};

    public String armor_type;  {"Head", "Chest", "Legs", "Boot", "Shield"};
    */
    public Item (String name, String quality){
        this.name = name;
        this.quality = quality;
        int mod = 1;

        switch(quality){
            case "Lesser":
                mod = 2;
                break;
            case "Normal":
                mod = 3;
                break;
            case "Greater":
                mod = 5;
                break;
            default:
                break;
        }

        this.healing = 10 * mod;
    }
    public Item (String name, String type, String quality) {
        this.name = name;
        this.type = type;
        this.quality = quality;
        this.AC = 0;

        switch (type){

            case "Sword":
                this.damage = 1;
                setDamageMod(quality);
                break;

            case "Greatsword":
                this.damage = 3;
                setDamageMod(quality);
                break;

            case "Head":
                this.AC = 0;
                setAC(quality);
                slot = 0;
                break;

            case "Chest":
                this.AC = 0;
                setAC(quality);
                slot = 1;
                break;

            case "Legs":
                this.AC = 0;
                setAC(quality);
                slot = 2;
                break;

            case "Boots":
                this.AC = 0;
                setAC(quality);
                slot = 3;
                break;

            case "Shield":
                this.AC = 0;
                setAC(quality);
                slot = 4;
                break;

            default:
                break;
        }
    }

private void setAC(String quality){
    switch(quality){
        case "Worn":
            ACMod = 1;
            break;
        case "Normal":
            ACMod = 2;
            break;
        case "Pristine":
            ACMod = 3;
            break;
    }
    this.AC = this.AC + ACMod;
}

private void setDamageMod(String quality){
    switch(quality){
        case "Worn":
            damageMod = 1;
            break;
        case "Normal":
            damageMod = 2;
            break;
        case "Pristine":
            damageMod = 3;
            break;
    }
    this.damage = this.damage + damageMod;
}

    public int getSlot(){ return slot;}

    public String getType(){ return type; }

    public int getAC() {return this.AC;}

    public String getName() {return this.name;}

    public int getHealing() {return this.healing;}

    public int getDamageMod() {return this.damage;}

}
