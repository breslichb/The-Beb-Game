public class Player extends Mobs {
    private int headArmor;
    private int bodyArmor;
    private int armsArmor;
    private int legsArmor;
    private Weapon[] hands;
    private List<Item> pack = new ArrayList<>();
    private List<Quest> quests = new ArrayList<>();

    public Player(int hp, int def, int attack, String name, String description, int headArmor, int bodyArmor, int armsArmor, int legsArmor, Weapon[] hands) {
        super(hp, def, attack, name, description);
        this.headArmor = headArmor;
        this.bodyArmor = bodyArmor;
        this.armsArmor = armsArmor;
        this.legsArmor = legsArmor;
        this.hands = hands;
    }

    public int getHeadArmor() {
        return headArmor;
    }

    public int getBodyArmor() {
        return bodyArmor;
    }

    public int getArmsArmor() {
        return armsArmor;
    }

    public int getLegsArmor() {
        return legsArmor;
    }

    public Weapon[] getHands() {
        return hands;
    }

    public List<Item> getPack() {
        return pack;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public boolean talk() {

    }

    public boolean walk(Direction direction) {

    }

    public void inventory() {

    }

    public void quests() {

    }

    public boolean interact() {

    }
}
