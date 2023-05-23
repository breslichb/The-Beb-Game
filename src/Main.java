
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public Main() {
        //set the text areas to not be editable
        playArea.setEditable(false);
        playerStats.setEditable(false);

        /* ==== Movement Action Listeners ==== */
        northButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        eastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        westButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* ==== Player Action Action Listeners ==== */
        talkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        interactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* ==== Menu Action Listeners ==== */
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainInventory inventory = new MainInventory();
                inventory.createForm();
            }
        });
        questsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /*=====MAIN METHOD=====*/
    public static void main(String[] args) {

        //initializing the frame
        f = new JFrame("The BEB Game");
        f.setContentPane(new Main().mainPanel);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(false);

        new MainStartMenu();


    }

    public static class MainStartMenu {
        private static JFrame s;
        private JPanel startScreenPanel;
        private JPanel startMenuPanel;
        private JButton startButton;

        MainStartMenu() {
            s = new JFrame("Start Menu");
            s.setContentPane(new MainStartMenu().startMenuPanel);
            s.pack();
            s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            s.setVisible(true);
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.setVisible(true);
                    s.dispose();
                }
            });
        }

        public void createFrame() {
        }

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
            startScreenPanel = new JPanel();
            startScreenPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        }

        /**
         * @noinspection ALL
         */
        public JComponent $$$getRootComponent$$$() {
            return startScreenPanel;
        }
    }

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
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 3, new Insets(5, 5, 5, 5), -1, -1));
        playAreaLabel = new JLabel();
        playAreaLabel.setText("Play Area");
        mainPanel.add(playAreaLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(295, 16), null, 0, false));
        moveButtonsPanel = new JPanel();
        moveButtonsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(moveButtonsPanel, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        northButton = new JButton();
        northButton.setText("North");
        moveButtonsPanel.add(northButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(60, 10), null, 0, false));
        southButton = new JButton();
        southButton.setText("South");
        moveButtonsPanel.add(southButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(60, 10), null, 0, false));
        westButton = new JButton();
        westButton.setText("West");
        moveButtonsPanel.add(westButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(60, 10), null, 0, false));
        eastButton = new JButton();
        eastButton.setText("East");
        moveButtonsPanel.add(eastButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(60, 10), null, 0, false));
        moveLabel = new JLabel();
        moveLabel.setText("Movement");
        mainPanel.add(moveLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        talkButton = new JButton();
        talkButton.setText("Talk");
        mainPanel.add(talkButton, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 10), null, 0, false));
        attackButton = new JButton();
        attackButton.setText("Attack");
        mainPanel.add(attackButton, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 10), null, 0, false));
        interactButton = new JButton();
        interactButton.setText("Interact");
        mainPanel.add(interactButton, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 10), null, 0, false));
        inventoryButton = new JButton();
        inventoryButton.setText("Inventory");
        mainPanel.add(inventoryButton, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 10), null, 0, false));
        questsButton = new JButton();
        questsButton.setText("Quests");
        mainPanel.add(questsButton, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 10), null, 0, false));
        actionLabel = new JLabel();
        actionLabel.setText("Actions");
        mainPanel.add(actionLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        menuLabel = new JLabel();
        menuLabel.setText("Menus");
        mainPanel.add(menuLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playerStats = new JTextArea();
        playerStats.setText("");
        mainPanel.add(playerStats, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 150), null, 0, false));
        playerStatLabel = new JLabel();
        playerStatLabel.setText("Player");
        mainPanel.add(playerStatLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playArea = new JTextArea();
        mainPanel.add(playArea, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(295, 150), null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Save");
        mainPanel.add(saveButton, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

    public static Potion createPotion(){
        Random rand = new Random();
        String[] type = new String[]{"HP", "STR", "DEF", "LUCK"};
        int typeNum = rand.nextInt(4);
        int useSize = rand.nextInt(3)+1;
        int effect = rand.nextInt(9)+2;
        int duration;
        if(typeNum == 0){
            duration=0;
        }else{
            duration = rand.nextInt(5)+1;
        }

        return new Potion(type[typeNum], useSize, effect, duration);
    }

    public static Food createFood(){
        Random rand = new Random();
        String[] type = new String[]{"Apple", "Bread", "Meat", "Ice Cream"};
        int typeNum = rand.nextInt(4);

        return new Food(type[typeNum], "Some delicious food.", rand.nextInt(3)+1, rand.nextInt(5)+typeNum+1);
    }

    public static Weapon createWeapon(){
        Random rand = new Random();
        String[] types = new String[]{"Sword", "Axe", "Mace", "Hammer"};
        int typeNum = rand.nextInt(4);
        int[] mods = new int[]{0, rand.nextInt(5)+(typeNum+1), 0, (rand.nextInt(10)+1)-typeNum};
        int size = rand.nextInt(5)+(typeNum+1);
        int durability = rand.nextInt(10)+(typeNum+1);

        return new Weapon(types[typeNum], "A weapon.", size, mods, durability);
    }

    public static Armor createArmor(){
        Random rand = new Random();
        int HP=0;
        if(rand.nextBoolean()){HP = rand.nextInt(10)+1;}

        Equipable.Slot slot = null;
        int STR=0;
        int DEF=0;
        int size=0;
        int durability=0;
        String name="";
        int slotNum = rand.nextInt(4)+1;
        switch(slotNum){
            case 1:
                slot = Equipable.Slot.HEAD;
                STR = 0;
                DEF = rand.nextInt(5)+1;
                size = rand.nextInt(6)+5;
                durability = rand.nextInt(6)+5;
                name = "Helmet";
                break;
            case 2:
                slot = Equipable.Slot.CHEST;
                STR = rand.nextInt(3);
                DEF = rand.nextInt(10)+1;
                size = rand.nextInt(11)+10;
                durability = rand.nextInt(11)+10;
                name = "Chestplate";
                break;
            case 3:
                slot = Equipable.Slot.LEGS;
                STR = 0;
                DEF = rand.nextInt(3)+1;
                size = rand.nextInt(4)+3;
                durability = rand.nextInt(4)+3;
                name = "Boots";
                break;
            case 4:
                slot = Equipable.Slot.ARMS;
                STR = rand.nextInt(5)+1;
                DEF = rand.nextInt(5)+1;
                size = rand.nextInt(4)+3;
                durability = rand.nextInt(4)+3;
                name = "Gauntlets";
                break;
        }

        int[] mods = new int[]{HP, STR, DEF, rand.nextInt(10)+1};

        return new Armor(name, "An armor piece.", size, slot, mods, durability);
    }
    */

