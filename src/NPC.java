import java.util.ArrayList;
import java.util.List;

class NPC {
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

    public void giveQuest(Quest quest) {
        availableQuests.add(quest);
        System.out.println("NPC " + name + " gives you a new quest: " + quest.getName());
    }
}
