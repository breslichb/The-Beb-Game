import java.io.Serializable;

/***
 * Main superclass for all Items within The BEB Game.
 *
 * @author Elijah Johnson
 * @version 1.0
 * @since 6.3.2023
 */
public class Item implements Serializable {

    /**The name of the Item*/
    private String name;

    /**The description of the Item*/
    private String desc;

    /**The "size" or "weight" of the Item*/
    private int size;

    /***
     * Constructor for an Item object.
     * @param name The name of the Item.
     * @param desc The description of the Item.
     * @param size The "size" or "weight" of the Item.
     */
    public Item(String name, String desc, int size){
        this.name = name;
        this.desc = desc;
        this.size = size;
    }

    /***
     * Getter method to retrieve the name of the Item.
     * @return the value of the name field.
     */
    public String getName(){
        return name;
    }

    /***
     * Getter method to retrieve the size of the Item.
     * @return the value of the size field.
     */
    public int getSize(){
        return size;
    }

    /***
     * The toString method which will return the description of the Item.
     * @return the value of the desc field.
     */
    public String toString(){
        return desc;
    }
}
