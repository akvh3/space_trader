import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

/**
 * The ship selection screen.
 */
public class ShipScreen extends JFrame implements ActionListener, WindowListener {
    private JFrame shipFrame;
    private JPanel shipPanel;
    private JButton mapButton;
    private JPanel textP;
    private Player player;
    private JRadioButton button1;
    private JRadioButton button2;
    private JRadioButton button3;
    private JRadioButton button4;
    private ButtonGroup group;
    private JLabel currShipLabel;
    private String name1 = "Millenium 1";
    private String name2 = "USS Enterprise";
    private String name3 = "Falcon Heavy";
    private String name4 = "Sturdy Eagle";
    private int initialCargo1 = 40;
    private int initialCargo2 = 30;
    private int initialCargo3 = 20;
    private int initialCargo4 = 10;
    private int initialFuel1 = 30;
    private int initialFuel2 = 60;
    private int initialFuel3 = 90;
    private int initialFuel4 = 120;
    private int initialHealth1 = 30;
    private int initialHealth2 = 40;
    private int initialHealth3 = 10;
    private int initialHealth4 = 20;
    private Ship currShip = null;
    private Ship ship1;
    private Ship ship2;
    private Ship ship3;
    private Ship ship4;

    public ShipScreen(Player player) throws IOException {
        this.player = player;
        shipFrame = new JFrame("Choose your ship");
        shipFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shipFrame.setDefaultLookAndFeelDecorated(true);
        createGUI();
    }

