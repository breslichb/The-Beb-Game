import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import javax.swing.*;
import java.awt.*;

/**
 * A class that will display the Player's current quests within the game.
 *
 * @author Elijah Johnson
 * @version 1.0
 * @since 6.3.2023
 */
public class MainQuests {
    private JPanel questsPanel;
    private JLabel questLabel;
    private JTextArea questDisplay;
    private JScrollPane questScroll;
    private JFrame q;

    /**
     * The constructor for the MainQuests. This holds all UI functionality.
     *
     * @param p a Player object, the current player.
     */
    MainQuests(Player p) {
        questDisplay.setEditable(false);
        questLabel.setText(p.getName() + "'s Quests");
    }

    /**
     * A method to be called that will update the questDisplay JTextArea with the Player's current quests.
     *
     * @param p a Player object, the current player
     */
    public void updateQuestDisplay(Player p) {
        questDisplay.setText("");
        Quest[] quests = p.getActiveQuests();
        for (Quest q : quests) {
            questDisplay.setText(questDisplay.getText() + " - " + q.getName() + "\n" + q.getDescription() + "\n");
        }
    }

    /**
     * A method to be called in Main that creates and displays the quest screen UI.
     */
    public void createFrame() {
        q = new JFrame("Quests");
        q.setContentPane(questsPanel);
        q.pack();
        q.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        q.setVisible(false);
    }

    /**
     * A method to be called in main that will set the UI to be visible upon re-opening the quest screen.
     */
    public void setVisible() {
        q.setVisible(true);
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
        questsPanel = new JPanel();
        questsPanel.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        questLabel = new JLabel();
        questLabel.setText("Player's Quests");
        questsPanel.add(questLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        questScroll = new JScrollPane();
        questsPanel.add(questScroll, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(400, 300), null, 0, false));
        questDisplay = new JTextArea();
        questScroll.setViewportView(questDisplay);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return questsPanel;
    }

}
