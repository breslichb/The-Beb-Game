import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The map class.
 * This class handles the 2-D representation of the game world, as well as any vertical links between rooms.
 */

public class GameMap implements Serializable {
    /** A 2-D array representing the rooms in the map. Coordinates are [y][x]. */
    private Room[][] rooms;

    /** A hashmap with an index of rooms, mapped room ID -> room object */
    private Map<Integer, Room> roomsByID;

    /** An int array that stores the player's location. 2-size, made on init.*/
    private int[] playerLocation;

    /** An enumerator that represents the possible directions a character can move. This is used to
     * check if someone can move in a given direction. North is always a lower Y, West is always a lower X.
     */
    public enum Direction{NORTH, EAST, WEST, SOUTH}

    /**
     * Generates our game map.
     * @param xdim Size x-wise.
     * @param ydim Size y-wise.
     * @param numQuests Number of quests to generate.
     * @param numEnemies Number of enemies to generate.
     * @param startX Starting X Coordinate.
     * @param startY Starting Y Coordinate.
     */
    private GameMap(int xdim, int ydim, int numQuests, int numEnemies, int startX, int startY) {
        // Init room lists
        rooms = new Room[ydim][xdim];
        roomsByID = new HashMap<Integer, Room>();

        // Make our starting room then run room generation
        rooms[startY][startX] = new StartingRoom();
        generateRooms(startX, startY, numQuests, numEnemies);
    }

    /**
     * Generates a game map using a central starting location.
     */
    private GameMap(int xdim, int ydim, int numQuests, int numEnemies) {
        int startX = xdim / 2;
        int startY = ydim / 2;
        // Init room lists
        rooms = new Room[ydim][xdim];
        roomsByID = new HashMap<Integer, Room>();

        // Make our starting room then run room generation
        rooms[startY][startX] = new StartingRoom();
        generateRooms(startX, startY, numQuests, numEnemies);
    }

    /**
     * Attempts to generate rooms using the input parameters.
     * This method is fail-safe, and will default to safe parameters if its passed invalid ones.
     * @param startX The starting X position of the character
     * @param startY The starting Y position of the character
     * @param numQuests The number of quests to generate
     * @param numEnemies The number of enemy-containing rooms to generate (excluding possible quest enemies)
     */
    private void generateRooms(int startX, int startY, int numQuests, int numEnemies) {

    }

    public boolean canMove(int startX, int startY, Direction dir) {
        switch(dir) {
            case NORTH -> {
                return rooms[startY - 1][startX] != null;
            }
            case SOUTH -> {
                return rooms[startY + 1][startX] != null;
            }
            case EAST -> {
                return rooms[startY][startX + 1] != null;
            }
            case WEST -> {
                return rooms[startY][startX - 1] != null;
            }
            default -> {
                return false;
            }
        }
    }
}
