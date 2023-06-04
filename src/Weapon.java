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
        String[] types = new String[]{"Sword", "Axe", "Mace", "Hammer"};
        int typeNum = rand.nextInt(4);
        int[] mods = new int[]{0, rand.nextInt(5)+(typeNum+1), 0};
        int size = rand.nextInt(5)+(typeNum+1);

        return new Weapon(types[typeNum], "A weapon.", size, mods);
    }
}
