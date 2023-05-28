import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainNewGame {
    private JPanel newGamePanel;
    private JTextField playerNameField;
    private JLabel nameLabel;
    private JButton startGameButton;
    private JLabel newGameTitle;
    private static JFrame n;

    MainNewGame() {

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = playerNameField.getText();
                Player player = Player.createPlayer(name);
                GameMap map = new GameMap(5, 5, 1, 1, 0, 0, player);
                Main main = new Main(player, map);
                main.startGame();
                n.dispose();
            }
        });
    }

    void createFrame() {
        n = new JFrame("The BEB Game");
        n.setContentPane(new MainNewGame().newGamePanel);
        n.pack();
        n.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        n.setVisible(true);
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
        newGamePanel = new JPanel();
        newGamePanel.setLayout(new GridLayoutManager(5, 2, new Insets(50, 50, 50, 50), -1, -1));
        playerNameField = new JTextField();
        newGamePanel.add(playerNameField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 20), null, 0, false));
        nameLabel = new JLabel();
        nameLabel.setText("Enter Your Name:");
        newGamePanel.add(nameLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        newGameTitle = new JLabel();
        newGameTitle.setText("New Game");
        newGamePanel.add(newGameTitle, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        newGamePanel.add(spacer1, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        final Spacer spacer2 = new Spacer();
        newGamePanel.add(spacer2, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        startGameButton = new JButton();
        startGameButton.setText("Start Game");
        newGamePanel.add(startGameButton, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 10), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return newGamePanel;
    }

}
