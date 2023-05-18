/***
 * Subclass of Item. Equipables will be items the
 * Player can 'equip' on their character to improve stats
 * like Defense and Strength. This consists of Armor and Weapons.
 *
 * @author Elijah Johnson
 * @version 1.1
 * @since 5.17.2023
 */
public class Equipable extends Item {

    /**enum to be used for equipping*/
    public enum Slot{HEAD, CHEST, LEGS, ARMS, HAND};

    /**determines which armor slot the Equipable will go*/
    private Slot slot;

    /**the modifiers for the player's stats, in order: Max HP, STR, DEF, LUCK*/
    private int[] mods;

    /**how many times the Equipable can be used/hit*/
    private int durability;

    /***
     * The Constructor for an Equipable object.
     * @param name name for the Item
     * @param desc description for the Item
     * @param size "size" or "weight" for the Item
     * @param slot which slot the Item will equip to
     * @param mods the modifiers for the Player's stats. In order: Max HP, STR, DEF, LUCK
     * @param durability the number of uses or hits the Item has
     */
    public Equipable(String name, String desc, int size, Slot slot, int[] mods, int durability){
        super(name, desc, size);
        this.slot = slot;
        this.mods = mods;
        this.durability = durability;
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

    /***
     * Getter to retrieve the durability of the Item.
     * @return the value of the durability field
     */
    public int getDurability(){
        return durability;
    }

    /***
     * Setter to set a new durability - used when a hit is successful
     * @param x the new value of durability
     */
    public void setDurability(int x){
        durability = x;
        if(durability<=0){//discard}
    }

    /***
     * Method to call when the Player tries to equip an item.
     * @return true if the item will equip, false if the item cannot be equipped
     */
    public boolean equip(){
        //code will come later when things are more fleshed out
        return true;
    }
    /***
     * Method to call when the Player tries to unequip an item.
     * @return true if the item will equip, false if the item cannot be equipped
     */
    public boolean unequip(){
        //code will come later when things are more fleshed out
        return true;
    }
}
