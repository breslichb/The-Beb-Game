/**
 * A room subtype that initializes with an NPC.
 */
import java.util.Random;

/**
 * A room containing an NPC.
 * @author Benjamin Breslich
 */
public class NPCRoom extends Room{
    /**
     * The NPCRoom constructor.
     * @param map A reference to the parent game map.
     */
    public NPCRoom(GameMap map) {
        super(map);
        addNPC(generateRandomNPC());
    }

    /**
     * Generates a random NPC to add to the room.
     * @return The generated NPC.
     */
    private NPC generateRandomNPC() {
        Random random = new Random();
        String[] names = {"James", "Amy", "David", "Jessica", "George", "Hana" };
        String name = names[random.nextInt(names.length)];

        return NPC.createNPC(name);
    }
}
