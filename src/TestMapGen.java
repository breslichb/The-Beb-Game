import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

/**
 * This class is a JUnit test class for map generation. It makes sure that the map generates sensibly and that
 * nothing is segmented off/inaccessible.
 */
public class TestMapGen {
    /** Test map */
    static GameMap g;

    /**
     * Initialize our test map.
     */
    @BeforeAll
    static void init() {
        Player p = Player.createPlayer("DEFAULT");
        g = new GameMap(10, 10, 2, 2, 5, 5, p);
    }

    /**
     * Test our map connections.
     */
    @Test
    @DisplayName("Test Map Connections")
    void testMapConnections() {
        ArrayList<Room> rmsToTest = new ArrayList<Room>(g.getRoomsList());
        recursiveTestRooms(5, 5, rmsToTest);
        System.out.println("Generated test room:");
        System.out.println(g);
        assertEquals(0, rmsToTest.size());
    }

    void recursiveTestRooms(int x, int y, ArrayList<Room> remainingRooms) {
        Room r = g.getRoomByCoords(x, y);
        if(!remainingRooms.contains(r)) {
            return;
        } else {
            remainingRooms.remove((r));
        }
        if(g.canMove(x, y, GameMap.Direction.NORTH)) {
            recursiveTestRooms(x, y - 1, remainingRooms);
        }
        if(g.canMove(x, y, GameMap.Direction.SOUTH)) {
            recursiveTestRooms(x, y + 1, remainingRooms);
        }
        if(g.canMove(x, y, GameMap.Direction.WEST)) {
            recursiveTestRooms(x - 1, y, remainingRooms);
        }
        if(g.canMove(x, y, GameMap.Direction.EAST)) {
            recursiveTestRooms(x + 1, y, remainingRooms);
        }
    }
}
