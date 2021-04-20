import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * The screen that displays the players inventory and karma.
 */
public class PlayerScreen extends JFrame implements ActionListener, WindowListener {
    private Item[] itemList;
    private JFrame playerFrame;
    private JPanel playerPanel = new JPanel();
    //private JButton[] buttonList = new JButton[10];
    //private JButton[] sellButtonList = new JButton[10];
    //private boolean[] isPurchased = new boolean[10];
    private JLabel[] labelList = new JLabel[10];
    private Player player;
    private Ship ship;
    private JPanel textP;
    private Universe universe;
    private Region region;
    private JLabel credLabel;
    private JButton backButton = new JButton("Back Button");

    public PlayerScreen(Item[] itemList, Player player, Ship ship, Region region) {
        this.itemList = itemList;
        this.player = player;
        this.ship = ship;
        this.universe = Universe.getUniverseInstance();
        this.region = region;
        playerFrame = new JFrame("Player");
        playerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
    }

    public PlayerScreen(Player player, Ship ship, Region region) {
        this.player = player;
        this.ship = ship;
        this.universe = Universe.getUniverseInstance();
        this.region = region;
        playerFrame = new JFrame("Player");
        playerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
    }

    public void createGUI() {
        playerFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel pane = createPlayerPane();
        playerPanel.setLayout(new GridLayout(15, 15, 5, 0));
        //credLabel = new JLabel(player.getCredits() + " Credits");
        int i = 0;
        for (Item item: player.getItemList()) {
            //GridBagConstraints c = new GridBagConstraints();
            //c.gridx = 0;
            //c.gridy = 0;
            labelList[i] = new JLabel("Item " + i + ": " + item.getName());
            //labelList[i] = new JLabel("code");
            playerFrame.add(addLabel(labelList[i]));
            i++;
        }
        //COMMENT THIS OUT WHEN ITEMLIST IS PROPERLY INSTANTIATED
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerFrame.dispose();
                JFrame market = new MarketScreen(player, ship, region);
            }
        });
        //marketFrame.add(makeTextPane("Hello"));

        playerFrame.add(pane);
        playerFrame.add(addButton());
        //playerFrame.add(addLabel(credLabel));
        playerFrame.pack();
        playerFrame.setVisible(true);
    }

    public JPanel createPlayerPane() {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createTitledBorder("Player Screen"));
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);

        return p;
    }

    public JPanel makeTextPane(String name) {
        JLabel newLabel = new JLabel(name);
        playerPanel.add(newLabel);
        return playerPanel;
    }

    private void addText(String text, Container container) {
        JLabel newText = new JLabel(text);
        container.add(newText);
    }

    public JPanel addButton() {
        playerPanel.add(backButton);
        return playerPanel;
    }

    public JPanel addLabel(JLabel words) {
        //JLabel words = new JLabel(label);
        playerPanel.add(words);
        return playerPanel;
    }

    public JPanel addGoodButton(JButton button) {
        //JButton mapButton = new JButton(label);
        playerPanel.add(button);
        return playerPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}