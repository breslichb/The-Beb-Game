import java.util.ArrayList;
import java.util.List;

public class Mobs {
    private int hp;
    private int def;
    private int attack;
    private int damageTaken;
    private String name;
    private String description;

    public Mobs(int hp, int def, int attack, String name, String description) {
        this.hp = hp;
        this.def = def;
        this.attack = attack;
        this.name = name;
        this.description = description;
        this.damageTaken = 0;
    }
    public int getHp() {
        return hp;
    }

    public int getDef() {
        return def;
    }

    public int getAttack() {
        return attack;
    }

    public int getDamageTaken() {
        return damageTaken;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void attack(Mobs target) {
        int damageDealt = Math.max(this.attack - target.getDef(), 0);
        target.receiveDamage(damageDealt);
    }

    public void receiveDamage(int damage) {
        damageTaken += damage;
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }
}