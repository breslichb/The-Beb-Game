/**
 * This is a room that is guaranteed to contain an enemy.
 * The enemy is randomly generated upon room init.
 * @author Benjamin Breslich
 */

public class CombatRoom extends Room{
    /**
     * CombatRoom constructor, takes in the parent GameMap object.
     * @param map The GameMap this room resides in.
     */
    public CombatRoom(GameMap map) {
        super(map);
        addEnemy(Enemy.createEnemy());
    }
}
