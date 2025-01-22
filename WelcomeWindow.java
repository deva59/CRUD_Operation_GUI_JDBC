import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class WelcomeWindow extends JFrame {
    private JButton next;
    private JLabel welcomeJLabel;

    public WelcomeWindow() {
        getContentPane().setBackground(new Color(192, 192, 192));
        getContentPane().setLayout(null);

        welcomeJLabel = new JLabel("Welcome to my Project");
        welcomeJLabel.setOpaque(true);
        welcomeJLabel.setBackground(new Color(255, 255, 0));
        welcomeJLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 17));
        welcomeJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeJLabel.setBounds(184, 68, 363, 65); // Position and size
        getContentPane().add(welcomeJLabel);

        next = new JButton("Next");
        next.setBackground(new Color(255, 128, 128));
        next.setBounds(314, 194, 100, 50); // Position and size
        getContentPane().add(next);

        // ActionListener for the next button
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AllOperationPerform(); // Open AllOperationPerform window
                dispose(); // Close the WelcomeWindow (optional)
            }
        });

        setSize(728, 488); // Set window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // Make window visible
    }

    public static void main(String[] args) {
        new WelcomeWindow();
    }
}
