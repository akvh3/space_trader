import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

/**
 * The success screen for the encounter with the merchant,
 * if you attempt to fight him.
 */
public class MerchSuccessScreen extends JFrame implements ActionListener, WindowListener {

    private JFrame merchantFrame;
    private JPanel merchantPanel = new JPanel();
    private Player player;
    private Ship ship;
    private Region region;
    private JButton regionButton;
    private Item[] itemList;
    private JLabel success;

    public MerchSuccessScreen(Player player, Ship currShip, Region region, Item[] itemList) {
        this.player = player;
        this.ship = currShip;
        this.region = region;
        this.itemList = itemList;
        merchantFrame = new JFrame("Merchant Encounter: Success");
        merchantFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
    }

    public void createGUI() {
        merchantFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel pane = createMerchantPane();
        merchantPanel.setLayout(new GridLayout(15, 15, 5, 0));
        Random rand = new Random();
        int randItem = rand.nextInt(2);
        Item win = itemList[randItem];
        success = new JLabel("You have succeeded in "
                + "your efforts to attack the merchant. "
                + "You gained " + win.getName());
        ship.addCargo(win);
        ship.setCurrCargo(ship.getCurrCargo() + 1);
        regionButton = new JButton("Ok, continue to region.");
        merchantFrame.add(addLabel(success));
        merchantFrame.add(addBlankSpace());
        merchantFrame.add(addButton(regionButton));
        merchantFrame.add(addBlankSpace());
        merchantFrame.pack();
        merchantFrame.setVisible(true);

        regionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                merchantFrame.dispose();
                JFrame regionScreen = new RegionScreen(player, ship, region);
            }
        });


    }

    public JPanel createMerchantPane() {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createTitledBorder("Merchant Encounter: Success"));
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);

        return p;
    }

    public JPanel addBlankSpace() {
        JLabel space = new JLabel("");
        merchantPanel.add(space);
        return merchantPanel;
    }

    public JPanel addText(String text) {
        JLabel add = new JLabel(text);
        add.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        merchantPanel.add(add);
        return merchantPanel;
    }

    public JPanel addLabel(JLabel label) {
        label.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        merchantPanel.add(label);
        return merchantPanel;
    }

    public JPanel addButton(JButton button) {
        merchantPanel.add(button);
        return merchantPanel;
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
