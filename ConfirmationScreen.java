import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Screen that confirms your selections from configuration,
 * such as skills, name, and difficulty.
 */
public class ConfirmationScreen
        extends JFrame implements ActionListener, WindowListener {
    private JFrame confirmFrame;
    private JPanel confirmPanel;
    private String characterName;
    private JButton shipButton;
    private String level;
    private int credits;
    private int totalPoints;
    private int fighterSkill;
    private int merchantSkill;
    private int engineerSkill;
    private int pilotSkill;
    private JPanel textP;
    private Player player;


    public ConfirmationScreen(String title, String character,
                              int skillPoints, int fSkill,
                              int mSkill, int eSkill, int pSkill) {
        confirmFrame = new JFrame(title);
        confirmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        characterName = character;
        this.totalPoints = skillPoints;
        fighterSkill = fSkill;
        merchantSkill = mSkill;
        engineerSkill = eSkill;
        pilotSkill = pSkill;

        if (skillPoints == 16) {
            credits = 1000;
            level = "Easy";
        } else if (skillPoints == 12) {
            credits = 500;
            level = "Medium";
        } else if (skillPoints == 8) {
            credits = 100;
            level = "Hard";
        }
        player = new Player(skillPoints,
                fighterSkill, merchantSkill,
                engineerSkill, pilotSkill, level, characterName);
        Game game = new Game(player);
        game.startGame();
        createGUI();
    }

    public void createGUI() {
        confirmFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel pane = makeConfirmPane();
        confirmFrame.add(pane);
        confirmPanel = new JPanel();
        confirmPanel.setLayout(new GridLayout(15, 15, 5, 0));

        shipButton = new JButton("Choose a Ship");

        confirmFrame.add(makeTextPane("Confirmation Page"));
        confirmFrame.add(addLabel("Character Name: " + characterName));

        confirmFrame.add(addLabel("Difficulty: " + level));
        confirmFrame.add(addLabel("Skills: "));
        confirmFrame.add(addLabel("     Fighter: " + fighterSkill));
        confirmFrame.add(addLabel("     Merchant: " + merchantSkill));
        confirmFrame.add(addLabel("     Engineering: " + engineerSkill));
        confirmFrame.add(addLabel("     Pilot: " + pilotSkill));
        confirmFrame.add(addLabel("Starting credits: " + credits));
        confirmFrame.add(addGoodButton("Select a Ship"));

        confirmFrame.pack();
        confirmFrame.setVisible(true);

        shipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmFrame.dispose();
                try {
                    JFrame shipScreen = new ShipScreen(player);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    public String getLevel() {
        return this.level;
    }

    public JPanel addLabel(String label) {
        JLabel words = new JLabel(label);
        confirmPanel.add(words);
        return confirmPanel;
    }

    public JPanel makeConfirmPane() {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createTitledBorder("Confirmation"));
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
        JButton button = new JButton(label);
        confirmPanel.add(shipButton);
        return confirmPanel;
    }
    public JPanel makeButtonPane() {
        JPanel p = new JPanel();
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);
        addButton(shipButton, p);
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

    public Player getPlayer() {
        return player;
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