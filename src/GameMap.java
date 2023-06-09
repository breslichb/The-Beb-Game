import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.max;

/**
 * The map class.
 * This class handles the 2-D representation of the game world, as well as any vertical links between rooms.
 * @author Benjamin Breslich
 */

public class GameMap implements Serializable {
    /** A 2-D array representing the rooms in the map. Coordinates are [y][x]. */
    private Room[][] rooms;

    /** A list of all our rooms, for ease of access. */
    private ArrayList<Room> roomsList;

    /** The player currently in the Map */
    private Player player;

    /** An int array that stores the player's location. 2-size, made on init.*/
    private int[] playerLocation;

    /** An enumerator that represents the possible directions a character can move. This is used to
     * check if someone can move in a given direction. North is always a lower Y, West is always a lower X.
     */
    public enum Direction{NORTH, EAST, WEST, SOUTH}

    /** The cap on random rooms, so we don't generate a million for large areas. */
    private static final double RANDOM_ROOM_PERCENTAGE = 0.6;

    /**
     * Generates our game map.
     * @param xdim Size x-wise.
     * @param ydim Size y-wise.
     * @param numQuests Number of quests to generate.
     * @param numEnemies Number of enemies to generate.
     * @param startX Starting X Coordinate.
     * @param startY Starting Y Coordinate.
     */
    public GameMap(int xdim, int ydim, int numQuests, int numEnemies, int startX, int startY, Player player) {
        // Init room lists
        rooms = new Room[ydim][xdim];
        roomsList = new ArrayList<Room>();

        // Make our starting room then run room generation
        addRoom(startX, startY, new StartingRoom(this));
        playerLocation = new int[]{startY, startX};
        generateRooms(startX, startY, numQuests, numEnemies, xdim, ydim);
        this.player = player;
    }

    /**
     * Generates a game map using a central starting location.
     */
    public GameMap(int xdim, int ydim, int numQuests, int numEnemies, Player player) {
        this(xdim, ydim, numQuests, numEnemies, xdim / 2, ydim / 2, player);
    }

    /**
     * Generates a new GameMap. Used when traversing floors.
     */
    public void regenerate(){
        int startX = playerLocation[1];
        int startY = playerLocation[0];
        rooms = new Room[rooms.length + 1][rooms[0].length + 1];
        roomsList = new ArrayList<Room>();

        addRoom(startX, startY, new StartingRoom(this));
        generateRooms(startX, startY, 3, 3, rooms.length, rooms[0].length);
    }

    /**
     * Prints our current game map to a string. This shows our room layout.
     */
    public String toString(){
        StringBuilder str = new StringBuilder("");
        for(int i = 0; i < rooms.length; i++) {
            for(int j = 0; j < rooms[0].length; j++) {
                if(rooms[i][j] == getPlayerRoom()) {
                    str.append("|/");
                } else if (rooms[i][j] instanceof DescendingRoom) {
                    str.append("|^");
                }else if (rooms[i][j] instanceof NPCRoom){
                    str.append("|!");
                } else if (rooms[i][j] != null) {
                    str.append("|*");
                } else {
                    str.append("|_");
                }
            }
            str.append("|\n");
        }
        return str.toString();
    }

    /**
     * Gets the player.
     * @return the player object.
     */
    public Player getPlayer() {return player;}

    /**
     * Return's the player's location.
     * @return The X/Y coords of the player.
     */
    public int[] getPlayerLocation(){
        return playerLocation;
    }

    /**
     * Returns the room the player is in
     * @return The room the player's in.
     */
    public Room getPlayerRoom(){
        return getRoomByCoords(playerLocation[1], playerLocation[0]);
    }

    /**
     * Tries to move the player
     * @return The room the player moved to if successful, null if the move was unsuccessful.
     */
    public Room tryMovePlayer(Direction dir) {
        if(canMove(playerLocation[1], playerLocation[0], dir)) {
            playerLocation = getCoordsFromDir(playerLocation[1], playerLocation[0], dir);
            return rooms[playerLocation[0]][playerLocation[1]];
        }
        return null;
    }

    /**
     * Gets the room list.
     * @return The room list.
     */
    public ArrayList<Room> getRoomsList() {
        return roomsList;
    }

