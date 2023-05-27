import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class MainInventory {

    private JPanel inventoryPanel;
    private JLabel testlabel;

    MainInventory() {

    }

    public void createFrame() {
        JFrame i = new JFrame("Inventory");
        i.setContentPane(new MainInventory().inventoryPanel);
        i.pack();
        i.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        inventoryPanel.setLayout(new GridLayoutManager(1, 1, new Insets(100, 100, 100, 100), -1, -1));
        testlabel = new JLabel();
        testlabel.setText("Success");
        inventoryPanel.add(testlabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return inventoryPanel;
    }

}
