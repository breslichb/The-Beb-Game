import java.io.Serializable;

/**
 * The Mob class is the superclass for player and enemy non-player characters. This stores health, attack, defense, and other
 * creature-centric values.
 * @author Bayasgalan Battogtokh
 */
class Mobs implements Serializable {
    /** The mob's name */
    private String name;

    /** The mob's health */
    private int health;

    /** The mob's attack value */
    private int attack;

    /** The mob's defense value */
    private int defense;

    /**
     * Creates a new mob with the given stats.
     * @param name The name of the mob.
     * @param health The health of the mob.
     * @param attack The attack value of the mob.
     * @param defense The defense value of the mob.
     */
    public Mobs(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    /**
     * Getter for a mob's name.
     * @return The mob's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for a mob's health.
     * @return The mob's health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Getter for a mob's attack value.
     * @return The mob's attack value.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Increases a mob's attack value by x.
     * @param x The value to increase attack by.
     */
    public void increaseAttack(int x){attack += x;}

    /**
     * Gets a mob's defense value.
     * @return The mob's defense value.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Increases a mob's defense value by x.
     * @param x The value to increase defense by.
     */
    public void increaseDefense(int x){defense += x;}

    /**
     * Makes a mob take damage.
     * @param damage The damage for the mob to take.
     */
    public void takeDamage(int damage) {
        int remainingHealth = Math.max(health - damage, 0);
        health = remainingHealth;
    }

    /**
     * Makes a mob attack an enemy.
     * @param enemy The enemy to attack.
     * @return The damage dealt to the enemy.
     */
    public int attack(Mobs enemy) {
        int damageDealt = calculateDamage(enemy);
        enemy.takeDamage(damageDealt);
        return damageDealt;
    }

    /**
     * Calculates how much damage a mob does to an enemy.
     * Mobs always do a minimum of 5 and a maximum of 15 damage.
     * @param enemy The enemy we're attacking.
     * @return The damage dealt.
     */
    private int calculateDamage(Mobs enemy) {
        int minDamage = 5;
        int maxDamage = 15;
        int attackDamage = getAttack() - enemy.getDefense();
        int damageDealt = Math.max(minDamage, Math.min(maxDamage, attackDamage));
        return damageDealt;
    }

    /**
     * Checks whether a mob is dead or not.
     * @return True if dead, false if alive.
     */
    public boolean isDead() {
        return getHealth() <= 0;
    }
}