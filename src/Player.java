import jdk.jshell.execution.JdiExecutionControl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * The Player class. Holds information about the player character.
 * @author Bayasgalan Battogtokh
 */
class Player extends Mobs implements Serializable {

    /** The player's inventory, stored as a list */
    private List<Item> inventory;

    /** The number of items the player has */
    private int numItems;

    /** The maximum weight the player can carry */
    private int weightMax;

    /** The current weight the player is carrying */
    private int weightCurr;

    /** The player's active quests, stored as a list */
    private List<Quest> activeQuests;

    /** The player's last killed enemy */
    private Mobs lastKilledEnemy;

    /** The player's equipped items */
    private Equipable[] equippedItems;

    /** The player's max health */
    private int maxHealth;

    /**
     * A detailed player constructor, allowing for direct setting of the player's stats.
     * @param name The player's name.
     * @param health The player's max health.
     * @param attack The player's attack value.
     * @param defense The player's defense value.
     * @param weightMax The player's max weight.
     */
    private Player(String name, int health, int attack, int defense, int weightMax) {
        super(name, health, attack, defense);
        this.maxHealth = health;
        this.inventory = new ArrayList<>();
        numItems = 0;
        this.weightMax = weightMax;
        weightCurr = 0;
        this.activeQuests = new ArrayList<>();
        equippedItems = new Equipable[5];
    }

    /**
     * Creates a player with randomly generated stats, except for the name.
     * @param name The player's name.
     * @return The generated player.
     */
    public static Player createPlayer(String name) {
        // Generate random player stats
        int health = generateRandomStat(100, 150);
        int attack = generateRandomStat(10, 20);
        int defense = generateRandomStat(10, 20);
        int weightMax = generateRandomStat(100, 150);

        return new Player(name, health, attack, defense, weightMax);
    }

    /**
     * Generates a random stat for use by createPlayer.
     * @param min The minimum bound for the stat.
     * @param max The maximum bound for the stat.
     * @return The generated stat.
     */
    private static int generateRandomStat(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Increases max health by a given amount.
     * @param x The amount to increase max HP by.
     */
    public void increaseHP(int x){
        maxHealth += x;
        heal(x);
    }
    public void decreaseHP(int x){
        maxHealth -= x;
        takeDamage(x);
    }

    /**
     * Sets the last killed enemy.
     * @param enemy The enemy to set.
     */
    public void setLastKilledEnemy(Mobs enemy) {
        this.lastKilledEnemy = enemy;
    }

    /**
     * Gets the last killed enemy.
     * @return The last killed enemy.
     */
    public Mobs getLastKilledEnemy() {
        return lastKilledEnemy;
    }

    /**
     * Adds an item to the inventory.
     * @param item The item to add.
     * @return Whether adding the item was successful or not.
     */
    public boolean addToInventory(Item item) {
        if((weightCurr + item.getSize()) > weightMax){
            return false;
        }else{
            inventory.add(item);
            numItems++;
            weightCurr += item.getSize();
            return true;
        }
    }

    /**
     * Removes an item from the inventory.
     * @param item The item to remove.
     * @return Whether the removal was successful or not.
     */
    public boolean removeFromInventory(Item item) {
        return inventory.remove(item);
    }

    /**
     * Gets the inventory list.
     * @return The inventory.
     */
    public List<Item> getInventory(){return inventory;}

    /**
     * Gets the active quests.
     * @return The active quests.
     */
    public Quest[] getActiveQuests() {
        return activeQuests.toArray(new Quest[0]);
    }

    /**
     * Adds a quest to the quest list.
     * @param quest The quest to add.
     */
    public void addToQuests(Quest quest) {
        activeQuests.add(quest);
    }

    /**
     * Removes ("completes") a quest from the quest list.
     * @param quest The completed quest.
     */
    public void completeQuest(Quest quest) {
        activeQuests.remove(quest);
    }

    /**
     * Handles equipping an item. Checks the slot to see if there's room, then equips the item.
     * @param equip The item to equip.
     */
    public void equipItem(Equipable equip) {
        switch(equip.getSlot()) {
            case HAND:
                if(equippedItems[0] == null) {
                    equippedItems[0] = equip;
                }else{
                    unequipItem(equippedItems[0]);
                    equippedItems[0] = equip;
                }
                break;
            case ARMS:
                if(equippedItems[1] == null) {
                    equippedItems[1] = equip;
                }else{
                    unequipItem(equippedItems[1]);
                    equippedItems[1] = equip;
                }
                break;
            case LEGS:
                if(equippedItems[2] == null) {
                    equippedItems[2] = equip;
                }else{
                    unequipItem(equippedItems[2]);
                    equippedItems[2] = equip;
                }
                break;
            case CHEST:
                if(equippedItems[3] == null) {
                    equippedItems[3] = equip;
                }else{
                    unequipItem(equippedItems[3]);
                    equippedItems[3] = equip;
                }
                break;
            case HEAD:
                if(equippedItems[4] == null) {
                    equippedItems[4] = equip;
                }else{
                    unequipItem(equippedItems[4]);
                    equippedItems[4] = equip;
                }
                break;
        }
        equip.isEquipped = true;
        increaseHP(equip.getMods()[0]);
        increaseAttack(equip.getMods()[1]);
        increaseDefense(equip.getMods()[2]);
    }

    /**
     * Handles unequipping armor.
     * @param equip The armor to unequip.
     */
    public void unequipItem(Equipable equip) {
        switch(equip.getSlot()){
            case HAND:
                equippedItems[0] = null;
                break;
            case ARMS:
                equippedItems[1] = null;
                break;
            case LEGS:
                equippedItems[2] = null;
                break;
            case CHEST:
                equippedItems[3] = null;
                break;
            case HEAD:
                equippedItems[4] = null;
                break;
        }
        equip.isEquipped = false;
        decreaseHP(equip.getMods()[0]);
        decreaseAttack(equip.getMods()[1]);
        decreaseDefense(equip.getMods()[2]);
    }

    /**
     * Gets all equipped items.
     * @return Equipped items.
     */
    public Equipable[] getEquippedArmor() {
        return equippedItems;
    }

    /**
     * Handles quest reception.
     * @param npc The NPC we're talking to.
     */
    public void talk(NPC npc) {
        npc.giveQuest(this);
    }

    public void use(Consumable item){
        if(item instanceof Food){
            heal(item.getEffect());
        }else if(item instanceof Potion){
            switch(item.getName()){
                case "HP Potion":
                    heal(item.getEffect());
                    break;
                case "STR Potion":
                    increaseAttack(item.getEffect());
                    break;
                case "DEF Potion":
                    increaseDefense(item.getEffect());
                    break;
            }
        }
        if(!item.use()){
            removeFromInventory(item);
        }
    }
  
    /**
     * Gets the player's max health.
     * @return The max health.
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Gets the player's health.
     * @return The health.
     */
    @Override
    public int getHealth() {
        return super.getHealth();
    }

    /**
     * Makes the player take damage.
     * @param damage The damage for the mob to take.
     */
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
    }
}
