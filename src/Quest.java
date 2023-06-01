import java.io.Serializable;
import java.util.Random;

/**
 * The Quest object.
 * A quest can be given by an NPC, and requires the player to kill a certain amount of enemies.
 * If the player completes the quest, they get an item as a reward.
 * @author Bayasgalan Battogtokh
 */

class Quest implements Serializable {
    /** The name of the quest */
    private String name;

    /** A description of the quest */
    private String description;

    /** Whether the quest has been completed */
    private boolean isCompleted;

    /** The kill count so far for this quest */
    private int killCount;

    /** The kill goal for this quest */
    private int killGoal;

    /** The quest reward */
    private Item reward;

    /**
     * Quest Constructor
     * @param name The quest's name.
     * @param description The quest's description.
     * @param killGoal The quest's kill goal.
     * @param reward The quest's reward.
     */
    private Quest(String name, String description, int killGoal, Item reward) {
        this.name = name;
        this.description = description;
        isCompleted = false;
        this.reward = reward;
        killCount = 0;
        this.killGoal = killGoal;
    }

    /**
     * Randomly creates a quest with a name and reward.
     * @return The created quest.
     */
    public static Quest createQuest(){
        Random rand = new Random();
        int kills = rand.nextInt(5)+1;
        String name = "Kill " + kills + " Enemies";
        String desc = "Kill a total of " + kills + " enemies to complete this quest.";
        Item reward = null;

        switch(rand.nextInt(4)+1){
            case 1:
                reward = Food.createFood();
                break;
            case 2:
                reward = Potion.createPotion();
                break;
            case 3:
                reward = Weapon.createWeapon();
                break;
            case 4:
                reward = Armor.createArmor();
                break;
        }
        return new Quest(name, desc, kills, reward);
    }

    /**
     * Name getter
     * @return The name of the quest
     */
    public String getName() {
        return name;
    }

    /**
     * Desc getter
     * @return The quest's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks and returns the quest's completion status.
     * @return True if complete, false if not.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Gets the quest's kill count.
     * @return The kill count.
     */
    public int getKillCount() {
        return killCount;
    }

    /**
     * Increments the quest's kill count, and completes it if kill count is equal to the goal.
     */
    public void incrementKillCount() {
        killCount++;
        if(killCount == killGoal){
            isCompleted = true;
        }
    }

    /**
     * Gets the quest's reward.
     * @return The item reward.
     */
    public Item getReward(){return reward;}

}

