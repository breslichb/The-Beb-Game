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
        int damageDealt = this.attack - target.getDef();
        target.receiveDamage(damageDealt);
    }

    public void receiveDamage(int damage) {
        damageTaken += damage;
        hp -= damage;
    }
}