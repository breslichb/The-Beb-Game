import java.util.Random;

/***
 * Subclass of Consumable. Potions will temporarily
 * increase a stat or restore HP. Within the driver class,
 * Potion stats will be randomly generated with a method.
 *
 * @author Elijah Johnson
 * @version 1.0
 * @since 6.3.2023
 */
public class Potion extends Consumable{

    /***
     * Constructor for a Potion.
     * @param type the type of Potion, will be one of 4 stats: HP, STR, DEF.
     * @param useSize the size of the Potion as well as how many uses it has
     * @param effect the amount the corresponding stat will increase/be restored
     */
    public Potion(String type, int useSize, int effect){
        super(type+" Potion", "A Magic Potion that will increase/restore your "+type+" Stat.", useSize, useSize, effect);
    }

    /**
     * Creates a randomly generated potion.
     * @return The created potion.
     */
    public static Potion createPotion(){
        Random rand = new Random();
        String[] type = new String[]{"HP", "STR", "DEF"};
        int typeNum = rand.nextInt(3);
        int useSize = rand.nextInt(3)+1;
        int effect = rand.nextInt(9)+2;

        return new Potion(type[typeNum], useSize, effect);
    }
}
