
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {

    private static JFrame f;
    private JTextArea playArea;
    private JLabel playAreaLabel;
    private JLabel playerStatLabel;
    private JTextArea playerStats;
    private JButton northButton;
    private JButton southButton;
    private JButton eastButton;
    private JButton westButton;
    private JLabel moveLabel;
    private JPanel moveButtonsPanel;
    private JButton talkButton;
    private JButton attackButton;
    private JButton interactButton;
    private JButton inventoryButton;
    private JButton questsButton;
    private JLabel actionLabel;
    private JLabel menuLabel;
    private JPanel mainPanel;
    private JButton saveButton;
    private JScrollPane playAreaScroll;
    private static Player player;
    private static GameMap map;
    private static Room currentRoom;

    public Main(Player p, GameMap m) {
        //set static fields
        player = p;
        map = m;
        currentRoom = map.getPlayerRoom();

        //Inventory screen
        MainInventory inventory = new MainInventory(player);
        inventory.createFrame();

        //Quest screen
        MainQuests quests = new MainQuests(player);
        quests.createFrame();

        //set the text areas to not be editable
        playArea.setEditable(false);
        playerStats.setEditable(false);

        updatePlayerStats();

        /* ==== Movement Action Listeners ==== */
        northButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Room temp = map.tryMovePlayer(GameMap.Direction.NORTH);
                if (temp == null) {
                    playArea.setText(playArea.getText() + "Cannot go North.\n");
                } else {
                    currentRoom = temp;
                    updatePlayerStats();
                    playArea.setText(playArea.getText() + player.getName() + " travelled North.\n");
                }
            }
        });
        eastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Room temp = map.tryMovePlayer(GameMap.Direction.EAST);
                if (temp == null) {
                    playArea.setText(playArea.getText() + "Cannot go East.\n");
                } else {
                    currentRoom = temp;
                    updatePlayerStats();
                    playArea.setText(playArea.getText() + player.getName() + " travelled East.\n");
                }
            }
        });
        southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Room temp = map.tryMovePlayer(GameMap.Direction.SOUTH);
                if (temp == null) {
                    playArea.setText(playArea.getText() + "Cannot go South.\n");
                } else {
                    currentRoom = temp;
                    updatePlayerStats();
                    playArea.setText(playArea.getText() + player.getName() + " travelled South.\n");
                }
            }
        });
        westButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Room temp = map.tryMovePlayer(GameMap.Direction.WEST);
                if (temp == null) {
                    playArea.setText(playArea.getText() + "Cannot go West.\n");
                } else {
                    currentRoom = temp;
                    updatePlayerStats();
                    playArea.setText(playArea.getText() + player.getName() + " travelled West.\n");
                }
            }
        });

        /* ==== Player Action Action Listeners ==== */
        talkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playArea.setText(playArea.getText() + player.getName() + " Spoke to an NPC - *Ask Bayasaa to add talk() function*.\n");
            }
        });
        interactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playArea.setText(playArea.getText() + player.getName() + " Spoke to an NPC - *Ask Bayasaa to add interact() function (also ask what this would do because I forgot)*.\n");
                updatePlayerStats();
            }
        });
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Enemy> enemies = map.getRoomByCoords(0, 0).getEnemies();  //current coords thing again
                player.takeDamage(10);
                updatePlayerStats();
                if (enemies.isEmpty()) {
                    playArea.setText(playArea.getText() + "There is nothing to attack!\n");
                } else {
                    playArea.setText(playArea.getText() + "Oh no an enemy ahhhhhh!\n");     //ask bayasaa to implement an attack() method
                }
            }
        });

        /* ==== Menu Action Listeners ==== */
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.setVisible();
            }
        });
        questsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quests.setVisible();
            }
        });
    }

    public void startGame() {
        //initializing the frame
        f = new JFrame("The BEB Game");
        f.setContentPane(mainPanel);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void updatePlayerStats() {
        int[] pos = map.getPlayerLocation();

        playerStats.setText(player.getName()
                + "\nHealth  : " + player.getHealth() + " / " + player.getMaxHealth()
                + "\nStrength: " + player.getAttack()
                + "\nDefense : " + player.getDefense()
                + "\nCurrent Location: (" + pos[1] + ", " + pos[0] + ")");
    }


    /*=====MAIN METHOD=====*/
    public static void main(String[] args) {
        MainStartMenu start = new MainStartMenu();
        start.createFrame();
    }

    /* ===== GUI STUFF - DON'T TOUCH!! ===== */

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(6, 3, new Insets(5, 5, 5, 5), -1, -1));
        playAreaLabel = new JLabel();
        playAreaLabel.setText("Play Area");
        mainPanel.add(playAreaLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(295, 16), null, 0, false));
        moveButtonsPanel = new JPanel();
        moveButtonsPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(moveButtonsPanel, new GridConstraints(3, 0, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        northButton = new JButton();
        northButton.setText("North");
        moveButtonsPanel.add(northButton, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(60, 10), null, 0, false));
        southButton = new JButton();
        southButton.setText("South");
        moveButtonsPanel.add(southButton, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(60, 10), null, 0, false));
        westButton = new JButton();
        westButton.setText("West");
        moveButtonsPanel.add(westButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(60, 10), null, 0, false));
        eastButton = new JButton();
        eastButton.setText("East");
        moveButtonsPanel.add(eastButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(60, 10), null, 0, false));
        moveLabel = new JLabel();
        moveLabel.setText("Movement");
        mainPanel.add(moveLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        talkButton = new JButton();
        talkButton.setText("Talk");
        mainPanel.add(talkButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 10), null, 0, false));
        attackButton = new JButton();
        attackButton.setText("Attack");
        mainPanel.add(attackButton, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 10), null, 0, false));
        interactButton = new JButton();
        interactButton.setText("Interact");
        mainPanel.add(interactButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 10), null, 0, false));
        inventoryButton = new JButton();
        inventoryButton.setText("Inventory");
        mainPanel.add(inventoryButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 10), null, 0, false));
        questsButton = new JButton();
        questsButton.setText("Quests");
        mainPanel.add(questsButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 10), null, 0, false));
        actionLabel = new JLabel();
        actionLabel.setText("Actions");
        mainPanel.add(actionLabel, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        menuLabel = new JLabel();
        menuLabel.setText("Menus");
        mainPanel.add(menuLabel, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playerStats = new JTextArea();
        playerStats.setText("");
        mainPanel.add(playerStats, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 150), null, 0, false));
        playerStatLabel = new JLabel();
        playerStatLabel.setText("Player");
        mainPanel.add(playerStatLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Save");
        mainPanel.add(saveButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 10), null, 0, false));
        playAreaScroll = new JScrollPane();
        mainPanel.add(playAreaScroll, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        playArea = new JTextArea();
        playAreaScroll.setViewportView(playArea);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}


    /*=====OTHER METHODS=====*/

    /*
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

    private static NPC createNPC() {
        NPC npc = new NPC("Bob");

        // Create and add quests to the NPC
        Quest quest1 = createCollectAmuletQuest();
        npc.giveQuest(quest1);

        Quest quest2 = createKillMonsterQuest();
        npc.giveQuest(quest2);

        return npc;
    }

    private static void battle(Player player, Mobs enemy) {
        System.out.println("You encounter a " + enemy.getName() + "!");

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            // Player's turn
            int playerDamage = calculateDamage(player.getAttack(), enemy.getDefense());
            enemy.takeDamage(playerDamage);
            System.out.println("You attack the " + enemy.getName() + " and deal " + playerDamage + " damage.");

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
            } else if (questName.equalsIgnoreCase("collect")) {
                Quest quest = createCollectAmuletQuest();
                player.addToQuests(quest);
                System.out.println("You accepted the quest: " + quest.getName());
                availableQuests.remove(quest);
            } else if (questName.equalsIgnoreCase("kill")) {
                Quest quest = createKillMonsterQuest();
                player.addToQuests(quest);
                System.out.println("You accepted the quest: " + quest.getName());
                availableQuests.remove(quest);
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



    private static Quest createCollectAmuletQuest() {
        String questName = "Collect Amulet";
        String questDescription = "Collect amulets.";
        String amuletType = "Amulet of Power";
        int requiredAmuletCount = 3;
        return new CollectAmuletQuest(questName, questDescription, amuletType, requiredAmuletCount);
    }

    private static Quest createKillMonsterQuest() {
        String questName = "Kill Monster";
        String questDescription = "Kill monsters.";
        String monsterType = "Monster A";
        int requiredKillCount = 5;
        return new KillMonsterQuest(questName, questDescription, monsterType, requiredKillCount);
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


    Elijah - Hypothetical method layouts for creating Consumables and Equipables


    */

