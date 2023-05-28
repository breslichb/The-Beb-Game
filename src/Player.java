import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

class Player extends Mobs implements Serializable {

    private List<Item> inventory;
    private List<Quest> activeQuests;
    private Mobs lastKilledEnemy;
    private Armor equippedArmor;
    private int maxHealth;

    private Player(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
        this.maxHealth = health;
        this.inventory = new ArrayList<>();
        this.activeQuests = new ArrayList<>();
        this.equippedArmor = null;
    }

    public static Player createPlayer(String name) {
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

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public List<Item> getInventory(){return inventory;}

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

    //armor stuff
    public void equipArmor(Armor armor) {
        if (equippedArmor != null) {
            unequipArmor();
        }
        equippedArmor = armor;
        System.out.println("Equipped " + armor.getName() + " armor.");
    }

    public void unequipArmor() {
        if (equippedArmor != null) {
            System.out.println("Unequipped " + equippedArmor.getName() + " armor.");
            equippedArmor = null;
        }
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public boolean hasEquippedArmor() {
        return equippedArmor != null;
    }

    //talk
    public void talk(NPC npc) {
        npc.giveQuest(this);
    }

    // the health ratio
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
    }
}
