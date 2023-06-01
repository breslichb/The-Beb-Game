/**
 * The room the player starts in on any given GameMap floor.
 * @author Benjamin Breslich
 */
public class StartingRoom extends Room{
    /**
     * StartingRoom constructor.
     * @param parent The parent GameMap.
     */
    public StartingRoom(GameMap parent) {
        super(parent);
        desc = "There's a hatch in the ceiling. It is closed";
    }
}
