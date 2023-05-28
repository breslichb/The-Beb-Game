import java.io.Serializable;
import java.util.Random;

class Quest implements Serializable {
    private String name;
    private String description;
    private boolean isCompleted;
    private int killCount;
    private Item reward;

    private Quest(String name, String description, Item reward) {
        this.name = name;
        this.description = description;
        isCompleted = false;
        this.reward = reward;
        killCount = 0;
    }

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
        return new Quest(name, desc, reward);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getKillCount() {
        return killCount;
    }

    public void incrementKillCount() {
        killCount++;
    }

}

