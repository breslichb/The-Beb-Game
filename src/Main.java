import java.util.*;
public class Main {

    /*
    Elijah - Hypothetical method layouts for creating Consumables and Equipables

    public static Potion createPotion(){
        Random rand = new Random();
        String[] type = new String[]{"HP", "STR", "DEF", "LUCK"};
        int typeNum = rand.nextInt(4);
        int useSize = rand.nextInt(3)+1;
        int effect = rand.nextInt(9)+2;
        int duration;
        if(typeNum == 0){
            duration=0;
        }else{
            duration = rand.nextInt(5)+1;
        }

        return new Potion(type[typeNum], useSize, effect, duration);
    }

    public static Weapon createWeapon(){
        Random rand = new Random();
        String[] types = new String[]{"Sword", "Axe", "Mace", "Hammer"};
        int typeNum = rand.nextInt(4);
        int[] mods = new int[]{0, rand.nextInt(5)+(typeNum+1), 0, (rand.nextInt(10)+1)-typeNum};
        int size = rand.nextInt(5)+(typeNum+1);
        int durability = rand.nextInt(10)+(typeNum+1);

        return new Weapon(types[typeNum], "A weapon.", size, mods, durability);
    }

    public static Armor createArmor(){
        Random rand = new Random();
        int HP=0;
        if(rand.nextBoolean()){HP = rand.nextInt(10)+1;}

        Equipable.Slot slot = null;
        int STR=0;
        int DEF=0;
        int size=0;
        int durability=0;
        String name="";
        int slotNum = rand.nextInt(4)+1;
        switch(slotNum){
            case 1:
                slot = Equipable.Slot.HEAD;
                STR = 0;
                DEF = rand.nextInt(5)+1;
                size = rand.nextInt(6)+5;
                durability = rand.nextInt(6)+5;
                name = "Helmet";
                break;
            case 2:
                slot = Equipable.Slot.CHEST;
                STR = rand.nextInt(3);
                DEF = rand.nextInt(10)+1;
                size = rand.nextInt(11)+10;
                durability = rand.nextInt(11)+10;
                name = "Chestplate";
                break;
            case 3:
                slot = Equipable.Slot.LEGS;
                STR = 0;
                DEF = rand.nextInt(3)+1;
                size = rand.nextInt(4)+3;
                durability = rand.nextInt(4)+3;
                name = "Boots";
                break;
            case 4:
                slot = Equipable.Slot.ARMS;
                STR = rand.nextInt(5)+1;
                DEF = rand.nextInt(5)+1;
                size = rand.nextInt(4)+3;
                durability = rand.nextInt(4)+3;
                name = "Gauntlets";
                break;
        }

        int[] mods = new int[]{HP, STR, DEF, rand.nextInt(10)+1};

        return new Armor(name, "An armor piece.", size, slot, mods, durability);
    }
    */


    public static void main(String args[]) {
        // Main UI stuff should go here.
    }
}