import java.util.Random;

/***
 * Subclass of Equipable. While this class doesn't add much,
 * it has a specific hit() method that will be different from
 * Armor hit() method.
 *
 * @author Elijah Johnson
 * @version 1.0
 * @since 6.3.2023
 */
public class Weapon extends Equipable {
    /***
     * The Constructor for a Weapon object.
     * @param name name for the Item
     * @param desc description for the Item
     * @param size "size" or "weight" for the Item
     * @param mods the modifiers for the Player's stats. In order: Max HP, STR, DEF, LUCK
     */
    public Weapon(String name, String desc, int size, int[] mods){
        super(name, desc, size, Slot.HAND, mods);
    }

    /**
     * Creates a randomly generated weapon.
     * @return The created weapon.
     */
    public static Weapon createWeapon(){
        Random rand = new Random();
        String[] materials = new String[]{"Iron", "Wooden", "Bronze", "Diamond", "Golden"};
        String[] types = new String[]{"Sword", "Axe", "Mace", "Hammer"};
        String[] descpt = {"Strong", "Shinny", "Heavy", "Durable", "Normal"};

        int materialNum = rand.nextInt(materials.length);
        int typeNum = rand.nextInt(types.length);
        int descriptorNum = rand.nextInt(descpt.length);

        int[] mods = new int[]{0, rand.nextInt(5) + (typeNum + 1), 0};
        int size = rand.nextInt(5) + (typeNum + 1);

        String name = descpt[descriptorNum] + " " + materials[materialNum] + " " + types[typeNum];
        String desc = "A " + descpt[descriptorNum] + ", " + materials[materialNum] + " " + types[typeNum].toLowerCase() + " weapon.";

        return new Weapon(name, desc, size, mods);
    }
}
