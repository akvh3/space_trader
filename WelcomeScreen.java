import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The first screen of the game, welcomes you to the game.
 */
public class WelcomeScreen extends
        JFrame implements ActionListener, WindowListener {
    /*
     * display a welcome screen
     * needs option to start New Game
     * blank window with the text
     * include start button
     *
     * Features: startButton that when clicked will take you
     * to the configurationScreen where you can edit your player
     * it also has text that welcomes the user
     */

    private static JFrame mainFrame;
    private static JButton startButton;
    private static JPanel welcomePanel = new JPanel();

    public static void createGUI() {
        mainFrame = new JFrame("Welcome Screen");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //Dimension size = new Dimension(500, 400);
        //mainFrame.setPreferredSize(size);
        //mainFrame.setLocation(450, 200);
        welcomePanel.setLayout(new GridLayout(15, 15, 5, 0));
        startButton = new JButton("Start New Game");
        mainFrame.add(addTitle("Welcome to Space Trader!"));
        mainFrame.add(addText(""));
        mainFrame.add(addText("An adventure through the Cosmos!"));
        mainFrame.add(addText(""));
        mainFrame.add(addText(""));
        mainFrame.add(addText(""));

        mainFrame.add(addText(""));
        mainFrame.add(addText(""));
        mainFrame.add(addText(""));
        mainFrame.add(addText(""));
        mainFrame.add(addText(""));

        mainFrame.add(addButton(startButton));
        mainFrame.add(welcomePanel);
        welcomePanel.setBackground(Color.black);
        mainFrame.pack();
        mainFrame.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                JFrame config = new ConfigurationScreen("Set-Up");
            }
        });
    }

    public static JPanel makeWelcomePane() {
        JPanel p = new JPanel();
        welcomePanel.setBorder(BorderFactory.createTitledBorder("Welcome"));
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);

        p.add(makeButtonPane());
        p.add(makeTextPane("Welcome to Space Trader!"));

        return p;
    }

    public static JPanel addImage() throws IOException {
        BufferedImage i = ImageIO.read(new
                File("C:\\Users\\andre\\OneDrive\\Documents\\Edit Files\\src\\planet.png"));
        JLabel iLabel = new JLabel(new ImageIcon(i));
        iLabel.setBounds((int)
                CENTER_ALIGNMENT, (int) BOTTOM_ALIGNMENT, 840, 729);
        welcomePanel.add(iLabel);
        return welcomePanel;
    }

    public static JPanel makeTextPane(String name) {
        JPanel textP = new JPanel();
        addText(name);
        return textP;
    }

    public static JPanel makeButtonPane() {
        JPanel p = new JPanel();
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);
        addButton(startButton);

        return p;
    }

    private static JPanel addText(String text) {
        JLabel newText = new JLabel(text);
        newText.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        newText.setForeground(Color.white);
        welcomePanel.add(newText);
        return welcomePanel;
    }

    private static JPanel addTitle(String text) {
        JLabel newText = new JLabel(text);
        newText.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        Font f1 = new Font(Font.DIALOG,  Font.BOLD, 20);
        newText.setFont(f1);
        newText.setForeground(Color.white);
        welcomePanel.add(newText);
        return welcomePanel;
    }

    private static JPanel addButton(JButton myButton) {
        JButton button = myButton;
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setAlignmentY(BOTTOM_ALIGNMENT);
        welcomePanel.add(button);
        return welcomePanel;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
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