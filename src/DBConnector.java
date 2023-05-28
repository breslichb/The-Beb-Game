import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class DBConnector {
    private static String dburl = "jdbc:mysql://localhost:3306/bebdb";
    private static String username = "root";
    private static String password = "2003";
    private static String putSerializedObjectSQL = "INSERT INTO savestates(name, gamemap) VALUES (?, ?)";
    private static String getSerializedObjectSQLbyID = "SELECT name, gamemap FROM savestates WHERE id = ";
    private static String getSerializedObjectSQLbyName = "SELECT gamemap FROM savestates WHERE name LIKE *?*";

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

    public static GameMap getSaveStateByID(int id, Connection con) throws SQLException {
        Statement sqlStatement = con.createStatement();
        ResultSet results = sqlStatement.executeQuery(getSerializedObjectSQLbyID + id);
        results.next();
        String name = results.getString("name");
        GameMap map = (GameMap) deserializeObject(results.getBytes("gamemap"));
        sqlStatement.close();
        return map;
    }

    public static ArrayList<GameMap> getSaveStateByName(String name, Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(getSerializedObjectSQLbyName);
        ps.setString(1, name);
        ResultSet results = ps.executeQuery();
        ArrayList<GameMap> maps = new ArrayList<>();
        while(results.next()) {
            GameMap map = (GameMap) deserializeObject(results.getBytes("gamemap"));
            maps.add(map);
        }
        ps.close();
        return maps;
    }

    public static GameMap getLatestSaveState(Connection con) throws SQLException {
        Statement sqlStatement = con.createStatement();
        ResultSet results = sqlStatement.executeQuery("SELECT id, gamemap FROM savestates ORDER BY id DESC");
        results.next();
        GameMap g = (GameMap) deserializeObject(results.getBytes("gamemap"));
        return g;
    }

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

    public static Connection connect(){
        try{
            return DriverManager.getConnection(dburl, username, password);
        } catch (Exception e) {
            System.out.println("ERROR: Database Connection Failed. Database Integration Unavailable.");
            return null;
        }
    }
}
