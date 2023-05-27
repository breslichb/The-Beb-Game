import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.*;
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
        assertNotNull(con);
    }

    /**
     * Test that everything's being serialized properly.
     * This looks at our player and our map before/after serialization, making sure that they're equal.
     * @throws SQLException
     */
    @Test
    @DisplayName("Test Serialization/Deserialization")
    void testSerialization() throws SQLException{
        DBConnector.putSaveState("save1", p, g, con);
        Statement sqlStatement = con.createStatement();
        ResultSet results = sqlStatement.executeQuery("SELECT id FROM savestates ORDER BY id DESC");
        results.next();
        int id = results.getInt("id");
        Object[] o = DBConnector.getSaveStateByID(id, con);
        Player p1 = (Player) o[1];
        GameMap g1 = (GameMap) o[2];
        assertTrue(p.getName().equals(p1.getName()));
        ArrayList<Room> l1 = g1.getRoomsList();
        ArrayList<Room> l2 = g.getRoomsList();
        assertTrue(l1.size() == l2.size());
    }
}
