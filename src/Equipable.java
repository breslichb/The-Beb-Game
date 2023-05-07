public class Equipable extends Item {

    //Find a way to have the Enums available for every class, especially the Driver
    public enum Slot{HEAD, CHEST, LEGS, ARMS, HAND};

    //fields
    private Slot slot;          //determines which armor slot the Equipable will go
    private int[] mods;         //the modifiers for the player's stats
    private int durability;     //how many times the Equipable can be used/hit

    /***
     * The Constructor for an Equipable object.
     * @param name name for the Item
     * @param desc description for the Item
     * @param size "size" or "weight" for the Item
     * @param slot which slot the Item will equip to
     * @param mods the modifiers for the Player's stats
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
     * Method to call when the Player tries to equip an item.
     * @return true if the item will equip, false if the item cannot be equipped
     */
    public boolean equip(){
        //code will come later when things are more fleshed out
        return true;
    }


}
