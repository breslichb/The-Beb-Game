import java.util.Random;

/***
 * Subclass of Consumable. Potions will temporarily
 * increase a stat or restore HP. Within the driver class,
 * Potion stats will be randomly generated with a method.
 *
 * @author Elijah Johnson
 * @version 1.0
 * @since 5.13.2023
 */
public class Potion extends Consumable{

    /**How long the Potion effect lasts*/
    private int duration;

    /***
     * Constructor for a Potion.
     * @param type the type of Potion, will be one of 4 stats: HP, STR, DEF, or LUCK.
     * @param useSize the size of the Potion as well as how many uses it has
     * @param effect the amount the corresponding stat will increase/be restored
     * @param duration how long the effect will last - HP Potions will have unlimited effect
     */
    public Potion(String type, int useSize, int effect, int duration){
        super(type+" Potion", "A Magic Potion that will increase/restore your "+type+" Stat.", useSize, useSize, effect);
        this.duration=duration;
    }

    /***
     * Getter method to retrieve how long the Potion effect lasts
     * @return how long the effect lasts
     */
    public int getDuration(){return duration;}

    /**
     * Creates a randomly generated potion.
     * @return The created potion.
     */
    public static Potion createPotion(){
        Random rand = new Random();
        String[] type = new String[]{"HP", "STR", "DEF", "LUCK"};
        int typeNum = rand.nextInt(4);
        int useSize = rand.nextInt(3)+1;
        int effect = rand.nextInt(9)+2;
        int duration;
        if(typeNum == 0){
            duration=0;
        }else{
            duration = rand.nextInt(5)+1;
        }

        return new Potion(type[typeNum], useSize, effect, duration);
    }
}
