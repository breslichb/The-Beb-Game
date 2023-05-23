import java.util.Random;

/***
 * Subclass of Equipable. While this class doesn't add much,
 * it has a specific hit() method that will be different from
 * Weapon hit() method.
 *
 * @author Elijah Johnson
 * @version 1.0
 * @since 5.17.2023
 */
public class Armor extends Equipable {
    /***
     * The Constructor for an Armor object.
     * @param name name for the Item
     * @param desc description for the Item
     * @param size "size" or "weight" for the Item
     * @param slot which slot the Item will equip to
     * @param mods the modifiers for the Player's stats. In order: Max HP, STR, DEF, LUCK
     * @param durability the number of uses or hits the Item has
     */
    public Armor(String name, String desc, int size, Slot slot, int[] mods, int durability){
        super(name, desc, size, slot, mods, durability);
    }

    /***
     * Method to call when the Player is hit by an enemy
     * @param playerAvo Player's AVO stat
     * @param enemyHit  Enemy's ACC stat + Enemy's Weapon LUCK stat
     * @return boolean whether the enemy hit (true) or missed (false)
     */
    public boolean hit(int playerAvo, int enemyHit){
        boolean hit = false;
        int missChance = playerAvo + this.getMods()[3];      //Player AVO stat + Armor LUCK stat
        if(enemyHit > missChance) {                          //Hits if enemyHit is greater than missChance
            hit = true;
            this.setDurability(this.getDurability()-1);      //reduce durability
        }
        return hit;
    }

    /**
     * Creates a randomly-generated armor piece.
     * @return The created armor piece.
     */
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
}