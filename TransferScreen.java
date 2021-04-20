import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The success/fail screens for the Bandit and Police encounters.
 */
public class TransferScreen extends JFrame implements ActionListener, WindowListener {
    private JFrame transferFrame;
    private JPanel transferPanel;
    private JButton continueButton;
    private JPanel textP;
    private Player player;
    private Ship currShip;
    private String encounter;
    private Boolean success;
    private String type;
    private Region currRegion;

    public TransferScreen(Player player, Ship currShip,
                          String encounter, String type, Boolean success, Region region) {
        transferFrame = new JFrame();
        transferFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.player = player;
        this.currShip = currShip;
        this.encounter = encounter;
        this.success = success;
        this.type = type;
        this.currRegion = region;
        createGUI();
    }
    public void createGUI() {
        transferFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel pane = makeTransferPane();
        transferFrame.add(pane);
        transferPanel = new JPanel();
        transferPanel.setLayout(new GridLayout(15, 15, 5, 0));
        if (encounter.equals("Bandit")) {
            if (type.equals("Payment")) {
                transferFrame.add(addLabel("You have paid the bandit's price"));
                transferFrame.add(addLabel("You now have " + player.getCredits() + " credits."));
                transferFrame.add(addLabel("You're current cargo is " + currShip.getCurrCargo()));
                transferFrame.add(addLabel("You're ship's current health is "
                        + currShip.getCurrHealth()));

                continueButton = new JButton("Continue to Region");

                transferFrame.add(makeTextPane("Successful Payment of Bandit"));
                transferFrame.add(addGoodButton());
            } else if (type.equals("Fight")) {
                if (success) {
                    transferFrame.add(addLabel("You have successfully fought the bandit!"));
                    continueButton = new JButton("Continue to Region");

                    transferFrame.add(makeTextPane("Successfully fought the bandit"));
                    transferFrame.add(addGoodButton());
                } else {
                    transferFrame.add(addLabel("You have failed in a fight against the bandit!"));
                    transferFrame.add(addLabel("You now have "
                            + player.getCredits() + " credits."));
                    transferFrame.add(addLabel("You're ship's current health is "
                            + currShip.getCurrHealth()));

                    continueButton = new JButton("Continue to Region");

                    transferFrame.add(makeTextPane("Unsuccessfully fought the bandit"));
                    transferFrame.add(addGoodButton());
                }
            } else if (type.equals("Flee")) {
                if (success) {
                    transferFrame.add(addLabel("You have successfully fled from the bandit"));
                    transferFrame.add(addLabel("You're ship's current fuel is "
                            + currShip.getCurrFuel()));
                    continueButton = new JButton("Continue to Previous Region");

                    transferFrame.add(makeTextPane("Successfully fled from Bandit"));
                    transferFrame.add(addGoodButton());
                } else {
                    transferFrame.add(addLabel("You have failed to flee from the bandit"));
                    transferFrame.add(addLabel("You now have "
                            + player.getCredits() + " credits."));
                    transferFrame.add(addLabel("You're current cargo is "
                            + currShip.getCurrCargo()));
                    transferFrame.add(addLabel("You're ship's current health is "
                            + currShip.getCurrHealth()));
                    continueButton = new JButton("Continue to Region");

                    transferFrame.add(makeTextPane("Unsuccessfully fled from Bandit"));
                    transferFrame.add(addGoodButton());
                }
            }
        } else if (encounter.equals("Police")) {
            if (type.equals("Flee")) {
                if (success) {
                    transferFrame.add(addLabel("You have successfully fled from the police"));
                    transferFrame.add(addLabel("You're ship's current fuel is "
                            + currShip.getCurrFuel()));
                    continueButton = new JButton("Continue to Previous Region");

                    transferFrame.add(makeTextPane("Successfully fled from police"));
                    transferFrame.add(addGoodButton());
                    currRegion = player.getRegion();
                } else {
                    transferFrame.add(addLabel("You have unsuccessfully fled from the police"));
                    transferFrame.add(addLabel("You now have "
                            + player.getCredits() + " credits."));
                    transferFrame.add(addLabel("You're current cargo is "
                            + currShip.getCurrCargo()));
                    transferFrame.add(addLabel("You're ship's current health is "
                            + currShip.getCurrHealth()));
                    continueButton = new JButton("Continue to Region");

                    transferFrame.add(makeTextPane("Successfully fled from police"));
                    transferFrame.add(addGoodButton());
                }
            } else if (type.equals("Payment")) {
                transferFrame.add(addLabel("You have forfeited items to the police"));
                transferFrame.add(addLabel("You're current cargo is " + currShip.getCurrCargo()));
                continueButton = new JButton("Continue to Region");
                transferFrame.add(makeTextPane("Forfeited items to the police"));
                transferFrame.add(addGoodButton());

            } else if (type.equals("Fight")) {
                if (success) {
                    transferFrame.add(addLabel("You have successfully fought the police"));
                    transferFrame.add(addLabel("You're ship's current fuel is "
                            + currShip.getCurrFuel()));
                    continueButton = new JButton("Continue to Previous Region");

                    transferFrame.add(makeTextPane("Successfully fought the police"));
                    transferFrame.add(addGoodButton());
                } else {
                    transferFrame.add(addLabel("You have unsuccessfully fought the police"));
                    transferFrame.add(addLabel("You now have "
                            + player.getCredits() + " credits."));
                    transferFrame.add(addLabel("You're current cargo is "
                            + currShip.getCurrCargo()));
                    transferFrame.add(addLabel("You're ship's current health is "
                            + currShip.getCurrHealth()));
                    continueButton = new JButton("Continue to Region");

                    transferFrame.add(makeTextPane("Unsuccessfully fought the police"));
                    transferFrame.add(addGoodButton());
                }
            }
        }

        transferFrame.pack();
        transferFrame.setVisible(true);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transferFrame.dispose();
                JFrame region = new RegionScreen(player, currShip, currRegion);
                return;
            }
        });

    }

    public JPanel makeTransferPane() {
        JPanel p = new JPanel();
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);

        return p;
    }

    public JPanel addGoodButton() {
        transferPanel.add(continueButton);
        return transferPanel;
    }

    public JPanel addLabel(String label) {
        JLabel words = new JLabel(label);
        transferPanel.add(words);
        return transferPanel;
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
