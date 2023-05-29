import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


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
    private Item selectedItem;
    private int selectedLoc;
    private JFrame i;

    MainInventory(Player p) {
        inventoryDisplay.setEditable(false);
        selectedItemField.setEditable(false);
        inventoryLabel.setText(p.getName() + "'s Inventory");
        List<Item> inv = p.getInventory();
        int size = inv.size();
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                if (selectedLoc < size) {
                    selectedLoc++;
                    selectedItem = inv.get(selectedLoc);
                    updateSelectedItem();
                }
            }
        });
        useButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedItem instanceof Equipable) {
                    p.equipItem((Equipable) selectedItem);
                } else if (selectedItem instanceof Consumable) {

                }
            }
        });
    }

    public void updateSelectedItem() {
        selectedItemField.setText(selectedItem.getName() + " Selected.");
    }

    public void updateInventoryDisplay(Player p) {
        inventoryDisplay.setText("");
        List<Item> inv = p.getInventory();
        selectedItem = inv.get(0);
        selectedLoc = 0;
        for (Item i : inv) {
            inventoryDisplay.setText(inventoryDisplay.getText() + " - " + i.getName() + "\n");
        }
        selectedItemField.setText(selectedItem.getName() + " Selected.");
    }

    public void createFrame() {
        i = new JFrame("Inventory");
        i.setContentPane(inventoryPanel);
        i.pack();
        i.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        i.setVisible(false);
    }

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
        inventoryPanel.setLayout(new GridLayoutManager(5, 4, new Insets(10, 10, 10, 10), -1, -1));
        inventoryScroll = new JScrollPane();
        inventoryPanel.add(inventoryScroll, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(200, 300), null, 0, false));
        inventoryDisplay = new JTextArea();
        inventoryScroll.setViewportView(inventoryDisplay);
        inventoryLabel = new JLabel();
        inventoryLabel.setText("Player's Inventory");
        inventoryPanel.add(inventoryLabel, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        upButton = new JButton();
        upButton.setText("^");
        inventoryPanel.add(upButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        useButton = new JButton();
        useButton.setText("Use/Equip");
        inventoryPanel.add(useButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        selectedItemField = new JTextField();
        inventoryPanel.add(selectedItemField, new GridConstraints(2, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        downButton = new JButton();
        downButton.setText("v");
        inventoryPanel.add(downButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dropButton = new JButton();
        dropButton.setText("Drop");
        inventoryPanel.add(dropButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return inventoryPanel;
    }

}
