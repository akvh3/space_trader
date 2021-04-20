import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

/**
 * The screen for the merchant encounter.
 */
public class MerchantScreen extends
        JFrame implements ActionListener, WindowListener {

    private JFrame merchantFrame;
    private JPanel merchantPanel = new JPanel();
    private Player player;
    private Ship ship;
    private Region region;
    private JButton regionButton;
    private JLabel welcome;
    private JButton negotiate;
    private JButton attack;
    private Item[] itemList;
    private JLabel info;
    private Random rand = new Random();
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private boolean[] boughtList = new boolean[3];
    private boolean negotiated;
    private JLabel inventory = new JLabel("");

    public MerchantScreen(Player player, Ship currShip, Region region) {
        this.player = player;
        this.ship = currShip;
        this.region = region;
        itemList = new Item[3];
        for (int i = 0; i < 3; i++) {
            itemList[i] = new Item(ItemType.getRandomItem(), 10, player);
        }
        for (int i = 0; i < 3; i++) {
            boughtList[i] = false;
        }
        negotiated = false;
        merchantFrame = new JFrame("Merchant Encounter");
        merchantFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
    }

    public void createGUI() {
        merchantFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel pane = createMerchantPane();
        merchantPanel.setLayout(new GridLayout(15, 15, 5, 0));
        regionButton = new JButton("We're done here.");
        welcome = new JLabel("Hello, I am a friendly merchant! These are my wares!");
        negotiate = new JButton("Negotiate (your merchant skill is "
                + player.getMerchantSkill() + " points)");
        attack = new JButton("Rob (your fighter skill is "
                + player.getFighterSkill() + " points)");
        info = new JLabel("You have " + player.getCredits() + " credits, your ship has "
                + ship.getCurrCargo() + " cargo and " + ship.getCurrHealth() + " health.");
        merchantFrame.add(addLabel(welcome));
        merchantFrame.add(addLabel(info));
        button1 = new JButton(itemList[0].getName() + ": " + itemList[0].getCost());
        button2 = new JButton(itemList[1].getName() + ": " + itemList[1].getCost());
        button3 = new JButton(itemList[2].getName() + ": " + itemList[2].getCost());
        merchantFrame.add(addButton(button1));
        merchantFrame.add(addButton(button2));
        merchantFrame.add(addButton(button3));

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((player.getCredits() > (int) itemList[0].getCost())
                        && ship.getCurrCargo() < ship.getmaxCargo() && !(boughtList[0])) {
                    player.setCredits(player.getCredits() - (int) itemList[0].getCost());
                    ship.setCurrCargo(ship.getCurrCargo() + 1);
                    info.setText("You have " + player.getCredits() + " credits, your ship has "
                            + ship.getCurrCargo()
                            + " cargo and " + ship.getCurrHealth() + " health.");
                    ship.addCargo(itemList[0]);
                    button1.setText("PURCHASED");
                    boughtList[0] = true;
                    inventory.setText(inventory.getText() + ", " + itemList[0].getName());
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((player.getCredits() > (int) itemList[1].getCost())
                        && ship.getCurrCargo() < ship.getmaxCargo() && !(boughtList[1])) {
                    player.setCredits(player.getCredits() - (int) itemList[1].getCost());
                    ship.setCurrCargo(ship.getCurrCargo() + 1);
                    info.setText("You have " + player.getCredits() + " credits, your ship has "
                            + ship.getCurrCargo()
                            + " cargo and " + ship.getCurrHealth() + " health.");
                    ship.addCargo(itemList[1]);
                    button2.setText("PURCHASED");
                    boughtList[1] = true;
                    inventory.setText(inventory.getText() + ", " + itemList[1].getName());
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((player.getCredits() > (int) itemList[2].getCost())
                        && ship.getCurrCargo() < ship.getmaxCargo() && !(boughtList[2])) {
                    player.setCredits(player.getCredits() - (int) itemList[2].getCost());
                    ship.setCurrCargo(ship.getCurrCargo() + 1);
                    info.setText("You have " + player.getCredits() + " credits, your ship has "
                            + ship.getCurrCargo()
                            + " cargo and " + ship.getCurrHealth() + " health.");
                    ship.addCargo(itemList[2]);
                    boughtList[2] = true;
                    button3.setText("PURCHASED");
                    inventory.setText(inventory.getText() + ", " + itemList[2].getName());
                }
            }
        });

        negotiate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(negotiated)) {
                    negotiated = true;
                    Random rand = new Random();
                    int randNum = rand.nextInt(10);
                    if (player.getMerchantSkill() > randNum) {
                        // SUCCESS
                        welcome.setText("You drive a hard bargain my friend! Half off all prices!");
                        for (int i = 0; i < 3; i++) {
                            itemList[i].setCost(itemList[i].getCost() / 2.0);
                        }
                        button1.setText(itemList[0].getName() + ": " + itemList[0].getCost());
                        button2.setText(itemList[1].getName() + ": " + itemList[1].getCost());
                        button3.setText(itemList[2].getName() + ": " + itemList[2].getCost());
                        negotiate.setText("Successfully Negotiated");
                    } else {
                        // FAILURE
                        welcome.setText("No tricks with "
                                + "me here sir, you have offended me! Markup for you!");
                        for (int i = 0; i < 3; i++) {
                            itemList[i].setCost(itemList[i].getCost()
                                    + (itemList[i].getCost() / 3.0));
                        }
                        button1.setText(itemList[0].getName() + ": " + itemList[0].getCost());
                        button2.setText(itemList[1].getName() + ": " + itemList[1].getCost());
                        button3.setText(itemList[2].getName() + ": " + itemList[2].getCost());
                        negotiate.setText("Unsuccessfully Negotiated");
                    }
                }
            }
        });

        merchantFrame.add(addBlankSpace());
        merchantFrame.add(addButton(negotiate));
        merchantFrame.add(addBlankSpace());
        merchantFrame.add(addButton(attack));
        merchantFrame.add(addBlankSpace());
        merchantFrame.add(addButton(regionButton));
        merchantFrame.add(addBlankSpace());
        merchantFrame.add(addText("Inventory:"));
        merchantFrame.add(addInventory());
        merchantFrame.pack();
        merchantFrame.setVisible(true);

        regionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                merchantFrame.dispose();
                JFrame regionScreen = new RegionScreen(player, ship, region);
            }
        });

        attack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int random = rand.nextInt(8 - 0) + 0;
                if (player.getFighterSkill() * .5 < random) {
                    ship.setCurrHealth(ship.getCurrHealth()
                            - ship.getCurrHealth() / 3);
                    player.setKarma(player.getKarma() - 1);
                    merchantFrame.dispose();
                    JFrame failScreen = new MerchFailScreen(player,
                            ship, region, itemList, ship.getCurrHealth() / 3);
                } else {
                    player.setKarma(player.getKarma() - 1);
                    merchantFrame.dispose();
                    JFrame successScreen = new MerchSuccessScreen(player, ship, region, itemList);
                }
            }
        });
    }

    public JPanel createMerchantPane() {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createTitledBorder("Merchant Encounter"));
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);

        return p;
    }

    public JPanel addInventory() {
        if (!(ship.getCurrCargo() == 0)) {
            Item[] cargo = ship.getCargo();
            for (int i = 0; i < cargo.length; i++) {
                if (cargo[i] != null) {
                    inventory.setText(inventory.getText() + ", " + cargo[i].getName());
                }
            }
        }
        merchantPanel.add(inventory);
        return merchantPanel;
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