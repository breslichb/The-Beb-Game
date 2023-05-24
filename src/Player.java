import java.util.Random;
import java.util.ArrayList;
import java.util.List;

class Player extends Mobs {

    private List<String> inventory;
    private List<Quest> activeQuests;
    private Mobs lastKilledEnemy;

    private Player(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);

        this.inventory = new ArrayList<>();
        this.activeQuests = new ArrayList<>();
    }

    public static Player createPlayer() {
        String name = "Player"; // Default name
        // Generate random player stats
        int health = generateRandomStat(100, 150);
        int attack = generateRandomStat(10, 20);
        int defense = generateRandomStat(10, 20);

        return new Player(name, health, attack, defense);
    }
    private static int generateRandomStat(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public void setLastKilledEnemy(Mobs enemy) {
        this.lastKilledEnemy = enemy;
    }

    public Mobs getLastKilledEnemy() {
        return lastKilledEnemy;
    }

    public void addToInventory(String item) {
        inventory.add(item);
    }

    public int getAmuletCount() {
        int count = 0;
        for (String item : inventory) {
            if (item.equals("Amulet of Power")) {
                count++;
            }
        }
        return count;
    }

    public Quest[] getActiveQuests() {
        return activeQuests.toArray(new Quest[0]);
    }

    public String getKillCount() {
        return null;
    }

    public void addToQuests(Quest quest) {
        activeQuests.add(quest);
    }

    public void completeQuest(Quest quest) {
        activeQuests.remove(quest);
        System.out.println("Congratulations! You have completed the quest: " + quest.getName());
    }

    public boolean hasActiveQuests() {
        return !activeQuests.isEmpty();
    }

    public void checkActiveQuests() {
        if (activeQuests.isEmpty()) {
            System.out.println("You have no active quests.");
        } else {
            System.out.println("Active quests:");
            for (Quest quest : activeQuests) {
                System.out.println(quest.getName() + ": " + quest.getDescription());
            }
        }
    }


}
