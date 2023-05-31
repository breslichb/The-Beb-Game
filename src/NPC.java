import java.io.Serializable;
import java.util.Random;

class NPC implements Serializable {
    private String name;
    private Quest availableQuest;

    private NPC(String name, Quest quest) {
        this.name = name;
        this.availableQuest = quest;
    }

    public static NPC createNPC(String name, int level){
        return new NPC(name, Quest.createQuest());
    }

    public String getName() {
        return name;
    }

    public Quest getAvailableQuest() {
        return availableQuest;
    }
    public static int generateRandomStat(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public void giveQuest(Player player) {
        player.addToQuests(getAvailableQuest());
        availableQuest = null;
    }

}
