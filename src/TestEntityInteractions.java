import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestEntityInteractions {
    static Player p;
    static GameMap g;
    static Enemy e;
    static NPC n;
    static Consumable i;

    // setup generated entities and our player
    @BeforeAll
    static void setup(){
        p = Player.createPlayer("Test player");
        g = new GameMap(5, 5, 1, 1, p);
        e = Enemy.createEnemy();
        n = NPC.createNPC("Test NPC");
        i = Food.createFood();
        g.getPlayerRoom().addItem(i);
        g.getPlayerRoom().addEnemy(e);
        g.getPlayerRoom().addNPC(n);
    }

    // Test player attacks
    // The player should be able to damage enemies.
    @Test
    void testPlayerAttack() {
        int ehp = e.getHealth();
        p.attack(e);
        assertTrue(e.getHealth() < ehp);
    }

    // Test enemy attacks.
    // Enemies should be able to damage the player
    @Test
    void testEnemyAttack() {
        int php = p.getHealth();
        e.attack(p);
        assertTrue(p.getHealth() < php);
    }

    // Test consumable usage.
    // The player should be able to heal with food.
    @Test
    void testPlayerConsumable() {
        int php = p.getHealth();
        p.takeDamage(php - 1); // damage player to 1 HP
        p.use(i);
        assertTrue(p.getHealth() > 1); // see if they got healed
    }

    // Test player movement. If CanMove says there's a room, the player should be able to move.
    @Test
    void testPlayerMovement() {
        int[] loc = g.getPlayerLocation();
        if(g.canMove(loc[1], loc[0], GameMap.Direction.NORTH)) {
            assertTrue(g.tryMovePlayer(GameMap.Direction.NORTH) != null);
        }
    }

    // Test player descent.
    // When descending a floor and regenerating, the floor should become bigger.
    @Test
    void testDescent(){
        int size = g.getRoomsList().size();
        g.regenerate();
        assertTrue(size < g.getRoomsList().size());
    }
}
