/**
 * This is a room that is guaranteed to contain an enemy.
 * The enemy is randomly generated upon room init.
 */

public class CombatRoom extends Room{
    public CombatRoom(GameMap map) {
        super(map);
        addEnemy(Enemy.createEnemy());
    }
}
