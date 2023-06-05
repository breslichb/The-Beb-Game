import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestInventory {
    static Player p;
    static Armor i;
    static Armor overweight;

    @BeforeAll
    static void setup() {
        p = Player.createPlayer("Test Player");
        i = Armor.createArmor();
        overweight = new Armor("Test item", "Test", 200, Equipable.Slot.CHEST, new int[4]); // Players have a max weight of 150 total
    }

    // Make sure items are equipped.
    @Test
    void testItemAdding() {
        p.equipItem(i);
        assertTrue(Arrays.stream(p.getEquippedArmor()).toList().contains(i));
    }

    // Make sure overweight items aren't equipped.
    @Test
    void testOverweight() {
        p.addToInventory(overweight);
        assertFalse(p.getInventory().contains(overweight));
    }
}
