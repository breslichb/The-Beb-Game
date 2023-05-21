public class NPCRoom extends Room{
    public NPCRoom(GameMap map) {
        super(map);
        NPC.createNPC();
    }
}
