import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

/**
 * This class is a JUnit test class for map generation. It makes sure that the map generates sensibly and that
 * nothing got lost along the way.
 */
public class TestMapGen {
    static GameMap g;

    @BeforeAll
    static void init() {
        g = new GameMap(10, 10, 2, 2, 5, 5);
    }

    @Test
    @DisplayName("Test Map Connections")
    void testMapConnections() {
        ArrayList<Room> rmsToTest = new ArrayList<Room>(g.getRoomsList());
        recursiveTestRooms(5, 5, rmsToTest);
        System.out.println(g);
        assertEquals(0, rmsToTest.size());
    }

    void recursiveTestRooms(int x, int y, ArrayList<Room> remainingRooms) {
        Room r = g.getRoomByCoords(x, y);
        remainingRooms.remove(r);
        if(remainingRooms.contains(g.getRoomByCoords(x, y-1))) {
            recursiveTestRooms(x, y-1, remainingRooms);
        }
        if(remainingRooms.contains(g.getRoomByCoords(x, y+1))) {
            recursiveTestRooms(x, y+1, remainingRooms);
        }
        if(remainingRooms.contains(g.getRoomByCoords(x-1, y))) {
            recursiveTestRooms(x-1, y, remainingRooms);
        }
        if(remainingRooms.contains(g.getRoomByCoords(x-1, y))) {
            recursiveTestRooms(x+1, y, remainingRooms);
        }
    }
}
