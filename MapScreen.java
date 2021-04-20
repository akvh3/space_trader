import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

/**
 * The map that contains the regions that you can visit in the game.
 */
public class MapScreen extends
        JFrame implements ActionListener, WindowListener {
    private JFrame mapFrame;
    private JPanel mapPanel;
    private String characterName;
    private JPanel textP;
    private Player player;
    private Universe theUniverse;
    private Region[] rList;
    private int numRegion;
    private JButton nextRegion;
    private boolean check = false;
    private JLabel curLoc;
    private Ship currShip;
    private Region curRegion;


    public MapScreen(Player player, int num, Ship currShip) {
        this.player = player;
        numRegion = num;
        this.currShip = currShip;
        this.curRegion = curRegion;
        mapFrame = new JFrame("Map");
        mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theUniverse = Universe.getUniverseInstance();
        rList = theUniverse.getRegionList();
        createGUI();
    }

    public void createGUI() {
        mapFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel pane = makeConfirmPane();
        mapFrame.add(pane);
        mapPanel = new JPanel();

        nextRegion = new JButton("Next Region");
        mapFrame.add(makeTextPane("Map Page"));
        if (!(check) && numRegion == 0) {
            mapFrame.add(addLabel());
        } else {
            mapFrame.add(addNewRegionLabel());
        }
        mapFrame.add(addGoodButton("Map"));
        mapPanel.setLayout(null);
        mapPanel.setBackground(Color.black);
        mapFrame.pack();
        mapFrame.setVisible(true);

        for (int i = 0; i < 10; i++) {
            mapFrame.add(addLoc(rList[i], i));
        }
    }

    public JPanel addLoc(Region r, int num) {
        JLabel name = new JLabel("Name: " + r.getName());
        JButton travel = new JButton("Travel");
        String xString = Integer.toString(r.getXCoord());
        String yString = Integer.toString(r.getyCoord());
        JLabel coords = new JLabel("Coordinates: " + xString + ", " + yString);
        JLabel tech = new JLabel("Tech Level: " + r.getTechLevel().name());
        if (r.equals(player.getRegion())) {
            curLoc = new JLabel("Current Location");
            mapPanel.add(curLoc);
            curLoc.setSize(1000, 100);
            curLoc.setLocation(r.getXCoord(), r.getyCoord() + 30);
            curLoc.setForeground(Color.white);
        }

        mapPanel.add(name);
        mapPanel.add(coords);
        mapPanel.add(tech);
        mapPanel.add(travel);

        name.setLocation(r.getXCoord(), r.getyCoord());
        name.setSize(1000, 100);
        name.setForeground(Color.white);
        coords.setLocation(r.getXCoord(), r.getyCoord() + 10);
        coords.setSize(1000, 100);
        coords.setForeground(Color.white);
        tech.setLocation(r.getXCoord(), r.getyCoord() + 20);
        tech.setSize(1000, 100);
        tech.setForeground(Color.white);
        travel.setLocation(r.getXCoord(), r.getyCoord() + 20);
        travel.setSize(100, 20);

        travel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int min = 0;
                int max = 13;
                Random rand = new Random();
                int encounter = rand.nextInt(max - min + 1) + min;
                int diff = -1;
                if (player.getLevel().equals("Easy")) {
                    diff = 4;
                }
                if (player.getLevel().equals("Medium")) {
                    diff = 8;
                }
                if (player.getLevel().equals("Hard")) {
                    diff = 12;
                }
                if (encounter < diff && player.getRegion() != rList[num]) {
                    if (diff == 4) {
                        if (encounter <= 2 && currShip.getCurrCargo() != 0) {
                            // POLICE ENCOUNTER
                            mapFrame.dispose();
                            JFrame police = new PoliceScreen(player, num, currShip, rList[num]);
                            return;
                        } else if (encounter == 3 || encounter == 4) {
                            // MERCHANT ENCOUNTER
                            mapFrame.dispose();
                            JFrame merchant = new MerchantScreen(player, currShip, rList[num]);
                            return;
                        } else if (encounter == 5 || encounter == 6) {
                            // BANDIT ENCOUNTER
                            mapFrame.dispose();
                            JFrame bandit = new BanditScreen(player, num, currShip, rList[num]);
                            return;
                        }
                    }
                    if (diff == 8) {
                        if (encounter <= 2 && currShip.getCurrCargo() != 0) {
                            // POLICE ENCOUNTER
                            mapFrame.dispose();
                            JFrame police = new PoliceScreen(player, num, currShip, rList[num]);
                            return;
                        } else if (encounter == 3 || encounter == 4 || encounter == 5) {
                            // MERCHANT ENCOUNTER
                            mapFrame.dispose();
                            JFrame merchant = new MerchantScreen(player, currShip, rList[num]);
                            return;
                        } else if (encounter == 6 || encounter == 7 || encounter == 8) {
                            // BANDIT ENCOUNTER
                            mapFrame.dispose();
                            JFrame bandit = new BanditScreen(player, num, currShip, rList[num]);
                            return;
                        }
                    }
                    if (diff == 12) {
                        if (encounter <= 4 && currShip.getCurrCargo() != 0) {
                            // POLICE ENCOUNTER
                            mapFrame.dispose();
                            JFrame police = new PoliceScreen(player, num, currShip, rList[num]);
                            return;
                        } else if (encounter > 4 && encounter <= 8) {
                            // MERCHANT ENCOUNTER
                            mapFrame.dispose();
                            JFrame merchant = new MerchantScreen(player, currShip, rList[num]);
                            return;
                        } else if (encounter > 8 && encounter <= 12) {
                            // BANDIT ENCOUNTER
                            mapFrame.dispose();
                            JFrame bandit = new BanditScreen(player, num, currShip, rList[num]);
                            return;
                        }
                    }
                }
                player.setNextRegion(rList[num]);
                mapPanel.add(curLoc);
                curLoc.setSize(1000, 100);
                curLoc.setLocation(rList[num].getXCoord(), rList[num].getyCoord() + 30);
                mapFrame.dispose();
                JFrame currRegion = new RegionScreen(player, currShip, rList[num]);
            }

        });
        return mapPanel;
    }

    public JPanel addLabel() {
        JLabel name = new JLabel("Name: " + player.getRegion().getName());
        String xString = Integer.toString(player.getRegion().getXCoord());
        String yString = Integer.toString(player.getRegion().getyCoord());
        JLabel coords = new JLabel("Coordinates: " + xString + ", " + yString);
        JLabel tech = new JLabel("Tech Level: " + player.getRegion().getTechLevel().name());
        mapPanel.add(name);
        mapPanel.add(coords);
        mapPanel.add(tech);
        return mapPanel;
    }

    public JPanel addNewRegionLabel() {
        JLabel name = new JLabel("Name: " + rList[numRegion].getName());
        String xString = Integer.toString(rList[numRegion].getXCoord());
        String yString = Integer.toString(rList[numRegion].getyCoord());
        JLabel coords = new JLabel("Coordinates: " + xString + ", " + yString);
        JLabel tech = new JLabel("Tech Level: " + rList[numRegion].getTechLevel().name());
        mapPanel.add(name);
        mapPanel.add(coords);
        mapPanel.add(tech);
        return mapPanel;
    }

    public JPanel makeConfirmPane() {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createTitledBorder("Map"));
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);

        return p;
    }

    public JPanel makeTextPane(String name) {
        textP = new JPanel();
        addText(name, textP);

        return textP;
    }

    public JPanel addGoodButton(String label) {
        JButton mapButton = new JButton(label);
        mapPanel.add(nextRegion);
        return mapPanel;
    }
    public JPanel makeButtonPane() {
        JPanel p = new JPanel();
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);
        addButton(nextRegion, p);
        return p;
    }

    private void addButton(JButton myButton, Container container) {
        JButton button = myButton;
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setAlignmentY(BOTTOM_ALIGNMENT);
        container.add(button);
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