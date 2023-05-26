import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class is a test class for the Database Connection System.
 * This tests the database connection, the serialization functions, and the deserialization functions.
 */
public class TestDBConnector {
    /** Test map */
    static GameMap g;

    /** Test player */
    static Player p;

    /** Connection */
    static Connection con;

    /**
     * Initialize our test map, player, and connection.
     */
    @BeforeAll
    static void init() {
        g = new GameMap(10, 10, 2, 2, 5, 5);
        p = Player.createPlayer();
        con = DBConnector.connect();
    }

    /**
     * Test our map connections.
     */
    @Test
    @DisplayName("Test DB Connection")
    void testDBConnection() {
        assertTrue(con != null);
    }

    @Test
    @DisplayName("Test Serialization/Deserialization")
    void testSerialization() throws SQLException{
        DBConnector.putSaveState("save1", p, g, con);
        Object[] o = DBConnector.getSaveStateByID(1, con);
        Player p1 = (Player) o[0];
        GameMap g1 = (GameMap) o[1];
        assertTrue(p.getName().equals(p1.getName()));
        assertTrue(g1.getRoomsList().equals(g.getRoomsList()));
    }
}
