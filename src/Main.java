import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Player player = createPlayer(scanner);
        System.out.println("Welcome, " + player.getName() + "!");

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1. Attack an enemy");
            System.out.println("2. Talk to an NPC");
            System.out.println("3. Check Inventory");
            System.out.println("4. Check Active Quests");
            System.out.println("5. Quit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Mobs enemy = createEnemy();
                    battle(player, enemy);
                    break;
                case 2:
                    NPC npc = createNPC();
                    talkToNPC(player, npc);
                    break;
                case 3:
                    System.out.println("Amulet count: " + player.getAmuletCount());
                    break;
                case 4:
                    player.checkActiveQuests();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static Player createPlayer(Scanner scanner) {
        System.out.print("Enter your character's name: ");
        String name = scanner.next();

        System.out.print("Enter your character's class: ");
        String playerClass = scanner.next();

        // Generate random player stats
        int health = generateRandomStat(100, 150);
        int attack = generateRandomStat(10, 20);
        int defense = generateRandomStat(10, 20);

        return new Player(name, playerClass, health, attack, defense);
    }

    private static Mobs createEnemy() {
        // Generate random enemy stats
        String[] enemyNames = {"Goblin", "Skeleton", "Orc", "Dragon"};
        String name = enemyNames[new Random().nextInt(enemyNames.length)];
        int health = generateRandomStat(50, 100);
        int attack = generateRandomStat(5, 15);
        int defense = generateRandomStat(5, 15);

        return new Enemy(name, health, attack, defense);
    }

    private static NPC createNPC() {
        // Create an NPC with desired characteristics
        return new NPC("Bob");
    }

    private static void battle(Player player, Mobs enemy) {
        System.out.println("You encounter a " + enemy.getName() + "!");

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            // Player's turn
            int playerDamage = calculateDamage(player.getAttack(), enemy.getDefense());
            enemy.takeDamage(playerDamage);
            System.out.println("You attack the " + enemy.getName() + " and deal " + playerDamage + " damage.");
            
         /*   if (enemy.getHealth() <= 0) {
                System.out.println("You have defeated the " + enemy.getName() + "! Congratulations!");
                break; // Exit the loop if the enemy is defeated
            }*/
            if (enemy.getHealth() <= 0) {
                System.out.println("You have defeated the " + enemy.getName() + "! Congratulations!");

                String amulet = ((Enemy) enemy).dropAmulet();
                player.addToInventory(amulet);

                System.out.println("The " + enemy.getName() + " dropped an amulet: " + amulet);
                break;
            }

            // Enemy's turn
            int enemyDamage = calculateDamage(enemy.getAttack(), player.getDefense());
            player.takeDamage(enemyDamage);
            System.out.println("The " + enemy.getName() + " attacks you and deals " + enemyDamage + " damage.");

            if (player.getHealth() <= 0) {
                System.out.println("You have been defeated. Game over.");
                break; // Exit the loop if the player is defeated
            }

            // Display remaining health
            System.out.println("Your health: " + player.getHealth());
            System.out.println(enemy.getName() + "'s health: " + enemy.getHealth());
            System.out.println();
        }
    }

    private static int calculateDamage(int attackerAttack, int defenderDefense) {
        int baseDamage = attackerAttack - defenderDefense;
        int randomDamage = generateRandomStat(5, 25);
        int totalDamage = baseDamage + randomDamage;
        return Math.max(totalDamage, 0); // Ensures damage is not negative
    }

    private static void talkToNPC(Player player, NPC npc) {
        System.out.println("You approach " + npc.getName() + ".");
        List<Quest> availableQuests = npc.getAvailableQuests();

        if (availableQuests.isEmpty()) {
            System.out.println("NPC " + npc.getName() + " doesn't have any quests for you.");
        } else {
            System.out.println("NPC " + npc.getName() + " has the following quests:");
            for (Quest quest : availableQuests) {
                System.out.println(quest.getName() + ": " + quest.getDescription());
            }
            System.out.print("Enter the quest name you want to accept (or 'cancel' to cancel): ");
            Scanner scanner = new Scanner(System.in);
            String questName = scanner.next();

            if (questName.equalsIgnoreCase("cancel")) {
                System.out.println("Quest canceled.");
            } else {
                Quest quest = findQuestByName(availableQuests, questName);
                if (quest != null) {
                    player.addToQuests(quest);
                    System.out.println("You accepted the quest: " + quest.getName());
                    availableQuests.remove(quest);
                } else {
                    System.out.println("Invalid quest name.");
                }
            }
        }
    }

    private static Quest findQuestByName(List<Quest> quests, String questName) {
        for (Quest quest : quests) {
            if (quest.getName().equalsIgnoreCase(questName)) {
                return quest;
            }
        }
        return null;
    }

    private static int generateRandomStat(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
