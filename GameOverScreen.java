import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * The game over screen that you reach if you manage
 * to get to 0 health.
 */
public class GameOverScreen extends JFrame implements ActionListener, WindowListener {

    private JFrame merchantFrame;
    private JPanel merchantPanel = new JPanel();
    private JButton restart;
    private JLabel fail;

    public GameOverScreen() {
        merchantFrame = new JFrame("Game Over");
        merchantFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
    }

    public void createGUI() {
        merchantFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel pane = createMerchantPane();
        merchantPanel.setLayout(new GridLayout(15, 15, 5, 0));
        fail = new JLabel("You have failed, and the game is now over.");
        restart = new JButton("Ok, I would like to start a new game.");
        merchantFrame.add(addLabel(fail));
        merchantFrame.add(addBlankSpace());
        merchantFrame.add(addButton(restart));
        merchantFrame.pack();
        merchantFrame.setVisible(true);

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Universe redo = Universe.getUniverseInstance();
                Universe.restart();
                merchantFrame.dispose();
                JFrame config = new ConfigurationScreen("Set-Up");
            }
        });

    }

    public JPanel createMerchantPane() {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createTitledBorder("Game Over"));
        BoxLayout layout = new BoxLayout(p, BoxLayout.X_AXIS);
        p.setLayout(layout);

        return p;
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