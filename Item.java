import java.util.Random;

public class Item {

    Random rand = new Random();
    public String name;
    public String description;
    public int hitMod;
    public int healing;
    public int AC;
    public int AC_h;
    public int AC_c;
    public int AC_l;
    public int AC_b;
    public int AC_s;

    
    public String type;

    public String quality; /*{"Lesser", "", "Greater" };*/


    /*
    public String weapon_type; /*{"Sword", "Greatsword"};

    public String magic_type; {"Potion of Healing"};

    public String armor_type;  {"Head", "Chest", "Legs", "Boot", "Shield"};
    */
    
    public Item (String name, String type, String quality) {
        this.name = name;
        this.type = type;
        this.quality = quality;
        this.AC = 0;

        switch (type){

            default:
                break;
            
            case "Sword":
                this.hitMod = 0;
                break;

            case "Greatsword":
                this.hitMod = 0;
                break;

            case "Potion of Healing":
                this.healing = 0;
                break;

            case "Head":
                this.AC_h = 0;
                break;

            case "Chest":
                this.AC_c = 0;
                break;

            case "Legs":
                this.AC_l = 0;
                break;

            case "Boots":
                this.AC_b = 0;
                break;

            case "Shield":
                this.AC_s = 0;
                break;
        }

    } 

/*
    public String main_drop() {
        
        switch (type)
            default:
                break:

            case "Weapon":
                this.quality = this.quality[rand(len(quality))];
                this.weapon_type = this.weapon_type[rand(len(weapon_type))];
                
                if (this.weapon_type == "Sword") {
                    
                    if (this.quality == "Lesser") {
                        this.hitMod = 1;
                    }
                    if (this.quality == "") {
                        this.hitMod = 2;
                    }
                    if (this.quality == "Greater") {
                        this.hitMod = 3;
                    }
                }

                if (this.weapon_type == "Greatsword") {
                    
                    if (this.quality == "Lesser") {
                        this.hitMod = 2;
                    }
                    if (this.quality == "") {
                        this.hitMod = 4;
                    }
                    if (this.quality == "Greater") {
                        this.hitMod = 6;
                    }
                }

                name = this.quality + this.weapon_type;

                break;

            case "Magic":
                this.quality = this.quality[rand(len(quality))];
                this.magic_type = this.magic_type[rand(len(magic_type))];
                
                if (this.magic_type == "Potion of Healing"){

                    if (this.quality == "Lesser") {
                        this.healing = dice(11);
                    }
                    if (this.quality == "") {
                        this.healing = dice(16);
                    }
                    if (this.quality == "Greater") {
                        this.healing = dice(21);
                    }
                }

                name = this.quality + this.magic_type;

                break;

            case "Armor":
                this.quality = this.quality[rand(len(quality))];
                this.armor_type = this.armor_type[rand(len(armor_type))]

                if (this.armor_type == "Head") {
                    
                    if (this.quality == "Lesser") {
                        this.AC_h = 1;
                    }
                    if (this.quality == "") {
                        this.AC_h = 2;
                    }
                    if (this.quality == "Greater") {
                        this.AC_h = 3;
                    }
                }

                if (this.armor_type == "Chest") {
                    
                    if (this.quality == "Lesser") {
                        this.AC_c = 1;
                    }
                    if (this.quality == "") {
                        this.AC_c = 2;
                    }
                    if (this.quality == "Greater") {
                        this.AC_c = 3;
                    }
                }

                if (this.armor_type == "Legs") {
                    
                    if (this.quality == "Lesser") {
                        this.AC_l = 1;
                    }
                    if (this.quality == "") {
                        this.AC_l = 2;
                    }
                    if (this.quality == "Greater") {
                        this.AC_l = 3;
                    }
                }

                if (this.armor_type == "Boot") {
                    
                    if (this.quality == "Lesser") {
                        this.AC_b = 1;
                    }
                    if (this.quality == "") {
                        this.AC_b = 2;
                    }
                    if (this.quality == "Greater") {
                        this.AC_b = 3;
                    }
                }

                if (this.armor_type == "Shield") {
                    
                    if (this.quality == "Lesser") {
                        this.AC_s = 1;
                    }
                    if (this.quality == "") {
                        this.AC_s = 2;
                    }
                    if (this.quality == "Greater") {
                        this.AC_s = 3;
                    }
                }

                this.AC = this.AC_h + this.AC_c + this.AC_l + this.AC_b + this.AC_s;

                name = this.quality + this.armor_type;

                    break;

        return name;
                    
    }
*/
                
    public int getAC() {return this.AC;}

    public String getName() {return this.name;}

    public int getHealing() {return this.healing;}

    public int hitMod() {return this.hitMod;}

}