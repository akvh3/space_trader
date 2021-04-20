import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The screen you see when arriving at each region,
 * where you decide to go to the market or leave.
 */
public class RegionScreen
        extends JFrame implements ActionListener, WindowListener {
    private JFrame regionFrame;
    private JPanel regionPanel;
    private Player player;
    private JPanel textP;
    private Ship myShip;
    private JButton travelButton;
    private JButton backButton;
    private JButton configButton;
    private Region region;

    public RegionScreen(Player player, Ship myShip, Region region) {
        this.player = player;
        this.myShip = myShip;
        this.region = region;
        regionFrame = new JFrame((region.getName()));
        regionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
    }

    public void createGUI() {
        regionFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel pane = makeRegionPane();
        regionFrame.add(pane);
        regionPanel = new JPanel();
        regionPanel.setLayout(new GridLayout(15, 15, 5, 0));

        regionFrame.add(makeTextPane("Welcome to " + region.getName()));
        regionFrame.add(addLabel("Wecome to " + region.getName()));
        regionFrame.add(addLabel("Coordinates: "
                + region.getXCoord() + ", " + region.getyCoord()));
        regionFrame.add(addLabel("Tech Level: " + region.getTechLevel()));

        int distance = Math.abs((int) (Math.pow(player.getRegion().getXCoord()
                - region.getXCoord(), 2)
                + Math.pow(player.getRegion().getyCoord()
                - region.getyCoord(), 2)));
        double dist = Math.sqrt(distance);
        dist = Math.round(dist * 100.00) / 100.00;
        regionFrame.add(addLabel("Your current region, " + player.getRegion().getName()
                + ", is " + dist + " units from this region."));
        // higher pilot skill == less time flying == cheaper
        double fuelCost;
        if (player.getPilotSkill() != 0) {
            fuelCost = Math.round(((20 - player.getPilotSkill()) * .02 * dist) * 100.0) / 1000.0;
        } else {
            fuelCost = .1 * dist;
        }

        regionFrame.add(addLabel("Fuel cost from current region to this region with "
                + myShip.getName() + " is " + fuelCost + " fuel. You have "
                + myShip.getCurrFuel() + " fuel.")); // + fuel cost

        regionFrame.add(addLabel("Fuel cost decreases as your pilot skill level increases."));
        regionFrame.add(addLabel("You have " + player.getKarmaTitle() + "."));

        travelButton = new JButton("Travel to this Region");
        backButton = new JButton("Back to Map");
        configButton = new JButton("Start New Game");

        regionFrame.add(addGoodButton());
        regionFrame.pack();
        regionFrame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regionFrame.dispose();
                JFrame mapScreen = new MapScreen(player, 0, myShip);
            }
        });
        travelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regionFrame.dispose();
                if (myShip.getCurrFuel() - (int) fuelCost < 0) {
                    String error = new String("You do not have enough fuel.");
                    JOptionPane.showMessageDialog(new JFrame(),
                            error, "Dialog", JOptionPane.ERROR_MESSAGE);
                    regionFrame.dispose();
                    JFrame newScreen = new RegionScreen(player, myShip, region);
                    return;
                }
                if (region.getName().equals("The Citadel") && player.getKarma() < 0) {
                    String error = new String("Your karma is too low to enter the Citadel.");
                    JOptionPane.showMessageDialog(new JFrame(), error,
                            "Dialog", JOptionPane.ERROR_MESSAGE);
                    regionFrame.dispose();
                    JFrame newScreen = new RegionScreen(player, myShip, region);
                    return;
                }
                player.setRegion(region);
                myShip.setCurrFuel((myShip.getCurrFuel() - (int) fuelCost));
                JFrame marketScreen = new MarketScreen(player, myShip, region);
            }
        });
        configButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Universe redo = Universe.getUniverseInstance();
                Universe.restart();
                regionFrame.dispose();
                JFrame config = new ConfigurationScreen("Set-Up");
            }
        });
    }

    public JPanel addGoodButton() {
        regionPanel.add(travelButton);
        regionPanel.add(backButton);
        regionPanel.add(configButton);
        return regionPanel;
    }

    public JPanel makeRegionPane() {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createTitledBorder("Confirmation"));
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);

        return p;
    }
    public JPanel addLabel(String label) {
        JLabel words = new JLabel(label);
        regionPanel.add(words);
        return regionPanel;
    }
    public JPanel makeTextPane(String name) {
        textP = new JPanel();
        addText(name, textP);

        return textP;
    }
    private void addText(String text, Container container) {
        JLabel newText = new JLabel(text);
        container.add(newText);
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