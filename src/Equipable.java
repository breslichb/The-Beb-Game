/***
 * Subclass of Item. Equipables will be items the
 * Player can 'equip' on their character to improve stats
 * like Defense and Strength. This consists of Armor and Weapons.
 *
 * @author Elijah Johnson
 * @version 1.0
 * @since 6.3.2023
 */
public class Equipable extends Item {

    /**enum to be used for equipping*/
    public enum Slot{HEAD, CHEST, LEGS, ARMS, HAND};

    /**determines which armor slot the Equipable will go*/
    private Slot slot;

    /**the modifiers for the player's stats, in order: Max HP, STR, DEF, LUCK*/
    private int[] mods;

    /**if the armor is equipped or not*/
    public boolean isEquipped = false;

    /***
     * The Constructor for an Equipable object.
     * @param name name for the Item
     * @param desc description for the Item
     * @param size "size" or "weight" for the Item
     * @param slot which slot the Item will equip to
     * @param mods the modifiers for the Player's stats. In order: Max HP, STR, DEF, LUCK
     */
    public Equipable(String name, String desc, int size, Slot slot, int[] mods){
        super(name, desc, size);
        this.slot = slot;
        this.mods = mods;
    }

    /***
     * Getter to retrieve which slot the Item will equip to.
     * @return the value of the slot field
     */
    public Slot getSlot(){
        return slot;
    }

    /***
     * Getter to retrieve the modifiers of the Item.
     * @return the mods array
     */
    public int[] getMods(){
        return mods;
    }
}
