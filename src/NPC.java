public class NPC extends Mobs {
    private Quest quest;

    public NPC(int hp, int def, int attack, String name, String description, Quest quest) {
        super(hp, def, attack, name, description);
        this.quest = quest;
    }
    public boolean talk() {

    }
    public Quest getQuest() {
        return quest;
    }

        public Quest giveQuest() {
        return quest;
    }
    /*    public void giveQuest(Player player, Quest quest) {
        System.out.println("Here is some quest: " + quest.getDescription());
        player.addQuest(quest);
    }*/

}
