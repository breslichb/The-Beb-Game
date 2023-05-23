import java.io.*;
import java.sql.*;

public class DBConnector {
    private static String dburl = "jdbc:mysql://localhost:3306/exampledb";
    private static String username = "root";
    private static String password = "";
    private static String getSerializedObjectSQL = "SELECT player, map FROM savestates WHERE id = ";

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
            return in.readObject();
        } catch (Exception e) {
            throw new SQLException("Deserialization Error: " + e.toString());
        }
    }

    public static Object[] getSaveStateByID(int id, Connection con) throws SQLException {
        Statement sqlStatement = con.createStatement();
        ResultSet results = sqlStatement.executeQuery(getSerializedObjectSQL + id);
        Player player = (Player) deserializeObject(results.getBytes("player"));
        GameMap map = (GameMap) deserializeObject(results.getBytes("map"));
        return new Object[] {player, map};
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
