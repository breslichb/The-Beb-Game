import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Room class is a representation of one in-game room.
 * This stores NPCs, enemies, items, etc. that can be encountered in the room.
 * Each room has a unique ID that is incremented as more rooms are added.
 */
public abstract class Room implements Serializable {
    /** The map we reside in */
    private GameMap parentMap;

    /** Our room ID */
    private Integer roomID;

    /** The current number of rooms in existence. This is TRANSIENT and is not stored during DB save,
     * instead being calculated after DB load and game init. */
    private static transient Integer numRooms = 0;

    /** An arraylist of the items currently present in the room. */
    private ArrayList<Item> items;

    /** An arraylist of the enemies currently present in the room. Usually just one.*/
    private ArrayList<Enemy> enemies;

    /** An arraylist of the quest NPCs currently present in the room. Usually just one. */
    private ArrayList<NPC> npcs;

    /** A flavor-text description of the room. This is independent of any enemies/NPCs/items in the area. */
    protected String desc;

    /** The file that contains our descriptions. */
    public static final String descFile = "room_descriptions.txt";

    /**
     * Makes a basic room object. This is called by the game map.
     * Type-based generation is handled by subtypes.
     * @param parent Our game map, used for sync purposes.
     */
    protected Room(GameMap parent) {
        // Init vars
        enemies = new ArrayList<Enemy>();
        npcs = new ArrayList<NPC>();
        items = new ArrayList<Item>();
        desc = getRandomDesc();

        // Get our room ID
        roomID = numRooms;
        numRooms++;

        // Init map linkage
        parentMap = parent;
    }

    /**
     * Description getter
     * @return The room's description.
     */
    public String getDesc(){
        return desc;
    }

    /**
     * Item getter.
     * @return Items in a room.
     */
    public ArrayList<Item> getItems(){return items;}

    /**
     * Used to add an item to a room.
     * @param item The item to add.
     */
    public void addItem(Item item){
        items.add(item);
    }

    /**
     * Used to remove an item from a room.
     * @param item The item to remove.
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Used to get the hostile NPCs in a room.
     * @return The enemies in the room.
     */
    public ArrayList<Enemy> getEnemies() {return enemies;}

    /**
     * Used to add a hostile NPC to a room.
     * @param e The enemy to add.
     */
    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    /**
     * Used to remove a hostile NPC from a room.
     * @param e The enemy to remove.
     */
    public void removeEnemy(Enemy e) {
        enemies.remove(e);
    }

    /**
     * Used to get the NPCs in a room.
     * @return A list of the NPCs in the room.
     */
    public ArrayList<NPC> getNPCs() {return npcs;}

    /**
     * Used to add a non-hostile NPC to a room.
     * @param npc The NPC to add.
     */
    public void addNPC(NPC npc) {
        npcs.add(npc);
    }

    /**
     * Used to remove an NPC from a room.
     * @param npc the NPC to remove.
     */
    public void removeNPC(NPC npc) {
        npcs.remove(npc);
    }

    /**
     * Gets a random description from a provided description file using RandomAccessFile
     * @return the description.
     */
    public String getRandomDesc(){
        try (RandomAccessFile descs = new RandomAccessFile(new File(descFile), "r")) {
            // Get a random position
            Random rand = new Random();
            long descLength = descs.length();
            long pos = rand.nextLong(descLength);
            descs.seek(pos);

            // If pos is 0, we don't need to skip to the next line. Otherwise, we do so to get the beginning of the next desc.
            if(pos != 0) {
                descs.readLine();
            }

            // Read in our desc
            String retDesc = descs.readLine();
            if(retDesc.length() > 0) {
                return descs.readLine();
            } else { // If we hit the end, we get a bland description.
                return "This room is simple and uninteresting.";
            }
        } catch (IOException e) {
            // Default in case the IO isn't set up properly
            System.out.println("IO Error in Description Handling: " + e.toString());
            return "This room is simple and uninteresting.";
        }
    }
}
