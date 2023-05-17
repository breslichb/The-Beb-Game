import java.util.Scanner;
public class NPC extends Mobs {
    private Quest quest;

    public NPC(int hp, int def, int attack, String name, String description, Quest quest) {
        super(hp, def, attack, name, description);
        this.quest = quest;
    }

    public boolean talk() {
        System.out.println("NPC: Hello, adventurer! How is your day going?");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player: ");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("good")) {
            System.out.println("NPC: That's great to hear! Can I offer you a quest?");
            return true;
        } else {
            System.out.println("NPC: I'm sorry to hear that.");
            return false;
        }
    }

    public Quest getQuest() {
        return quest;
    }

    public Quest giveQuest() {
        return quest;
    }
}
