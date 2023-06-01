import java.io.Serializable;
import java.util.Random;

/**
 * The NPC class implements NPCs that give quests to the player.
 * Unlike enemies and players, these are not mobs and can't be attacked.
 * @author Bayasgalan Battogtokh
 */

class NPC implements Serializable {
    /** The NPC's name */
    private String name;

    /** The NPC's quest */
    private Quest availableQuest;

    /**
     * The NPC constructor.
     * @param name The NPC's name.
     * @param quest The NPC's quest.
     */
    private NPC(String name, Quest quest) {
        this.name = name;
        this.availableQuest = quest;
    }

    /**
     * Creates a new NPC with a given name and a quest.
     * @param name The NPC's name
     * @return
     */
    public static NPC createNPC(String name){
        return new NPC(name, Quest.createQuest());
    }

    /**
     * Getter for the NPC's name.
     * @return The NPC's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the NPC's quest.
     * @return The NPC's quest.
     */
    public Quest getAvailableQuest() {
        return availableQuest;
    }

    /**
     * Gives an NPC's quest to the player.
     * @param player A reference to the player object.
     */
    public void giveQuest(Player player) {
        player.addToQuests(getAvailableQuest());
        availableQuest = null;
    }

}
