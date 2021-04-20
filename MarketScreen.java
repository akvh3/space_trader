import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * The screen for the market, contains the options to
 * buy and sell items, repair your ship, refuel your
 * ship, or buy the end game item.
 */
public class MarketScreen extends JFrame implements ActionListener, WindowListener {

    private Item[] itemList;
    private JFrame marketFrame;
    private int repairCost;
    private JPanel marketPanel = new JPanel();
    private JButton[] buttonList = new JButton[10];
    private JButton[] sellButtonList = new JButton[10];
    private boolean[] isPurchased = new boolean[10];
    private JLabel[] labelList = new JLabel[10];
    private JButton playerButton = new JButton("Player Inventory");
    private JButton repairButton = new JButton("Repair Ship: " + repairCost + " Credits");
    private Player player;
    private Ship ship;
    private Universe universe;
    private Region region;
    private JLabel credLabel;
    private JButton backButton = new JButton("Back Button");

    public MarketScreen(Item[] itemList, Player player, Ship ship, Region region) {
        this.itemList = itemList;
        this.player = player;
        this.ship = ship;
        this.universe = Universe.getUniverseInstance();
        this.region = region;
        marketFrame = new JFrame("Market");
        marketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
    }

    public MarketScreen(Player player, Ship ship, Region region) {
        itemList = new Item[10];
        for (int i = 0; i < 10; i++) {
            itemList[i] = new Item(ItemType.getRandomItem(), 10, player);
            while ((itemList[i].getItemType() == ItemType.COMPUTERS)
                    || (itemList[i].getItemType() == ItemType.THRUSTERS)
                    || (itemList[i].getItemType() == ItemType.WEAPONS)) {
                if (!((region.getTechLevel() == TechLevel.MODERN)
                        || (region.getTechLevel() == TechLevel.FUTURISTIC))) {
                    itemList[i] = new Item(ItemType.getRandomItem(), 10, player);
                } else {
                    break;
                }
            }
            isPurchased[i] = false;
        }
        //set the cost of repairing based on engineer skill
        repairCost = 100 - (player.getEngineerSkill() * 4);
        repairButton.setText("Repair Ship: " + repairCost + " Credits");
        this.player = player;
        this.ship = ship;
        this.universe = Universe.getUniverseInstance();
        this.region = region;
        marketFrame = new JFrame("Market");
        marketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
    }

