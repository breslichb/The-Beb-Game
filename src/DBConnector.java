import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class DBConnector {
    private static String dburl = "jdbc:mysql://localhost:3306/bebdb";
    private static String username = "root";
    private static String password = "wI*n2AXv8K6czKdW";
    private static String putSerializedObjectSQL = "INSERT INTO savestates(name, player, gamemap) VALUES (?, ?, ?)";
    private static String getSerializedObjectSQLbyID = "SELECT name, player, gamemap FROM savestates WHERE id = ";
    private static String getSerializedObjectSQLbyName = "SELECT player, gamemap FROM savestates WHERE name LIKE *?*";

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

    public static Object[] getSaveStateByID(int id, Connection con) throws SQLException {
        Statement sqlStatement = con.createStatement();
        ResultSet results = sqlStatement.executeQuery(getSerializedObjectSQLbyID + id);
        results.next();
        String name = results.getString("name");
        Player player = (Player) deserializeObject(results.getBytes("player"));
        GameMap map = (GameMap) deserializeObject(results.getBytes("gamemap"));
        sqlStatement.close();
        return new Object[] {name, player, map};
    }

    public static Object[] getSaveStateByName(String name, Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(getSerializedObjectSQLbyName);
        ps.setString(1, name);
        ResultSet results = ps.executeQuery();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<GameMap> maps = new ArrayList<>();
        while(results.next()) {
            Player player = (Player) deserializeObject(results.getBytes("player"));
            GameMap map = (GameMap) deserializeObject(results.getBytes("gamemap"));
            players.add(player);
            maps.add(map);
        }
        ps.close();
        return new ArrayList[] {players, maps};
    }

    public static int putSaveState(String name, Player p, GameMap map, Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(putSerializedObjectSQL, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ps.setBytes(2, serializeObject(p));
        ps.setBytes(3, serializeObject(map));
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

    public static Connection connect(){
        try{
            return DriverManager.getConnection(dburl, username, password);
        } catch (Exception e) {
            System.out.println("ERROR: Database Connection Failed. Database Integration Unavailable.");
            return null;
        }
    }
}
