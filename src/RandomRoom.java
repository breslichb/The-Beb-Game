import java.util.Random;

/**
 * The RandomRoom class randomly populates a room with an item and an enemy based on chance rolls.
 * Chances: 25% item & enemy, 50% enemy, 25% empty.
 * @author Benjamin Breslich
 */
public class RandomRoom extends Room{
    /**
     * Random Room constructor.
     * @param parent The gamemap we reside in.
     */
    public RandomRoom(GameMap parent){
        super(parent);
        Random rand = new Random();
        int picker = rand.nextInt(4);
        switch(picker) {
            case 0:
                addItem(makeDrop());
            case 1, 2:
                addEnemy(Enemy.createEnemy());
        }
    }

    /**
     * MakeDrop creates a random item for room generation purposes.
     * @return The item to add to the room.
     */
    public Item makeDrop() {
        Random rand = new Random();
        int picker = rand.nextInt(4);
        switch(picker){
            case 0:
                return Armor.createArmor();
            case 1:
                return Potion.createPotion();
            case 2:
                return Weapon.createWeapon();
            case 3:
                return Food.createFood();
            default:
                return Potion.createPotion();
        }
    }
}
