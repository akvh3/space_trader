import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

/**
 * The screen where you configure the various aspects
 * of the game, including your name, skills, and level.
 */
public class ConfigurationScreen extends
        JFrame implements ActionListener, WindowListener, ChangeListener {
    /*
     * configurationScreen
     * title: Game Set-Up
     * textbox where players can enter their character's name
     * checkboxes to select level (easy, hard, medium)
     * Text displaying amount of money (based on level selected)
     * meters to set 4 skill levels
     * Confirm button at bottom to go to a confirmation screen
     *
     */
    private JFrame configFrame;
    private String charName;
    private JTextField userEntry;
    private JTextArea characters;
    private JButton confirmButton;
    private JRadioButton easyButton;
    private JRadioButton medButton;
    private JRadioButton hardButton;
    private JLabel skillLabel;
    private ButtonGroup group;
    private JPanel configPanel = new JPanel();
    private int credits;
    private int skillPoints = 0;
    private int fighterSkill = 0;
    private int merchantSkill = 0;
    private int engineerSkill = 0;
    private int pilotSkill = 0;
    private JSlider pilot;
    private JSlider merchant;
    private JSlider engineer;
    private JSlider fighter;


    public ConfigurationScreen(String title) {
        configFrame = new JFrame(title);
        configFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createGUI();
    }

    public void createGUI() {
        configFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        confirmButton = new JButton("Confirm");

        easyButton = new JRadioButton("Easy");
        easyButton.setActionCommand("16");
        medButton = new JRadioButton("Medium");
        medButton.setActionCommand("12");
        hardButton = new JRadioButton("Hard");
        hardButton.setActionCommand("8");

        class ActionListen implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                String updated = group.getSelection().getActionCommand();
                skillPoints = Integer.parseInt(updated);
                update(updated);
            }
        }

        easyButton.addActionListener(new ActionListen());
        medButton.addActionListener(new ActionListen());
        hardButton.addActionListener(new ActionListen());

        JPanel pane = makeWelcomePane();
        configFrame.add(pane);
        configFrame.add(makeTextPane("Configure Your Character"));
        configPanel.setLayout(new GridLayout(15, 15, 5, 0));
        configFrame.add(addLabel("Enter your name below:"));
        configFrame.add(makeTextField());
        configFrame.add(addLabel("Choose your difficulty:"));
        configFrame.add(addRadioButton());
        skillLabel = new JLabel("Allocate your skill points: "
                + getSkillPoints() + " points total");
        configPanel.add(skillLabel);
        configFrame.add(configPanel);
        configFrame.add(addSliders());
        configFrame.add(addGoodButton("Confirm"));
        configFrame.pack();
        configFrame.setVisible(true);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configFrame.dispose();
                JFrame config = new ConfirmationScreen("Confirmation Screen", charName,
                        skillPoints, fighterSkill, merchantSkill, engineerSkill, pilotSkill);
            }
        });
    }

    private void update(String newText) {
        skillLabel.setText("Allocate your skill points: " + newText + " points total");
    }

    public JPanel addLabel(String label) {
        JLabel words = new JLabel(label);
        configPanel.add(words);
        return configPanel;
    }

    public JPanel addSliders() {

        JLabel lPilot = new JLabel("Pilot:");
        pilot = new JSlider(0, getSkillPoints() - fighterSkill - merchantSkill - engineerSkill);
        pilot.setValue(0);
        pilotSkill = pilot.getValue();
        JLabel lFighter = new JLabel("Fighter:");
        fighter = new JSlider(0, getSkillPoints() - pilotSkill - merchantSkill - engineerSkill);
        fighter.setValue(0);
        fighterSkill = fighter.getValue();
        JLabel lMerchant = new JLabel("Merchant:");
        merchant = new JSlider(0, getSkillPoints() - pilotSkill - fighterSkill - engineerSkill);
        merchant.setValue(0);
        merchantSkill = merchant.getValue();
        JLabel lEngineer = new JLabel("Engineer:");
        engineer = new JSlider(0, getSkillPoints() - pilotSkill - fighterSkill - merchantSkill);
        engineer.setValue(0);
        engineerSkill = engineer.getValue();

        pilot.addChangeListener(this);
        merchant.addChangeListener(this);
        engineer.addChangeListener(this);
        fighter.addChangeListener(this);
        pilot.setMajorTickSpacing(1);
        pilot.setPaintTicks(true);
        fighter.setMajorTickSpacing(1);
        fighter.setPaintTicks(true);
        merchant.setMajorTickSpacing(1);
        merchant.setPaintTicks(true);
        engineer.setMajorTickSpacing(1);
        engineer.setPaintTicks(true);
        configPanel.add(lPilot);
        configPanel.add(pilot);
        configPanel.add(lFighter);
        configPanel.add(fighter);
        configPanel.add(lMerchant);
        configPanel.add(merchant);
        configPanel.add(lEngineer);
        configPanel.add(engineer);

        return configPanel;
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            if ((JSlider) e.getSource() == pilot) {
                pilotSkill = source.getValue();
                pilot.setMaximum(getSkillPoints() - fighterSkill - merchantSkill - engineerSkill);
            } else if ((JSlider) e.getSource() == fighter) {
                fighterSkill = source.getValue();
                fighter.setMaximum(getSkillPoints() - pilotSkill - merchantSkill - engineerSkill);
            } else if ((JSlider) e.getSource() == merchant) {
                merchantSkill = source.getValue();
                merchant.setMaximum(getSkillPoints() - fighterSkill - pilotSkill - engineerSkill);
            } else if ((JSlider) e.getSource() == engineer) {
                engineerSkill = source.getValue();
                engineer.setMaximum(getSkillPoints() - fighterSkill - merchantSkill - pilotSkill);
            }
        }
    }

    public JPanel addRadioButton() {
        group = new ButtonGroup();
        group.add(easyButton);
        group.add(medButton);
        group.add(hardButton);

        JPanel radioPanel = new JPanel(new GridLayout(1, 0));
        radioPanel.add(easyButton);
        radioPanel.add(medButton);
        radioPanel.add(hardButton);

        configPanel.add(radioPanel);
        return configPanel;
    }

    public JPanel makeTextField() {
        userEntry = new JTextField(15);
        TextFieldListener tfListen = new TextFieldListener();
        userEntry.addActionListener(tfListen);
        configPanel.add(userEntry);
        return configPanel;
    }


    public JPanel makeWelcomePane() {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createTitledBorder("Set-Up"));
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);
        return p;
    }

    public JPanel addGoodButton(String label) {
        JButton button = new JButton(label);
        configPanel.add(confirmButton);
        return configPanel;
    }

    public JPanel makeTextPane(String name) {
        JPanel textP = new JPanel();
        addText(name, textP);
        return textP;
    }

    public JPanel makeButtonPane() {
        JPanel p = new JPanel();
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);
        addButton(confirmButton, p);
        return p;
    }

    private void addText(String text, Container container) {
        JLabel newText = new JLabel(text);
        container.add(newText);
    }

    private void addButton(JButton myButton, Container container) {
        JButton button = myButton;
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setAlignmentY(BOTTOM_ALIGNMENT);
        container.add(button);
    }
    public void setSkillPoints(int points) {
        skillPoints = points;
    }
    public int getSkillPoints() {
        return skillPoints;
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

    private class TextFieldListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            charName = userEntry.getText();
        }
    }

}





