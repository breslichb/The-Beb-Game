/***
 * <h1>The Item Superclass</h1>
 *
 * This is the Superclass used for all items within The BEB Game.
 *
 * @Author Elijah Johnson
 * @version 1.0
 * @since 5.6.2023
 */
import java.io.Serializable;
public class Item implements Serializable {

    //Fields
    private String name;    //The name of the Item
    private String desc;    //The description of the Item
    private int size;       //The "size" or "weight" of the Item

    /***
     * Default Constructor
     */
    public Item(){}
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
