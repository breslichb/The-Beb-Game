/**
 * A room subtype that initializes with an NPC.
 */
import java.util.Random;

public class NPCRoom extends Room{
    public NPCRoom(GameMap map) {
        super(map);
        addNPC(generateRandomNPC());
    }

    private NPC generateRandomNPC() {
        Random random = new Random();
        String[] names = {"James", "Amy", "David", "Jessica", "George", "Hana" };
        String name = names[random.nextInt(names.length)];
        int level = random.nextInt(10) + 1;

        return NPC.createNPC(name, level);
    }
}
