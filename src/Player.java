import java.util.ArrayList;
import java.util.List;

class Player {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private List<String> inventory;
    private List<Quest> activeQuests;
    private Mobs lastKilledEnemy;

    public void setLastKilledEnemy(Mobs enemy) {
        this.lastKilledEnemy = enemy;
    }

    public Mobs getLastKilledEnemy() {
        return lastKilledEnemy;
    }

    public Player(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.inventory = new ArrayList<>();
        this.activeQuests = new ArrayList<>();
    }
  /*
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
    }*/

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public void takeDamage(int damage) {
        int remainingHealth = Math.max(health - damage, 0);
        health = remainingHealth;
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
        // TODO Auto-generated method stub
        return null;
    }

    public String getKillCount() {
        // TODO Auto-generated method stub
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