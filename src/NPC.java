import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class NPC implements Serializable {
    private String name;
    private Quest availableQuest;

    public NPC(String name, Quest quest) {
        this.name = name;
        this.availableQuest = quest;
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
