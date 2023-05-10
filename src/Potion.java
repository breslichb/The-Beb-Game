import java.util.Random;
public class Potion extends Consumable{

    private Random rand = new Random();
    private String[] types = new String[]{"HP", "DEF", "STR", "LUCK"};

    public Potion(){
        super(name(), "desc", 1, 2, 3);
    }

    public static String name(){
        return "hdbakjsfd";
    }



}
