import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Room class is a representation of one in-game room.
 * This stores NPCs, enemies, items, etc. that can be encountered in the room.
 * Each room has a unique ID that is incremented as more rooms are added.
 * @author Benjamin Breslich
 */
public abstract class Room implements Serializable {
    /** The map we reside in */
    protected GameMap parentMap;

    /** Our room ID */
    protected Integer roomID;

    /** The current number of rooms in existence. */
    private static Integer numRooms = 0;

    /** An arraylist of the items currently present in the room. */
    private ArrayList<Item> items;

    /** An arraylist of the enemies currently present in the room. Usually just one.*/
    private ArrayList<Enemy> enemies;

    /** An arraylist of the quest NPCs currently present in the room. Usually just one. */
    private ArrayList<NPC> npcs;

    /** A flavor-text description of the room. This is independent of any enemies/NPCs/items in the area. */
    protected String desc;

    /** The file that contains our descriptions. */
    public static final String descFileName = "room_descriptions.txt";

    /** A list containing all our descs for easy sampling. */
    public static List<String> descFile;

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
     * Returns a string describing the room. Also does things on entry.
     * @return a string describing the interaction.
     */
    public String onEntry(){
        String retString = "You enter " + desc + ".";
        if(items.size() > 0) {
            for(Item i : items) {
                retString += "\nThere is a " + i.getName() + " inside.";
            }
        }
        if(enemies.size() > 0) {
            retString += "\nThere is a hostile " + enemies.get(0).getName() + " inside.";
        }
        if(npcs.size() > 0) {
            retString += "\nYou see " + npcs.get(0).getName() + " inside.";
        }
        return retString + "\n";
    }

    /**
     * Allows a player to interact with the room itself.
     * @return a string describing the interaction.
     */
    public String interact(){
        if(items.size() > 0) {
            Item i = items.get(0);
            parentMap.getPlayer().addToInventory(i);
            removeItem(i);
            return "You pick up " + i.getName() + ".";
        }
        return "You don't see anything particularly interesting you can do with this room.";
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
    private String getRandomDesc() {
        try {
            descFile = Files.readAllLines(Paths.get(descFileName));
            // Get a random position
            Random rand = new Random();
            return descFile.get(rand.nextInt(descFile.size()));
        } catch (IOException e) {
            // Default in case the IO isn't set up properly
            System.out.println("IO Error in Description Handling: " + e.toString());
            return "This room is simple and uninteresting.";
        }
    }
}
