import java.io.Serializable;
import java.util.Random;

/**
 * The Enemy class is used to create enemies, which can attack the player when they engage in combat.
 * This is a subtype of the NPC class.
 * @author Bayasgalan Battogtokh
 */

class Enemy extends Mobs implements Serializable {
    /**
     * Enemy constructor. Creates an
     * @param name The name of the enemy
     * @param health The health of the enemy
     * @param attack The enemy's attack damage
     * @param defense The enemy's defense value
     */
    public Enemy(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
    }

    /**
     * Creates a randomly-generated enemy.
     * @return The created enemy.
     */
    public static Enemy createEnemy() {
        // Generate random enemy stats
        String[] enemyNames = {"Goblin", "Skeleton", "Orc", "Dragon"};
        String name = enemyNames[new Random().nextInt(enemyNames.length)];
        int health = generateRandomStat(50, 100);
        int attack = generateRandomStat(5, 15);
        int defense = generateRandomStat(5, 15);

        return new Enemy(name, health, attack, defense);
    }

    /**
     * Generates a random stat value between the provided min and max.
     * @param min The minimum bound for the stat.
     * @param max The maximum bound for the stat.
     * @return The stat value to use.
     */
    private static int generateRandomStat(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
    