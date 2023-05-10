public class Enemy extends Mobs {
    private Item drop;

    public Enemy(int hp, int def, int attack, String name, String description, Item drop) {
        super(hp, def, attack, name, description);
        this.drop = drop;
    }

    public Item getDrop() {
        return drop;
    }

    public Item death() {
        return drop;
    }
}