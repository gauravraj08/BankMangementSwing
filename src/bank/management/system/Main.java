package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {
    JButton customerButton;
    JButton adminButton;

    public Main() {
        setTitle("Main Menu");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));


        JLabel headingLabel = new JLabel("Welcome to Baka Bank");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(headingLabel);

        // Button for Customer
        customerButton = new JButton("Customer");
        customerButton.addActionListener(this);
        panel.add(customerButton);

        // Button for Admin
        adminButton = new JButton("Admin");
        adminButton.addActionListener(this);
        panel.add(adminButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == customerButton) {
            // Redirect to Customer login page
            new Login();
            dispose(); // Close current frame
        } else if (e.getSource() == adminButton) {
            // Redirect to Admin page
            new AdminPage();
            dispose(); // Close current frame
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
