/***
 * Subclass of Item. Consumables will consist of items
 * like Potions or Food that the Player will 'consume'
 * to evoke an effect.
 *
 * @author Elijah Johnson
 * @version 1.1
 * @since 5.13.2023
 */
public class Consumable extends Item {

    /**The uses available for the Consumable*/
    private int uses;
    /**the numerical effect on whatever stat the consumable affects*/
    private int effect;

    /***
     * The constructor for a Consumable object.
     * @param name name of the Consumable
     * @param desc description of the Consumable
     * @param size "size" or "weight" of the Consumable
     * @param uses how many uses the Consumable has
     * @param effect numerical effect on a stat
     */
    public Consumable(String name, String desc, int size, int uses, int effect){
        super(name, desc, size);
        this.uses = uses;
        this.effect = effect;
    }

    /***
     * Method to call when the player uses the Consumable. Will reduce uses by 1.
     */
    public void use(){
        uses--;
    }

    /***
     * Getter to retrieve how many uses the Consumable has left.
     * @return uses left on the Consumable
     */
    public int getUses(){
        return uses;
    }

    /***
     * Getter method to retrieve the effect on what stat the Consumable will affect.
     * @return how many points the stat will be increased/restored
     */
    public int getEffect(){return effect;}
}
