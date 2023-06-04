import java.util.Random;

/***
 * Subclass of Equipable. While this class doesn't add much,
 * it has a specific hit() method that will be different from
 * Weapon hit() method.
 *
 * @author Elijah Johnson
 * @version 1.0
 * @since 6.3.2023
 */
public class Armor extends Equipable {
    /***
     * The Constructor for an Armor object.
     * @param name name for the Item
     * @param desc description for the Item
     * @param size "size" or "weight" for the Item
     * @param slot which slot the Item will equip to
     * @param mods the modifiers for the Player's stats. In order: Max HP, STR, DEF, LUCK
     */
    public Armor(String name, String desc, int size, Slot slot, int[] mods){
        super(name, desc, size, slot, mods);
    }

    /**
     * Creates a randomly-generated armor piece.
     * @return The created armor piece.
     */
    public static Armor createArmor(){
        Random rand = new Random();
        int HP=0;
        if(rand.nextBoolean()){HP = rand.nextInt(10)+1;}

        Equipable.Slot slot = null;
        int STR=0;
        int DEF=0;
        int size=0;
        String name="";
        int slotNum = rand.nextInt(4)+1;
        switch(slotNum){
            case 1:
                slot = Equipable.Slot.HEAD;
                STR = 0;
                DEF = rand.nextInt(5)+1;
                size = rand.nextInt(6)+5;
                name = generateRandomHelmets();
                break;
            case 2:
                slot = Equipable.Slot.CHEST;
                STR = rand.nextInt(3);
                DEF = rand.nextInt(10)+1;
                size = rand.nextInt(11)+10;
              //  name = "Chestplate";
                name = generateRandomChestplates();
                break;
            case 3:
                slot = Equipable.Slot.LEGS;
                STR = 0;
                DEF = rand.nextInt(3)+1;
                size = rand.nextInt(4)+3;
                name = generateRandomBoots();
                break;
            case 4:
                slot = Equipable.Slot.ARMS;
                STR = rand.nextInt(5)+1;
                DEF = rand.nextInt(5)+1;
                size = rand.nextInt(4)+3;
                name = generateRandomGauntlets();
                break;
        }

        int[] mods = new int[]{HP, STR, DEF};

        return new Armor(name, "An armor piece.", size, slot, mods);
    }

    /**
     * A method to be called to generate a random name for a helmet.
     * @return String, the name of the Helmet.
     */
    private static String generateRandomHelmets() {
        String[] descpt = {"Strong", "Shinny", "Heavy", "Durable", "Normal"};
        String[] materials = {"Iron", "Wooden", "Bronze", "Diamond", "Golden"};
        String[] types = {"Helmet", "Bascinet ", "Hat"};
        Random rand = new Random();
        String adjective = descpt[rand.nextInt(descpt.length)];
        String material = materials[rand.nextInt(materials.length)];
        String type = types[rand.nextInt(types.length)];
        return adjective + " " + material + " " + type;
    }
    /**
     * A method to be called to generate a random name for a chestplate.
     * @return String, the name of the chestplate.
     */
    private static String generateRandomChestplates() {
        String[] descpt = {"Strong", "Shinny", "Heavy", "Durable", "Normal"};
        String[] materials = {"Iron", "Wooden", "Bronze", "Diamond", "Golden"};
        String[] types = {"Chestplate", "Armor", "Aegis"};
        Random rand = new Random();
        String adjective = descpt[rand.nextInt(descpt.length)];
        String material = materials[rand.nextInt(materials.length)];
        String type = types[rand.nextInt(types.length)];
        return adjective + " " + material + " " + type;
    }
    /**
     * A method to be called to generate a random name for boots.
     * @return String, the name of the boots.
     */
    private static String generateRandomBoots() {
        String[] descpt = {"Strong", "Shinny", "Heavy", "Durable", "Normal"};
        String[] materials = {"Iron", "Wooden", "Bronze", "Diamond", "Golden"};
        String[] types = {"Boots", "Sabaton ", "Sandal"};
        Random rand = new Random();
        String adjective = descpt[rand.nextInt(descpt.length)];
        String material = materials[rand.nextInt(materials.length)];
        String type = types[rand.nextInt(types.length)];
        return adjective + " " + material + " " + type;
    }
    /**
     * A method to be called to generate a random name for gauntlets.
     * @return String, the name of the gauntlets.
     */
    private static String generateRandomGauntlets() {
        String[] descpt = {"Strong", "Shinny", "Heavy", "Durable", "Normal"};
        String[] materials = {"Iron", "Wooden", "Bronze", "Diamond", "Golden"};
        String[] types = {"Gauntlets", "Gloves", "Mitten"};
        Random rand = new Random();
        String adjective = descpt[rand.nextInt(descpt.length)];
        String material = materials[rand.nextInt(materials.length)];
        String type = types[rand.nextInt(types.length)];
        return adjective + " " + material + " " + type;
    }
}
