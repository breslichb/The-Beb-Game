
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private static Connection con;

    public Main(Player p, GameMap m, Connection c) {
        //set static fields
        player = p;
        map = m;
        currentRoom = map.getPlayerRoom();
        con = c;

        //Inventory screen
        MainInventory inventory = new MainInventory(player, this);
        inventory.createFrame();

        //Quest screen
        MainQuests quests = new MainQuests(player);
        quests.createFrame();

        //set the text areas to not be editable
        playArea.setEditable(false);
        playerStats.setEditable(false);

        //beginning stat display
        updatePlayerStats();

        //initial onEntry call
        playArea.setText(currentRoom.onEntry() + "\n");

        /* ==== Movement Action Listeners ==== */
        northButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playArea.setText(playArea.getText() + "====================\n");
                Room temp = map.tryMovePlayer(GameMap.Direction.NORTH);
                if (temp == null) {
                    playArea.setText(playArea.getText() + "Cannot go North.\n");
                } else {
                    currentRoom = temp;
                    updatePlayerStats();
                    playArea.setText(playArea.getText() + player.getName() + " travelled North.\n"
                            + currentRoom.onEntry() + "\n");
                }
            }
        });
        eastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playArea.setText(playArea.getText() + "====================\n");
                Room temp = map.tryMovePlayer(GameMap.Direction.EAST);
                if (temp == null) {
                    playArea.setText(playArea.getText() + "Cannot go East.\n");
                } else {
                    currentRoom = temp;
                    updatePlayerStats();
                    playArea.setText(playArea.getText() + player.getName() + " travelled East.\n"
                            + currentRoom.onEntry() + "\n");
                }
            }
        });
        southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playArea.setText(playArea.getText() + "====================\n");
                Room temp = map.tryMovePlayer(GameMap.Direction.SOUTH);
                if (temp == null) {
                    playArea.setText(playArea.getText() + "Cannot go South.\n");
                } else {
                    currentRoom = temp;
                    updatePlayerStats();
                    playArea.setText(playArea.getText() + player.getName() + " travelled South.\n"
                            + currentRoom.onEntry() + "\n");
                }
            }
        });
        westButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playArea.setText(playArea.getText() + "====================\n");
                Room temp = map.tryMovePlayer(GameMap.Direction.WEST);
                if (temp == null) {
                    playArea.setText(playArea.getText() + "Cannot go West.\n");
                } else {
                    currentRoom = temp;
                    updatePlayerStats();
                    playArea.setText(playArea.getText() + player.getName() + " travelled West.\n"
                            + currentRoom.onEntry() + "\n");
                }
            }
        });

        /* ==== Player Action Action Listeners ==== */

        talkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playArea.setText(playArea.getText() + "====================\n");
                ArrayList<NPC> npcs = currentRoom.getNPCs();
                if (npcs.isEmpty()) {
                    playArea.setText(playArea.getText() + "There is no one here.\n");
                } else {
                    NPC npc = npcs.get(0);
                    if (npc.getAvailableQuest() == null) {
                        playArea.setText(playArea.getText() + "\"I already gave you my quest, adventurer.\"\n");
                    } else {
                        playArea.setText(playArea.getText() + "\"Hello, adventurer! I will reward you for completing my quest!\"\n" +
                                "New Quest: " + npc.getAvailableQuest().getName() + "\n");
                        player.talk(npc);
                    }
                }
            }
        });
        interactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playArea.setText(playArea.getText() + "====================\n");
                playArea.setText(playArea.getText() + currentRoom.interact() + "\n");
                updatePlayerStats();
            }
        });
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playArea.setText(playArea.getText() + "====================\n");
                ArrayList<Enemy> enemies = currentRoom.getEnemies();
                if (enemies.isEmpty()) {
                    playArea.setText(playArea.getText() + "There is nothing to attack!\n");
                } else {
                    Enemy enemy = enemies.get(0);
                    int damage = player.attack(enemy);
                    playArea.setText(playArea.getText() + player.getName() + " attacks " + enemy.getName() + "!\n"
                            + player.getName() + " dealt " + damage + " damage to " + enemy.getName() + "!\n");
                    if (enemy.isDead()) {
                        player.setLastKilledEnemy(enemy);
                        playArea.setText(playArea.getText() + player.getName() + " killed " + enemy.getName() + "!\n");
                        currentRoom.removeEnemy(enemy);

                        Quest[] quests = player.getActiveQuests();

                        for (Quest q : quests) {
                            q.incrementKillCount();
                            if (q.isCompleted()) {
                                if (player.addToInventory(q.getReward())) {
                                    player.completeQuest(q);
                                    playArea.setText(playArea.getText() + "====================\n" + "You completed a quest! Your reward: " + q.getReward().getName() + "\n");
                                } else {
                                    playArea.setText(playArea.getText() + "====================\n" + "You completed a quest!\n"
                                            + "But, you are carrying too much. You can receive the reward after your next kill if you have enough room.\n");
                                }
                            }
                        }

                    } else {
                        damage = enemy.attack(player);
                        playArea.setText(playArea.getText() + enemy.getName() + " attacks " + player.getName() + "!\n"
                                + enemy.getName() + " dealt " + damage + " damage to " + player.getName() + "!\n");
                        if (player.isDead()) {
                            f.dispose();
                            GameOver gameOver = new GameOver();
                            gameOver.createFrame();
                        }
                    }
                }
                updatePlayerStats();
            }
        });

        /* ==== Menu Action Listeners ==== */
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p.getInventory().size() == 0) {
                    playArea.setText(playArea.getText() + "====================\n" + "You have nothing in your inventory.\n");
                } else {
                    inventory.updateInventoryDisplay(player);
                    inventory.setVisible();
                }
            }
        });
        questsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quests.updateQuestDisplay(player);
                quests.setVisible();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playArea.setText(playArea.getText() + "====================\n");
                try {
                    DBConnector.putSaveState(player.getName(), map, con);
                    playArea.setText(playArea.getText() + "Game successfully saved!\n");
                } catch (SQLException ex) {
                    playArea.setText(playArea.getText() + "ERROR: Cannot save game.\n" + ex.getMessage() + '\n');
                }
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
                + "\nCurrent Location: (" + pos[1] + ", " + pos[0] + ")\n"
                + map.toString()
        );
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