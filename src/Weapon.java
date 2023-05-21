/***
 * Subclass of Equipable. While this class doesn't add much,
 * it has a specific hit() method that will be different from
 * Armor hit() method.
 *
 * @author Elijah Johnson
 * @version 1.0
 * @since 5.17.2023
 */
public class Weapon extends Equipable {
    /***
     * The Constructor for a Weapon object.
     * @param name name for the Item
     * @param desc description for the Item
     * @param size "size" or "weight" for the Item
     * @param mods the modifiers for the Player's stats. In order: Max HP, STR, DEF, LUCK
     * @param durability the number of uses or hits the Item has
     */
    public Weapon(String name, String desc, int size, int[] mods, int durability){
        super(name, desc, size, Slot.HAND, mods, durability);
    }

    /***
     * Method to be called when the Player attempts to hit an enemy.
     * @param playerAcc the Player's ACC stat
     * @param playerStr the Player's STR stat
     * @param enemyAvo  the Enemy's AVO stat
     * @return the damage dealt to the enemy
     */
    public int hit(int playerAcc, int playerStr, int enemyAvo, int enemyDef){

        int hitChance = playerAcc + this.getMods()[3]; //Player ACC stat + Weapon LUCK stat
        int damage = 0;

        if(hitChance > enemyAvo){                                   //hits if hitChance is greater than enemyAvo
            damage = playerStr + this.getMods()[1] - enemyDef;      //Player STR stat + Weapon STR stat - enemyDef
            this.setDurability(this.getDurability()-1);             //Reduce durability
        }

        return damage;
    }
}
