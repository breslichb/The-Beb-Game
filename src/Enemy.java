import java.io.Serializable;
import java.util.Random;

class Enemy extends Mobs implements Serializable {
    public Enemy(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
    }

    public String dropAmulet() {
        String amulet = "Amulet of Sus";
        return amulet;
    }

    public static Enemy createEnemy() {
        // Generate random enemy stats
        String[] enemyNames = {"Goblin", "Skeleton", "Orc", "Dragon"};
        String name = enemyNames[new Random().nextInt(enemyNames.length)];
        int health = generateRandomStat(50, 100);
        int attack = generateRandomStat(5, 15);
        int defense = generateRandomStat(5, 15);

        return new Enemy(name, health, attack, defense);
    }
    private static int generateRandomStat(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
    