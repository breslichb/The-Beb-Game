import java.util.Random;

/***
 * Subclass of Consumable. Food will only ever
 * restore HP. Their effects and descriptions will be
 * determined in a method within Main.
 *
 * @author Elijah Johnson
 * @version 1.0
 * @since 5.13.2023
 */
public class Food extends Consumable{
    /***
     * Constructor to create a Food object.
     * @param name name of the Food
     * @param desc description of the Food
     * @param size size of the Food
     * @param effect how many HP  the Food will restore
     */
    public Food(String name, String desc, int size, int effect){
        super(name, desc, size, 1, effect);
    }

    /**
     * Creates a randomly-generated food item.
     * @return The created food item.
     */
    public static Food createFood(){
        Random rand = new Random();
        String[] type = new String[]{"Apple", "Bread", "Meat", "Ice Cream"};
        int typeNum = rand.nextInt(4);

        return new Food(type[typeNum], "Some delicious food.", rand.nextInt(3)+1, rand.nextInt(5)+typeNum+1);
    }

}
