import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

class Player extends Mobs implements Serializable {

    private List<Item> inventory;
    private int numItems;
    private int weightMax;
    private int weightCurr;
    private List<Quest> activeQuests;
    private Mobs lastKilledEnemy;
    private Equipable[] equippedItems;
    private int maxHealth;

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

    public static Player createPlayer(String name) {
        // Generate random player stats
        int health = generateRandomStat(100, 150);
        int attack = generateRandomStat(10, 20);
        int defense = generateRandomStat(10, 20);
        int weightMax = generateRandomStat(100, 150);

        return new Player(name, health, attack, defense, weightMax);
    }
    private static int generateRandomStat(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public void increaseHP(int x){
        maxHealth += x;
        heal(x);
    }

    public void setLastKilledEnemy(Mobs enemy) {
        this.lastKilledEnemy = enemy;
    }

    public Mobs getLastKilledEnemy() {
        return lastKilledEnemy;
    }

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

    public boolean removeFromInventory(Item item) {
        return inventory.remove(item);
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
    }

    public boolean hasActiveQuests() {
        return !activeQuests.isEmpty();
    }

    //armor stuff
    public void equipItem(Equipable equip) {
        switch(equip.getSlot()) {
            case HAND:
                if(equippedItems[0] == null) {
                    equippedItems[0] = equip;
                }
                break;
            case ARMS:
                if(equippedItems[1] == null) {
                    equippedItems[1] = equip;
                }
                break;
            case LEGS:
                if(equippedItems[2] == null) {
                    equippedItems[2] = equip;
                }
                break;
            case CHEST:
                if(equippedItems[3] == null) {
                    equippedItems[3] = equip;
                }
                break;
            case HEAD:
                if(equippedItems[4] == null) {
                    equippedItems[4] = equip;
                }
                break;
        }
        increaseHP(equip.getMods()[0]);
        increaseAttack(equip.getMods()[1]);
        increaseDefense(equip.getMods()[2]);
    }

    public void unequipArmor(Equipable equip) {

    }

    public Equipable[] getEquippedArmor() {
        return equippedItems;
    }

    public boolean hasEquippedArmor() {
        return equippedItems != null;
    }

    //talk
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
