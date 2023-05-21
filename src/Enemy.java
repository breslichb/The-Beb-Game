class Enemy extends Mobs {
    public Enemy(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
    }
    public String dropAmulet() {
        String amulet = "Amulet of Suseness";
        return amulet;
    }
}