import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The screen for the police encounter.
 */
public class PoliceScreen extends JFrame implements ActionListener, WindowListener {

    private JFrame policeFrame;
    private JPanel policePanel;
    private String characterName;
    private JButton forfeitButton;
    private JButton fleeButton;
    private JButton fightButton;
    private String level;
    private int credits;
    private JPanel textP;
    private Player player;
    private int num;
    private Ship currShip;
    private Item[] policeItems;
    private int policePilotSkill;
    private int policeFightSkill;
    private Region region;

    public PoliceScreen(Player player, int num, Ship currShip, Region region) {
        policeFrame = new JFrame("The police are here!");
        policeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.player = player;
        this.num = num;
        this.currShip = currShip;
        this.region = region;
        createGUI();
    }
    public void createGUI() {
        policeFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel pane = makePolicePane();
        policeFrame.add(pane);
        policePanel = new JPanel();
        policePanel.setLayout(new GridLayout(15, 15, 5, 0));
        policeItems = new Item[currShip.getmaxCargo()];
        setPoliceSettings();

        policeFrame.add(addLabel("You have encountered the police!"));
        policeFrame.add(addLabel("Current credits: " + player.getCredits()));
        policeFrame.add(addLabel("Current pilot skill: " + player.getPilotSkill()));
        policeFrame.add(addLabel("Current fight skill: " + player.getFighterSkill()));
        policeFrame.add(addLabel("The police are "
                + "interested in the following items in your cargo: " + getPoliceList()));
        forfeitButton = new JButton("Forfeit items");
        fleeButton = new JButton("Flee back to "
                + "previous region (police pilot skill: " + policePilotSkill + ")");
        fightButton = new JButton("Fight the police "
                + "(police fight skill: " + policeFightSkill + ")");
        policeFrame.add(makeTextPane("The Police are here!"));
        policeFrame.add(addGoodButton());
        policeFrame.pack();
        policeFrame.setVisible(true);
        forfeitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currShip.getCurrCargo() > policeItems.length) {
                    for (int i = 0; i < policeItems.length; i++) {
                        for (int j = 0; j < currShip.getCargoList().length; j++) {
                            if (policeItems[i] == currShip.getCargoList()[j]) {
                                currShip.removeCargo(policeItems[i]);
                            }
                        }
                    }
                } else {
                    currShip.setCurrCargo(0);
                    currShip.setCargoList(new Item[currShip.getmaxCargo()]);
                }
                player.setKarma(player.getKarma() + 1);
                policeFrame.dispose();
                JFrame transferScreen2 = new TransferScreen(player, currShip,
                        "Police", "Payment", true, region);
                return;
            }
        });
        fleeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getPilotSkill() > policePilotSkill) {
                    int distance = Math.abs((int) (Math.pow(player.getNextRegion().getXCoord()
                            - player.getRegion().getXCoord(), 2)
                            + Math.pow(player.getNextRegion().getyCoord()
                            - player.getRegion().getyCoord(), 2)));
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
                    policeFrame.dispose();
                    JFrame transferScreen2 = new TransferScreen(player, currShip,
                            "Police", "Flee", true, region);
                    return;
                } else {
                    if (player.getLevel().equals("Easy")) {
                        if (currShip.getCurrHealth() - 6 >= 0) {
                            currShip.setCurrHealth(currShip.getCurrHealth() - 6);
                        } else {
                            currShip.setCurrHealth(0);
                            policeFrame.dispose();
                            JFrame gameover = new GameOverScreen();
                            return;
                        }
                        if (player.getCredits() - 10 >= 0) {
                            player.setCredits(player.getCredits() - 10);
                        } else {
                            player.setCredits(0);
                            policeFrame.dispose();
                            JFrame gameover = new GameOverScreen();
                            return;
                        }
                    } else if (player.getLevel().equals("Medium")) {
                        if (currShip.getCurrHealth() - 8 >= 0) {
                            currShip.setCurrHealth(currShip.getCurrHealth() - 8);
                        } else {
                            currShip.setCurrHealth(0);
                            policeFrame.dispose();
                            JFrame gameover = new GameOverScreen();
                            return;
                        }
                        if (player.getCredits() - 20 >= 0) {
                            player.setCredits(player.getCredits() - 20);
                        } else {
                            player.setCredits(0);
                            policeFrame.dispose();
                            JFrame gameover = new GameOverScreen();
                            return;
                        }
                    } else if (player.getLevel().equals("Hard")) {
                        if (currShip.getCurrHealth() - 10 >= 0) {
                            currShip.setCurrHealth(currShip.getCurrHealth() - 10);
                        } else {
                            currShip.setCurrHealth(0);
                            policeFrame.dispose();
                            JFrame gameover = new GameOverScreen();
                            return;
                        }
                        if (player.getCredits() - 30 >= 0) {
                            player.setCredits(player.getCredits() - 30);
                        } else {
                            player.setCredits(0);
                            policeFrame.dispose();
                            JFrame gameover = new GameOverScreen();
                            return;
                        }
                    }
                    if (currShip.getCurrCargo() > policeItems.length) {
                        for (int i = 0; i < policeItems.length; i++) {
                            for (int j = 0; j < currShip.getCargoList().length; j++) {
                                if (policeItems[i] == currShip.getCargoList()[j]) {
                                    currShip.removeCargo(policeItems[i]);
                                }
                            }
                        }
                    } else {
                        currShip.setCurrCargo(0);
                        currShip.setCargoList(new Item[currShip.getmaxCargo()]);
                    }
                    player.setKarma(player.getKarma() - 1);
                    policeFrame.dispose();
                    JFrame transferScreen2 = new TransferScreen(player, currShip,
                            "Police", "Flee", false, region);
                    return;
                }
            }
        });
        setFightButton();
    }

    private String getPoliceList() {
        String retString = "";
        for (int i = 0; i < policeItems.length - 1; i++) {
            retString += policeItems[i] + ", ";
        }
        retString += policeItems[policeItems.length - 1];
        return retString;
    }

    private void setFightButton() {
        fightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getFighterSkill() >= policeFightSkill) {
                    policeFrame.dispose();
                    JFrame transferScreen2 = new TransferScreen(player, currShip,
                            "Police", "Fight", true, region);
                    return;
                } else {
                    if (currShip.getCurrCargo() > policeItems.length) {
                        for (int i = 0; i < policeItems.length; i++) {
                            for (int j = 0; j < currShip.getCargoList().length; j++) {
                                if (policeItems[i] == currShip.getCargoList()[j]) {
                                    currShip.removeCargo(policeItems[i]);
                                }
                            }
                        }
                    } else {
                        currShip.setCurrCargo(0);
                        currShip.setCargoList(new Item[currShip.getmaxCargo()]);
                    }
                    player.setKarma(player.getKarma() - 1);
                    policeFrame.dispose();
                    JFrame transferScreen2 = new TransferScreen(player, currShip,
                            "Police", "Fight", false, region);
                }
            }
        });
    }

    private void setPoliceSettings() {
        if (player.getLevel().equals("Easy")) {
            if (currShip.getCargoList().length <= 2) {
                for (int j = 0; j < currShip.getCargoList().length; j++) {
                    policeItems[j] = currShip.getCargoList()[j];
                }
            } else {
                int random;
                int[] randomList = new int[2];
                for (int i = 0; i < 2; i++) {
                    random = (int) (Math.random() * 10) % currShip.getCargoList().length;
                    policeItems[i] = currShip.getCargoList()[random];
                }
            }
            policeFightSkill = 4;
            policePilotSkill = 4;
        } else if (player.getLevel().equals("Medium")) {
            if (currShip.getCargoList().length <= 4) {
                for (int j = 0; j < currShip.getCargoList().length; j++) {
                    policeItems[j] = currShip.getCargoList()[j];
                }
            } else {
                int random;
                int[] randomList = new int[4];
                for (int i = 0; i < 4; i++) {
                    random = (int) (Math.random() * 10) % currShip.getCargoList().length;
                    policeItems[i] = currShip.getCargoList()[random];
                }
            }
            policeFightSkill = 6;
            policePilotSkill = 6;
        } else if (player.getLevel().equals("Hard")) {
            if (currShip.getCargoList().length <= 6) {
                for (int j = 0; j < currShip.getCargoList().length; j++) {
                    policeItems[j] = currShip.getCargoList()[j];
                }
            } else {
                int random;
                int[] randomList = new int[6];
                for (int i = 0; i < 6; i++) {
                    random = (int) (Math.random() * 10) % currShip.getCargoList().length;
                    policeItems[i] = currShip.getCargoList()[random];
                }
            }
            policeFightSkill = 8;
            policePilotSkill = 8;
        }
    }
    public JPanel makePolicePane() {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createTitledBorder("Police"));
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);

        return p;
    }

    public JPanel addGoodButton() {
        policePanel.add(forfeitButton);
        policePanel.add(fleeButton);
        policePanel.add(fightButton);
        return policePanel;
    }

    public JPanel addLabel(String label) {
        JLabel words = new JLabel(label);
        policePanel.add(words);
        return policePanel;
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
