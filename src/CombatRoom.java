public class CombatRoom extends Room{
    public CombatRoom(GameMap map) {
        super(map);
        addEnemy(Enemy.createEnemy());
    }
}