    public void createGUI() throws IOException {
        shipFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel pane = makeShipPane();
        shipFrame.add(pane);
        shipPanel = new JPanel();
        shipPanel.setLayout(new GridLayout(15, 15, 5, 0));
        shipFrame.add(makeTextPane("Ship Selection"));


        shipFrame.add(makeRadioPanel());
        shipFrame.add(makeShipTextPane());
        currShipLabel = new JLabel("Current Ship");
        shipFrame.add(addUpdate(currShipLabel));
        //shipFrame.add(currShipLabel);

        mapButton = new JButton("Go to Map");
        shipFrame.add(addGoodButton("Go To Map"));

        shipFrame.pack();
        shipFrame.setVisible(true);
        mapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shipFrame.dispose();
                JFrame mapScreen = new MapScreen(player, 0, currShip);
            }
        });

        button1.setActionCommand("1");
        button2.setActionCommand("2");
        button3.setActionCommand("3");
        button4.setActionCommand("4");

        class ActionListen implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                String updated = group.getSelection().getActionCommand();
                update(updated);
            }
        }
        button1.addActionListener(new ActionListen());
        button2.addActionListener(new ActionListen());
        button3.addActionListener(new ActionListen());
        button4.addActionListener(new ActionListen());

    }

    private JPanel addUpdate(JLabel myUpdate) {
        shipPanel.add(myUpdate);
        return shipPanel;
    }

    private void update(String newText) {
        if (newText == "1") {
            currShip = new Ship(name1, initialCargo1,
                    initialFuel1, initialHealth1, 20,
                    initialFuel1);
        } else if (newText == "2") {
            currShip = new Ship(name2, initialCargo2,
                    initialFuel2, initialHealth2, 15,
                    initialFuel2);
        } else if (newText == "3") {
            currShip = new Ship(name3, initialCargo3, initialFuel3,
                    initialHealth3, 10,
                    initialFuel3);
        } else if (newText == "4") {
            currShip = new Ship(name4, initialCargo4,
                    initialFuel4, initialHealth4, 5,
                    initialFuel4);
        }
        currShipLabel.setText("Current Ship: "
                + currShip.getName() + " Current Cargo Space: "
                + currShip.getCurrCargo()
                + " Current Fuel Capacity: "
                + currShip.getCurrFuel() + " Current Health: "
                + currShip.getCurrHealth());
    }
    private JPanel makeShipTextPane() {
        // JPanel radioTextPanel = new JPanel(new GridLayout(12,4));

        ship1 = new Ship(name1, initialCargo1, initialFuel1, initialHealth1,
                20, initialFuel1);
        ship2 = new Ship(name2, initialCargo2, initialFuel2, initialHealth2,
                15, initialFuel2);
        ship3 = new Ship(name3, initialCargo3, initialFuel3, initialHealth3,
                10,  initialFuel3);
        ship4 = new Ship(name4, initialCargo4, initialFuel4, initialHealth4,
                5, initialFuel4);

        JLabel ship1C = new JLabel("Max Cargo Space: " + ship1.getmaxCargo());
        JLabel ship1F = new JLabel("Max Fuel Capacity: " + ship1.getmaxFuel());
        JLabel ship1H = new JLabel("Max Health: " + ship1.getmaxHealth());
        JLabel ship1CC = new JLabel("Current Cargo Space: " + ship1.getCurrCargo());
        JLabel ship1FF = new JLabel("Current Fuel Capacity: " + ship1.getCurrFuel());
        JLabel ship1HH = new JLabel("Current Health: " + ship1.getCurrHealth());

        JLabel ship2C = new JLabel("Max Cargo Space: " + ship2.getmaxCargo());
        JLabel ship2F = new JLabel("Max Fuel Capacity: " + ship2.getmaxFuel());
        JLabel ship2H = new JLabel("Max Health: " + ship2.getmaxHealth());
        JLabel ship2CC = new JLabel("Current Cargo Space: " + ship2.getCurrCargo());
        JLabel ship2FF = new JLabel("Current Fuel Capacity: " + ship2.getCurrFuel());
        JLabel ship2HH = new JLabel("Current Health: " + ship2.getCurrHealth());

        JLabel ship3C = new JLabel("Max Cargo Space: " + ship3.getmaxCargo());
        JLabel ship3F = new JLabel("Max Fuel Capacity: " + ship3.getmaxFuel());
        JLabel ship3H = new JLabel("Max Health: " + ship3.getmaxHealth());
        JLabel ship3CC = new JLabel("Current Cargo Space: " + ship3.getCurrCargo());
        JLabel ship3FF = new JLabel("Current Fuel Capacity: " + ship3.getCurrFuel());
        JLabel ship3HH = new JLabel("Current Health: " + ship3.getCurrHealth());

        JLabel ship4C = new JLabel("Max Cargo Space: " + ship4.getmaxCargo());
        JLabel ship4F = new JLabel("Max Fuel Capacity: " + ship4.getmaxFuel());
        JLabel ship4H = new JLabel("Max Health: " + ship4.getmaxHealth());
        JLabel ship4CC = new JLabel("Current Cargo Space: " + ship4.getCurrCargo());
        JLabel ship4FF = new JLabel("Current Fuel Capacity: " + ship4.getCurrFuel());
        JLabel ship4HH = new JLabel("Current Health: " + ship4.getCurrHealth());


        //radioTextPanel.setLayout(new BoxLayout(radioTextPanel, BoxLayout.PAGE_AXIS));
        JPanel radioTextPanel = new JPanel(new GridLayout(3, 4));
        JPanel radioTextPanel2 = new JPanel(new GridLayout(3, 4));
        radioTextPanel.add(ship1C);
        radioTextPanel.add(ship2C);
        radioTextPanel.add(ship3C);
        radioTextPanel.add(ship4C);

        radioTextPanel.add(ship1F);
        radioTextPanel.add(ship2F);
        radioTextPanel.add(ship3F);
        radioTextPanel.add(ship4F);

        radioTextPanel.add(ship1H);
        radioTextPanel.add(ship2H);
        radioTextPanel.add(ship3H);
        radioTextPanel.add(ship4H);

        radioTextPanel2.add(ship1CC);
        radioTextPanel2.add(ship2CC);
        radioTextPanel2.add(ship3CC);
        radioTextPanel2.add(ship4CC);

        radioTextPanel2.add(ship1FF);
        radioTextPanel2.add(ship2FF);
        radioTextPanel2.add(ship3FF);
        radioTextPanel2.add(ship4FF);

        radioTextPanel2.add(ship1HH);
        radioTextPanel2.add(ship2HH);
        radioTextPanel2.add(ship3HH);
        radioTextPanel2.add(ship4HH);

        shipPanel.add(radioTextPanel);
        shipPanel.add(radioTextPanel2);

        return shipPanel;

    }

    private JPanel makeRadioPanel() {
        button1 = new JRadioButton(name1);
        button2 = new JRadioButton(name2);
        button3 = new JRadioButton(name3);
        button4 = new JRadioButton(name4);

        group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        group.add(button3);
        group.add(button4);

        JPanel radioPanel = new JPanel(new GridLayout(1, 4));
        radioPanel.add(button1);
        radioPanel.add(button2);
        radioPanel.add(button3);
        radioPanel.add(button4);

        shipPanel.add(radioPanel);

        return shipPanel;
    }

    public JPanel makeShipPane() {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createTitledBorder("Ship Selection"));
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);

        return p;
    }

    public JPanel makeTextPane(String name) {
        textP = new JPanel();
        addText(name, textP);
        shipPanel.add(textP);
        return shipPanel;
    }

    private void addText(String text, Container container) {
        JLabel newText = new JLabel(text);
        container.add(newText);
    }

    public JPanel addGoodButton(String label) {
        mapButton.setAlignmentY(BOTTOM_ALIGNMENT);
        shipPanel.add(mapButton);
        return shipPanel;
    }

    private void addButton(JButton myButton, Container container) {
        JButton button = myButton;
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setAlignmentY(BOTTOM_ALIGNMENT);
        container.add(button);
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