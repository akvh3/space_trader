import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The screen for the bandit encounter.
 */
public class BanditScreen extends JFrame implements ActionListener, WindowListener {

    private JFrame banditFrame;
    private JPanel banditPanel;
    private String characterName;
    private JButton payButton;
    private JButton fleeButton;
    private JButton fightButton;
    private String level;
    private int credits;
    private JPanel textP;
    private Player player;
    private int banditPrice;
    private int banditPilotSkill;
    private int banditFightSkill;
    private int num;
    private Ship currShip;
    private Region region;
    private int banditCredit;

    public BanditScreen(Player player, int num, Ship currShip, Region region) {
        banditFrame = new JFrame("The bandit is here!");
        banditFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.player = player;
        this.num = num;
        this.currShip = currShip;
        this.region = region;
        createGUI();
    }

    public void createGUI() {
        banditFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel pane = makeBanditPane();
        banditFrame.add(pane);
        banditPanel = new JPanel();
        banditPanel.setLayout(new GridLayout(15, 15, 5, 0));
        setBandit();

        if (player.getLevel().equals("Easy")) {
            banditPrice = 200;
            banditFightSkill = 4;
            banditPilotSkill = 4;
        } else if (player.getLevel().equals("Medium")) {
            banditPrice = 100;
            banditFightSkill = 4;
            banditPilotSkill = 4;
        } else if (player.getLevel().equals("Hard")) {
            banditPrice = 50;
            banditFightSkill = 4;
            banditPilotSkill = 4;
        }

        banditFrame.add(addLabel("You have encountered a bandit!"));
        banditFrame.add(addLabel("Current credits: " + player.getCredits()));
        banditFrame.add(addLabel("Current pilot skill: " + player.getPilotSkill()));
        banditFrame.add(addLabel("Current fight skill: " + player.getFighterSkill()));
        banditFrame.add(addLabel("You must pay the bandit's price, "
                + "flee back to old region, or fight the bandit"));
        payButton = new JButton("Pay bandit's price "
                + "(" + banditPrice + ")"); // NEED TO INSERT PRICE HERE
        fleeButton = new JButton("Flee back to previous "
                + "region (bandit pilot skill: " + banditPilotSkill + ")");
        fightButton = new JButton("Fight the bandit "
                + "(bandit fight skill: " + banditFightSkill + ")");

        banditFrame.add(makeTextPane("The Bandit is Here!"));
        banditFrame.add(addGoodButton());

        banditFrame.pack();
        banditFrame.setVisible(true);

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getCredits() >= banditPrice) {
                    player.setCredits(player.getCredits() - banditPrice);
                } else {
                    if (currShip.getCurrCargo() != 0) {
                        currShip.setCurrCargo(0);
                    } else {
                        if (currShip.getCurrHealth() - 2 >= 0) {
                            currShip.setCurrHealth(currShip.getCurrHealth() - 2);
                        } else {
                            currShip.setCurrHealth(0);
                        }
                    }
                }
                player.setKarma(player.getKarma() - 1);
                banditFrame.dispose();
                JFrame transfer = new TransferScreen(player, currShip,
                        "Bandit", "Payment", true, region);
                return;
            }
        });

        fleeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getPilotSkill() > banditPilotSkill) {
                    int distance = Math.abs((int) (Math.pow(region.getXCoord()
                            - player.getRegion().getXCoord(), 2)
                            + Math.pow(region.getyCoord() - region.getyCoord(), 2)));
                    double dist = Math.sqrt(distance);
                    dist = Math.round(dist * 100.00) / 100.00;
                    double fuelCost;
                    if (player.getPilotSkill() != 0) {
                        fuelCost = Math.round(((20 - player.getPilotSkill())
                                * .02 * dist) * 100.0) / 1000.0;
                    } else {
                        fuelCost = .1 * dist;
                    }
                    currShip.setCurrFuel((int) (currShip.getCurrFuel() - fuelCost));
                    banditFrame.dispose();
                    JFrame region2 = new TransferScreen(player, currShip,
                            "Bandit", "Flee", true, region);
                    return;
                } else {
                    player.setCredits(0);
                    if (player.getLevel().equals("Easy")) {
                        if (currShip.getCurrHealth() - 6 >= 0) {
                            currShip.setCurrHealth(currShip.getCurrHealth() - 6);
                        } else {
                            currShip.setCurrHealth(0);
                            banditFrame.dispose();
                            JFrame gameover = new GameOverScreen();
                            return;
                        }
                    } else if (player.getLevel().equals("Medium")) {
                        if (currShip.getCurrHealth() - 8 >= 0) {
                            currShip.setCurrHealth(currShip.getCurrHealth() - 8);
                        } else {
                            currShip.setCurrHealth(0);
                            banditFrame.dispose();
                            JFrame gameover = new GameOverScreen();
                            return;
                        }
                    } else if (player.getLevel().equals("Hard")) {
                        if (currShip.getCurrHealth() - 10 >= 0) {
                            currShip.setCurrHealth(currShip.getCurrHealth() - 10);
                        } else {
                            currShip.setCurrHealth(0);
                            banditFrame.dispose();
                            JFrame gameover = new GameOverScreen();
                            return;
                        }
                    }
                    player.setKarma(player.getKarma() + 1);
                    banditFrame.dispose();
                    JFrame region2 = new TransferScreen(player, currShip,
                            "Bandit", "Flee", false, region);
                    return;
                }
            }
        });
        setFightButton();
    }

    private void setBandit() {
        if (player.getLevel().equals("Easy")) {
            banditPrice = 200;
            banditFightSkill = 4;
            banditPilotSkill = 4;
            banditCredit = 100;
        } else if (player.getLevel().equals("Medium")) {
            banditPrice = 100;
            banditFightSkill = 4;
            banditPilotSkill = 4;
            banditCredit = 80;
        } else if (player.getLevel().equals("Hard")) {
            banditPrice = 50;
            banditFightSkill = 4;
            banditPilotSkill = 4;
            banditCredit = 60;
        }

    }

    private void setFightButton() {
        fightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getFighterSkill() >= banditFightSkill) {
                    player.setCredits(player.getCredits() + (banditCredit / 6));
                    banditFrame.dispose();
                    JFrame transferScreen2 = new TransferScreen(player, currShip,
                            "Bandit", "Fight", true, region);
                    return;
                } else {
                    player.setCredits(0);
                    if (player.getLevel().equals("Easy")) {
                        if (currShip.getCurrHealth() - 6 >= 0) {
                            currShip.setCurrHealth(currShip.getCurrHealth() - 6);
                        } else {
                            currShip.setCurrHealth(0);
                            banditFrame.dispose();
                            JFrame gameover = new GameOverScreen();
                            return;
                        }
                    } else if (player.getLevel().equals("Medium")) {
                        if (currShip.getCurrHealth() - 8 >= 0) {
                            currShip.setCurrHealth(currShip.getCurrHealth() - 8);
                        } else {
                            currShip.setCurrHealth(0);
                            banditFrame.dispose();
                            JFrame gameover = new GameOverScreen();
                            return;
                        }
                    } else if (player.getLevel().equals("Hard")) {
                        if (currShip.getCurrHealth() - 10 >= 0) {
                            currShip.setCurrHealth(currShip.getCurrHealth() - 10);
                        } else {
                            currShip.setCurrHealth(0);
                            banditFrame.dispose();
                            JFrame gameover = new GameOverScreen();
                            return;
                        }
                    }
                    player.setKarma(player.getKarma() + 1);
                    banditFrame.dispose();
                    JFrame transferScreen2 = new TransferScreen(player, currShip,
                            "Bandit", "Fight", false, region);
                    return;
                }
            }
        });
    }

    public JPanel addGoodButton() {
        banditPanel.add(payButton);
        banditPanel.add(fleeButton);
        banditPanel.add(fightButton);
        return banditPanel;
    }

    public JPanel makeBanditPane() {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createTitledBorder("Bandit"));
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);

        return p;
    }
    public JPanel addLabel(String label) {
        JLabel words = new JLabel(label);
        banditPanel.add(words);
        return banditPanel;
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
