import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player extends Mobs {
    private Armor head;
    private Armor body;
    private Armor arms;
    private Armor legs;
    private Weapon[] hands;
    private List<Item> pack = new ArrayList<>();
    private List<Quest> quests = new ArrayList<>();

    public Player(int hp, int def, int attack, String name, String description, Armor head, Armor body, Armor arms, Armor legs, Weapon[] hands) {
        super(hp, def, attack, name, description);
        this.head = head;
        this.body = body;
        this.arms = arms;
        this.legs = legs;
        this.hands = hands;
    }

    // Add a constructor with random attack and defense values for the player, 100hp, attack 10-20 and adds extra hp 10-50
    public Player(String name, String description, Armor head, Armor body, Armor arms, Armor legs, Weapon[] hands) {
        super(100, getRandomValue(10, 20), getRandomValue(10, 50), name, description);
        this.head = head;
        this.body = body;
        this.arms = arms;
        this.legs = legs;
        this.hands = hands;
    }

    public Armor getHead() {
        return head;
    }

    public Armor getBody() {
        return body;
    }

    public Armor getArms() {
        return arms;
    }

    public Armor getLegs() {
        return legs;
    }

    public Weapon[] getHands() {
        return hands;
    }

    public List<Item> getPack() {
        return pack;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public boolean walk(Direction direction) {
        // Implementation for walking
        return false;
    }

    public void inventory() {
        // Implementation for displaying inventory
    }

    public void quests() {
        // Implementation for displaying quests
    }

    // Helper method to get a random value within a range
    private static int getRandomValue(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }


    public boolean talk() {
        System.out.println("Player: Hello!");
        Scanner scanner = new Scanner(System.in);
        System.out.print("NPC: How can I help you today? ");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Player: Sure, I'm interested in a quest.");

            return true;
        } else {
            System.out.println("Player: Not right now, maybe later.");
            return false;
        }
    }
    @Override
    public void receiveDamage(int damage) {
        super.receiveDamage(damage);
        if (hp <= 0) {
            // Player dies
            death();
        }
    }

    public void death() {

    }
}
