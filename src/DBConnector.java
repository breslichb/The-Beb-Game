import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * The Database Connector class
 * Handles DB connection, save serialization, and save deserialization.
 * @author Benjamin
 */
public class DBConnector {
    /** Database connection URL*/
    private static final String dburl = "jdbc:mysql://localhost:3306/bebdb";

    /** Database connection user */
    private static final String username = "root";

    /** Database connection password */
    private static final String password = "2003";

    /** SQL to insert a save state into the database */
    private static final String putSerializedObjectSQL = "INSERT INTO savestates(name, gamemap) VALUES (?, ?)";

    /** SQL to get a save state from the database by save ID */
    private static final String getSerializedObjectSQLbyID = "SELECT name, gamemap FROM savestates WHERE id = ";

    /** SQL to get a save state from the database by name */
    private static final String getSerializedObjectSQLbyName = "SELECT gamemap FROM savestates WHERE name LIKE *?*";

    /** SQL to get all save states, ordered latest to earliest. */
    private static final String getSerializedObjectSQLbyLatest = "SELECT id, gamemap FROM savestates ORDER BY id DESC";

    /**
     * Serializes an object for use in the database.
     * @param input The object to serialize.
     * @return A byte array containing the serialized object.
     * @throws SQLException Thrown when serialization fails for any reason.
     */
    private static byte[] serializeObject(Object input) throws SQLException {
        try {
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            ObjectOutputStream objectout = new ObjectOutputStream(byteout);
            objectout.writeObject(input);
            objectout.close();
            return byteout.toByteArray();
        } catch (Exception e) {
            throw new SQLException("Serialization Error: " + e.toString());
        }
    }

    /**
     * Deserializes a byte array containing an object. This object must be cast after deserialization.
     * @param input The byte array to deserialize.
     * @return The deserialized object.
     * @throws SQLException Thrown when deserialization fails for any reason.
     */
    private static Object deserializeObject(byte[] input) throws SQLException {
        try {
            ByteArrayInputStream bytein = new ByteArrayInputStream(input);
            ObjectInput in = new ObjectInputStream(bytein);
            Object retVal = in.readObject();
            bytein.close();
            in.close();
            return retVal;
        } catch (Exception e) {
            throw new SQLException("Deserialization Error: " + e.toString());
        }
    }

    /**
     * Gets the last-made save.
     * @param con The database connection.
     * @return The retrieved save.
     * @throws SQLException Thrown if database access fails.
     */
    public static GameMap getLatestSaveState(Connection con) throws SQLException {
        Statement sqlStatement = con.createStatement();
        ResultSet results = sqlStatement.executeQuery(getSerializedObjectSQLbyLatest);
        results.next();
        GameMap g = (GameMap) deserializeObject(results.getBytes("gamemap"));
        return g;
    }

    /**
     * Puts a savestate into the database.
     * @param name The name of the save state (typically the player name).
     * @param map The map to save.
     * @param con The database connection.
     * @return The ID that this save state has been assigned.
     * @throws SQLException Thrown if database access or serialization fails.
     */
    public static int putSaveState(String name, GameMap map, Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(putSerializedObjectSQL, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ps.setBytes(2, serializeObject(map));
        boolean success = false;
        if(ps.executeUpdate() == 1) {
            success = true;
        }
        ResultSet rs = ps.getGeneratedKeys();
        int serialized_id = -1;
        if (rs.next()) {
            serialized_id = rs.getInt(1);
        }
        ps.close();
        return serialized_id;
    }

    /**
     * Gets a database connection.
     * @return The newly-created connection.
     */
    public static Connection connect(){
        try{
            return DriverManager.getConnection(dburl, username, password);
        } catch (Exception e) {
            System.out.println("ERROR: Database Connection Failed. Database Integration Unavailable.");
            return null;
        }
    }
}