    /**
     * Gets a room by x/y coords.
     * @param x The x-coord of the target
     * @param y The y-coord of the target
     * @return The room at the x/y pair.
     */
    public Room getRoomByCoords(int x, int y) {
        try {
            return rooms[y][x];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Handles adding a room to the map and our room list.
     * @param x The X coord of the room.
     * @param y The Y coord of the room.
     * @param r The room we're adding.
     */
    public void addRoom(int x, int y, Room r) {
        rooms[y][x] = r;
        roomsList.add(r);
    }

    /**
     * Attempts to generate rooms using the input parameters.
     * This method is fail-safe, and will default to safe parameters if its passed invalid ones.
     * @param startX The starting X position of the character
     * @param startY The starting Y position of the character
     * @param numQuests The number of quests to generate
     * @param numEnemies The number of enemy-containing rooms to generate (excluding possible quest enemies)
     */
    private void generateRooms(int startX, int startY, int numQuests, int numEnemies, int xdim, int ydim){
        // Init random for later
        Random r = new Random();

        // Get our room totals. If we don't have enough rooms to actually generate all our quests/enemies,
        // we just abort early, so we don't have to worry about randomRooms being negative.
        int possibleRooms = xdim * ydim - 1;
        int randomRooms = possibleRooms - numQuests - numEnemies;
        if(randomRooms > 0) {
            randomRooms = (int) (randomRooms * RANDOM_ROOM_PERCENTAGE);
        }
        int roomsToGenerate = randomRooms + numQuests * 2 + numEnemies;

        // Next, we need to make our room structure. To do this, we make a list of coordinates that we can use
        // to generate rooms, then pick random rooms for each.
        // We have 2 arraylists. One stores the coords for rooms we'll generate, and one stores room coords
        // adjacent to our current rooms.
        ArrayList<int[]> coords = new ArrayList<int[]>();
        ArrayList<int[]> adjacencies = new ArrayList<int[]>();
        adjacencies.add(new int[]{startY, startX});

        // This for loop handles the coord picking.
        for(int i = 0; i < roomsToGenerate; i++) {
            int[] coord = adjacencies.get(r.nextInt(adjacencies.size()));
            coords.add(coord);
            adjacencies.remove(coord);
            adjacencies.addAll(getAdjacencies(coord[1], coord[0], adjacencies, coords));
        }

        int numDescents = 1;

        // This is the loop that handles initializing rooms
        while(coords.size() > 0) {
            int[] coord = coords.get(max(r.nextInt(coords.size()) - 1, 0));
            if(rooms[coord[0]][coord[1]] != null) {
                coords.remove(coord);
                continue;
            }
            if(numDescents > 0) {
                numDescents--;
                addRoom(coord[1], coord[0], new DescendingRoom(this));
                coords.remove(coord);
                continue;
            }
            if(numQuests > 0) {
                numQuests--;
                addRoom(coord[1], coord[0], new NPCRoom(this));
                coords.remove(coord);
                continue;
            }
            if(numEnemies > 0) {
                numEnemies--;
                addRoom(coord[1], coord[0], new CombatRoom(this));
                coords.remove(coord);
                continue;
            }
            addRoom(coord[1], coord[0], new RandomRoom(this));
            coords.remove(coord);
        }
    }

    /**
     * Gets the adjacent coordinates in each cardinal direction for a given coordinate pair.
     * @param x The origin x-coord.
     * @param y The origin y-coord.
     * @param adjacenciesList The list to store the adjacencies in.
     * @param coordsList A list of coordinates we want to ignore.
     * @return
     */
    public ArrayList<int[]> getAdjacencies(int x, int y, ArrayList<int[]> adjacenciesList , ArrayList<int[]> coordsList) {
        ArrayList<int[]> retList = new ArrayList<int[]>();
        // Cycle through every possible direction from our room
        dir:
        for(Direction dir : Direction.values()) {
            int[] coords = getCoordsFromDir(x, y, dir);
            // Don't bother if this is already a coordinate we want to use
            for(int[] coord : coordsList) {
                if(coord[0] == coords[0] && coord[1] == coords[1]) {
                    continue dir;
                }
            }
            // check to make sure a given direction is valid
            if (coords[0] >= 0 && coords[0] < rooms.length && coords[1] >= 0 && coords[1] < rooms[0].length) {
                retList.add(coords);
            }
        }
        return retList;
    }

    /**
     * Checks if we can move a given direction from a given position.
     * @param startX the start X-pos.
     * @param startY the start Y-pos.
     * @param dir The direction we want to move.
     * @return True if we can move, false if we can't.
     */
    public boolean canMove(int startX, int startY, Direction dir) {
        int[] newCoords = getCoordsFromDir(startX, startY, dir);
        try { // See if we can access our room. If there's a room there, we can move there.
            if (rooms[newCoords[0]][newCoords[1]] != null) {
                return true;
            }
        // Of course, if that's out of bounds, we can't move there regardless.
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return false;
    }

    /**
     * Gets the coordinates of one "step" in a given direction
     * @param x The start X-pos.
     * @param y The start Y-pos.
     * @param dir The direction to step.
     * @return The coordinates of the new area, formatted [y][x] for ease of use with the rooms array.
     */
    public int[] getCoordsFromDir(int x, int y, Direction dir) {
        switch(dir){
            case NORTH -> {
                return new int[]{y-1, x};
            }
            case SOUTH -> {
                return new int[]{y+1, x};
            }
            case EAST -> {
                return new int[]{y, x+1};
            }
            case WEST -> {
                return new int[]{y, x-1};
            }
        }
        return null;
    }


}
