public class Consumable extends Item {

    //fields
    private int uses;   //the uses available for the Consumable

    /***
     * The constructor for a Consumable object.
     * @param name name of the Consumable
     * @param desc description of the Consumable
     * @param size "size" or "weight" of the Consumable
     * @param uses how many uses the Consumable has
     */
    public Consumable(String name, String desc, int size, int uses){
        super(name, desc, size);
        this.uses = uses;
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
}
