import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * A class that will display the Player's inventory within the game.
 *
 * @author Elijah Johnson
 * @version 1.0
 * @since 6.3.2023
 */
public class MainInventory {
    private JPanel inventoryPanel;
    private JScrollPane inventoryScroll;
    private JTextArea inventoryDisplay;
    private JLabel inventoryLabel;
    private JButton upButton;
    private JButton downButton;
    private JButton useButton;
    private JButton dropButton;
    private JTextField selectedItemField;
    private JLabel actionLabel;
    private JLabel capacityLabel;
    private Item selectedItem;
    private int selectedLoc;
    private JFrame i;

    /**
     * The constructor for MainInventory. This holds all the UI functionality.
     *
     * @param p a Player object, the current user's Player character
     * @param m a Main object, the main program called with the 'this' keyword. This allows updates to Main file UI.
     */
    MainInventory(Player p, Main m) {
        inventoryDisplay.setEditable(false);
        selectedItemField.setEditable(false);
        inventoryLabel.setText(p.getName() + "'s Inventory");
        capacityLabel.setText("Capacity: " + p.getWeightCurr() + "/" + p.getWeightMax());
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Item> inv = p.getInventory();
                if (selectedLoc > 0) {
                    selectedLoc--;
                    selectedItem = inv.get(selectedLoc);
                    updateSelectedItem();
                }
            }
        });
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Item> inv = p.getInventory();
                if (selectedLoc < inv.size() - 1) {
                    selectedLoc++;
                    selectedItem = inv.get(selectedLoc);
                    updateSelectedItem();
                }
            }
        });
        useButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Item> inv = p.getInventory();
                if (selectedItem instanceof Equipable) {
                    p.equipItem((Equipable) selectedItem);
                    actionLabel.setText("Equipped " + selectedItem.getName() + "!");
                } else if (selectedItem instanceof Consumable) {
                    actionLabel.setText("Used " + selectedItem.getName() + "!");
                    p.use((Consumable) selectedItem);
                    updateSelectedItem();
                    updateInventoryDisplay(p);
                    if (selectedLoc >= inv.size()) {
                        if (inv.size() > 0) {
                            selectedLoc = inv.size() - 1;
                            selectedItem = inv.get(selectedLoc);
                        } else {
                            selectedLoc = 0;
                            selectedItem = null;
                        }
                    }
                }
                m.updatePlayerStats();
                updateInventoryDisplay(p);
                updateSelectedItem();
            }
        });
        dropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionLabel.setText("Dropped " + selectedItem.getName() + ". It is gone forever!");
                p.removeFromInventory(selectedItem);
                List<Item> inv = p.getInventory();
                if (selectedLoc >= inv.size()) {
                    if (inv.size() > 0) {
                        selectedLoc = inv.size() - 1;
                        selectedItem = inv.get(selectedLoc);
                    } else {
                        selectedLoc = 0;
                        selectedItem = null;
                    }
                }
                updateSelectedItem();
                updateInventoryDisplay(p);
            }
        });
    }

    /**
     * A method that will update the selectedItemField JTextField
     */
    public void updateSelectedItem() {
        selectedItemField.setText(selectedItem != null ? selectedItem.getName() : "Nothing" + " Selected.");
    }

    /**
     * A method that will update the inventoryDisplay JTextArea to display the Player's current inventory.
     *
     * @param p a Player object, the current player.
     */
    public void updateInventoryDisplay(Player p) {
        capacityLabel.setText("Capacity: " + p.getWeightCurr() + "/" + p.getWeightMax());
        inventoryDisplay.setText("");
        List<Item> inv = p.getInventory();
        if (inv.size() > 0) {
            selectedItem = inv.get(0);
        } else {
            selectedItem = null;
        }
        selectedLoc = 0;
        for (Item i : inv) {
            if (i instanceof Consumable) {
                inventoryDisplay.setText(inventoryDisplay.getText() + i.getName() + " - Effect: " + ((Consumable) i).getEffect() + ". Uses: " + ((Consumable) i).getUses() + ". Weight: " + i.getSize() + "\n");
            } else if (i instanceof Equipable) {
                if (((Equipable) i).isEquipped) {
                    inventoryDisplay.setText(inventoryDisplay.getText() + "*" + i.getName() + "* - ");
                } else {
                    inventoryDisplay.setText(inventoryDisplay.getText() + i.getName() + " - ");
                }
                int[] mods = ((Equipable) i).getMods();
                if (mods[0] != 0) {
                    inventoryDisplay.setText(inventoryDisplay.getText() + "HP Up " + mods[0] + ". ");
                }
                if (mods[1] != 0) {
                    inventoryDisplay.setText(inventoryDisplay.getText() + "STR Up " + mods[1] + ". ");
                }
                if (mods[2] != 0) {
                    inventoryDisplay.setText(inventoryDisplay.getText() + "DEF Up " + mods[2] + ". ");
                }
                inventoryDisplay.setText(inventoryDisplay.getText() + ". Weight: " + i.getSize() + "\n");
            }
        }
        updateSelectedItem();
    }

    /**
     * A method to be called in Main that creates and displays the inventory UI.
     */
    public void createFrame() {
        i = new JFrame("Inventory");
        i.setContentPane(inventoryPanel);
        i.pack();
        i.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        i.setVisible(false);
    }

    /**
     * Method that will be called when the player dies that will dispose of the window.
     */
    public void dispose() {
        i.dispose();
    }

    /**
     * A method to be called in main that will set the UI to be visible upon re-opening the inventory.
     */
    public void setVisible() {
        i.setVisible(true);
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
        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new GridLayoutManager(7, 4, new Insets(10, 10, 10, 10), -1, -1));
        inventoryScroll = new JScrollPane();
        inventoryPanel.add(inventoryScroll, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(400, 300), null, 0, false));
        inventoryDisplay = new JTextArea();
        inventoryScroll.setViewportView(inventoryDisplay);
        inventoryLabel = new JLabel();
        inventoryLabel.setText("Player's Inventory");
        inventoryPanel.add(inventoryLabel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        upButton = new JButton();
        upButton.setText("^");
        inventoryPanel.add(upButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        useButton = new JButton();
        useButton.setText("Use/Equip");
        inventoryPanel.add(useButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        selectedItemField = new JTextField();
        inventoryPanel.add(selectedItemField, new GridConstraints(3, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        downButton = new JButton();
        downButton.setText("v");
        inventoryPanel.add(downButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dropButton = new JButton();
        dropButton.setText("Drop");
        inventoryPanel.add(dropButton, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        actionLabel = new JLabel();
        actionLabel.setText("Use, Equip, or Drop your items!");
        inventoryPanel.add(actionLabel, new GridConstraints(4, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Selected Item:");
        inventoryPanel.add(label1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        capacityLabel = new JLabel();
        capacityLabel.setText("Capacity:");
        inventoryPanel.add(capacityLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return inventoryPanel;
    }

}
