public class DescendingRoom extends Room{
    /**
     * DescendingRoom constructor, takes in the parent GameMap object.
     * @param map The GameMap this room resides in.
     */
    public DescendingRoom(GameMap map) {
        super(map);
    }

    /**
     * Returns a string describing the room. Also does things on entry.
     * @return a string describing the interaction.
     */
    public String onEntry(){
        return "You enter a room.\n" + desc + ".\nYou notice that there's a trapdoor in the center of the room.";
    }

    /**
     * Allows a player to interact with the room itself.
     * @return a string describing the interaction.
     */
    public String interact(){
        parentMap.regenerate();
        return "You climb down through the trapdoor into a lower floor.\nAs you drop down, the hatch slams shut behind you.";
    }

}