    public void createGUI() {
        marketFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel pane = createMarketPane();
        marketPanel.setLayout(new GridLayout(15, 15, 5, 0));
        credLabel = new JLabel(player.getCredits() + " Credits");
        for (int i = 0; i < 10; i++) {
            //GridBagConstraints c = new GridBagConstraints();
            //c.gridx = 0;
            //c.gridy = 0;
            buttonList[i] = new JButton("Purchase Item");
            int finalI = i;
            sellButtonList[i] = new JButton("Sell Item");
            marketFrame.add(addGoodButton(buttonList[i]));
            //c.gridx = 1;
            marketFrame.add(addGoodButton(sellButtonList[i]));
            labelList[i] =
                    new JLabel(itemList[i].getItemType()
                            + ": " + (int) (itemList[i].getCost()) + " Credits");
            sellButtonList[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (Item item: player.getItemList()) {
                        if (item.getName().equals(itemList[finalI].getName())) {
                            //if selling fuel
                            if (itemList[finalI].getItemType().equals(ItemType.FUEL)) {
                                player.setCredits((int) (player.getCredits()
                                        + itemList[finalI].getCost()));
                                credLabel.setText(player.getCredits() + " Credits");
                                ship.setCurrFuel(ship.getCurrFuel() - 10);
                                //can't have negative fuel
                                if (ship.getCurrFuel() < 0) {
                                    ship.setCurrFuel(0);
                                }
                            } else {
                                player.setCredits((int) (player.getCredits()
                                        + itemList[finalI].getCost()));
                                ship.setCurrCargo(ship.getCurrCargo() - 1);
                                player.getItemList().remove(item);
                                credLabel.setText(player.getCredits() + " Credits");
                            }
                        }
                    }
                }
            });
            buttonList[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if ((player.getCredits() > itemList[finalI].getCost())
                            && (!(isPurchased[finalI]))
                            && (ship.getCurrCargo() < ship.getmaxCargo())) {
                        player.setCredits((int) (player.getCredits() - itemList[finalI].getCost()));
                        //if itemtype is fuel, simply refuel
                        if (itemList[finalI].getItemType().equals(ItemType.FUEL)) {
                            if (ship.getCurrFuel() < ship.getmaxFuel()) {
                                ship.setCurrFuel(ship.getCurrFuel() + 10);
                                //ensures that fuel never goes above maximum
                                if (ship.getCurrFuel() > ship.getmaxFuel()) {
                                    ship.setCurrFuel(ship.getmaxFuel());
                                }
                                credLabel.setText(player.getCredits() + " Credits");
                                labelList[finalI].setText("PURCHASED");
                                isPurchased[finalI] = true;
                            }
                        } else {
                            player.getItemList().add(itemList[finalI]);
                            credLabel.setText(player.getCredits() + " Credits");
                            ship.setCurrCargo(ship.getCurrCargo() + 1);
                            labelList[finalI].setText("PURCHASED");
                            isPurchased[finalI] = true;
                        }
                    }
                }
            });
            marketFrame.add(addLabel(labelList[i]));
        }
        if (region.checkEndgame().equals("y")) {
            int endPrice;
            if (player.getLevel().equals("Easy")) {
                endPrice = 2000;
            } else if (player.getLevel().equals("Medium")) {
                endPrice = 1500;
            } else {
                endPrice = 1000;
            }
            JButton buyUniverse = new JButton("Purchase: "
                    + player.getName() + "'s Universe: " + endPrice + " Credits");
            marketFrame.add(addGoodButton(buyUniverse));
            buyUniverse.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (player.getCredits() > endPrice
                            && ship.getCurrCargo() < ship.getmaxCargo()) {
                        player.setCredits(player.getCredits() - endPrice);
                        marketFrame.dispose();
                        JFrame dubs = new WinScreen(player);
                    }
                }
            });

        }
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                marketFrame.dispose();
                JFrame map = new MapScreen(player, 0, ship);
            }
        });
        playerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                marketFrame.dispose();
                JFrame playerScreen = new PlayerScreen(player, ship, region);
            }
        });
        repairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((player.getCredits() >= repairCost)
                        && (ship.getCurrHealth() < ship.getmaxHealth())) {
                    player.setCredits(player.getCredits() - repairCost);
                    ship.setCurrHealth(ship.getCurrHealth() + 10);
                    credLabel.setText(player.getCredits() + " Credits");
                    //makes sure repair doesn't go above max health
                    if (ship.getCurrHealth() > ship.getmaxHealth()) {
                        ship.setCurrHealth(ship.getmaxHealth());
                    }
                }
            }
        });
        //marketFrame.add(makeTextPane("Hello"));
        marketFrame.add(makeTextPane(""));
        marketFrame.add(makeTextPane(""));
        marketFrame.add(makeTextPane(""));
        marketFrame.add(makeTextPane(""));
        marketFrame.add(pane);
        marketFrame.add(addButton(repairButton));
        marketFrame.add(addButton(backButton));
        marketFrame.add(addButton(playerButton));
        marketFrame.add(addLabel(credLabel));
        marketFrame.pack();
        marketFrame.setVisible(true);
    }

    public JPanel createMarketPane() {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createTitledBorder("Market Screen"));
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);

        return p;
    }

    public JPanel makeTextPane(String name) {
        JLabel newLabel = new JLabel(name);
        marketPanel.add(newLabel);
        return marketPanel;
    }

    private void addText(String text, Container container) {
        JLabel newText = new JLabel(text);
        container.add(newText);
    }

    public JPanel addButton(JButton button) {
        marketPanel.add(button);
        return marketPanel;
    }

    public JPanel addLabel(JLabel words) {
        //JLabel words = new JLabel(label);
        marketPanel.add(words);
        return marketPanel;
    }

    public JPanel addGoodButton(JButton button) {
        //JButton mapButton = new JButton(label);
        marketPanel.add(button);
        return marketPanel;
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