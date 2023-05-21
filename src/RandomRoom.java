import java.util.Random;

/**
 * The RandomRoom class randomly populates a room with an item and an enemy based on chance rolls.
 * Chances: 25% item & enemy, 25% enemy, 50% empty.
 */
public class RandomRoom extends Room{
    public RandomRoom(GameMap parent){
        super(parent);
        /**
        Random rand = new Random();
        int picker = rand.nextInt(3);
        switch(picker) {
            case 0:
                addItem(Item.makeDrop());
            case 1:
                addEnemy(new Enemy());
        }
         */
    }
}
