/**
 * The Room class is a representation of one in-game room.
 * This stores NPCs, enemies, items, etc. that can be encountered in the room.
 * Each room has a unique ID that is incremented as more rooms are added.
 */
public class Room {
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

    /** An arraylist of the quest NPCs currently present in the room. Also usually just one. */
    private ArrayList<NPC> npcs;

    /** A flavor-text description of the room. This is independent of any enemies/NPCs/items in the area. */
    private String desc;

    /**
     * Makes a basic room object. This is called by the game map.
     * Type-based generation is handled by subtypes.
     * @param parent Our game map, used for sync purposes.
     */
    private Room(GameMap parent) {
        // Get our room ID
        roomID = numRooms;
        numRooms++;

        // Init map linkage
        parentMap = parent;
    }
}
