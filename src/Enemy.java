import java.util.Random;
public class Enemy extends Mobs {

    private Item drop;

    public Enemy(int hp, int def, int attack, String name, String description, Item drop) {
        super(hp, def, attack, name, description);
        this.drop = drop;
    }
    // Add a constructor with random attack and defense values for the enemy, 100hp, attack 15-25 and adds extra hp 10-50
    public Enemy(String name, String description, Item drop) {
        super(100, getRandomValue(15, 25), getRandomValue(10, 50), name, description);
        this.drop = drop;
    }
    // Helper method to get a random value within a range
    private static int getRandomValue(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    @Override
    public void receiveDamage(int damage) {
        super.receiveDamage(damage);
        if (hp <= 0) {
            // Enemy dies
            death();
        }
    }

    public void death() {
        // Drop the amulet
        System.out.println("Enemy " + getName() + " has been defeated!");
        System.out.println("It dropped an amulet.");

        // Add the dropped amulet to the player's inventory
       // player.getPack().add(getDrop());
    }
}