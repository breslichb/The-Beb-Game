import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class NPC implements Serializable {
    private String name;
    private List<Quest> availableQuests;

    public NPC(String name) {
        this.name = name;
        this.availableQuests = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Quest> getAvailableQuests() {
        return availableQuests;
    }
    public static int generateRandomStat(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public void giveQuest(Quest quest) {
        availableQuests.add(quest);
        System.out.println("NPC " + name + " gives you a new quest: " + quest.getName());
    }
    //dialogue method for player class
    public void dialogue() {
        System.out.println(getName() + " talking");
    }
}
