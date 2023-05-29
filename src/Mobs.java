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
    public void increaseAttack(int x){attack += x;}

    public int getDefense() {
        return defense;
    }
    public void increaseDefense(int x){defense += x;}

    public void takeDamage(int damage) {
        int remainingHealth = Math.max(health - damage, 0);
        health = remainingHealth;
    }

    public int attack(Mobs enemy) {
        int damageDealt = calculateDamage(enemy);
        enemy.takeDamage(damageDealt);
        return damageDealt;
    }

    private int calculateDamage(Mobs enemy) {
        // Calculate damage taking into account player's attack and enemy's defense
        int attackDamage = getAttack() - enemy.getDefense();
        return Math.max(0, attackDamage);
    }

    //death method for player class
    public boolean isDead() {
        return getHealth() <= 0;
    }
}