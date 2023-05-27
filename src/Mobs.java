import java.io.Serializable;

class Mobs implements Serializable {
    private String name;
    private int health;
    private int attack;
    private int defense;

    public Mobs(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public void takeDamage(int damage) {
        int remainingHealth = Math.max(health - damage, 0);
        health = remainingHealth;
    }

    //death method for player class
    public boolean isDead() {
        return getHealth() <= 0;
    }
}