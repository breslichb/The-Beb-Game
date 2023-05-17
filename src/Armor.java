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
}