package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

@SuppressWarnings("serial")
public class CloseAccount extends JFrame implements ActionListener {
    private String pin;

    public CloseAccount(String pin) {
        this.pin = pin;
        getContentPane().setBackground(new Color(145, 149, 246));
        setSize(400, 300);
        setLocation(400, 200);
        setLayout(null);

        JLabel lblCloseAccount = new JLabel("Close Account");
        lblCloseAccount.setFont(new Font("Arial", Font.BOLD, 20));
        lblCloseAccount.setBounds(120, 30, 200, 30);
        add(lblCloseAccount);

        JButton btnClose = new JButton("Close Account");
        btnClose.setBounds(120, 150, 150, 30);
        btnClose.addActionListener(this);
        add(btnClose);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Close Account")) {
            int balance = 0;
            try {
                Connn c = new Connn();
                ResultSet resultSet = c.statement.executeQuery("Select * from bank where pin = '" + pin + "'");
                while (resultSet.next()) {
                    if (resultSet.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(resultSet.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(resultSet.getString("amount"));
                    }
                }

                if (balance == 0) {
                    c.statement.executeUpdate("delete from login where pin = '" + pin + "'");
                    c.statement.executeUpdate("delete from bank where pin = '" + pin + "'");
                    JOptionPane.showMessageDialog(this, "Account closed successfully!");
                    dispose(); // Close the current frame
                } else {
                    JOptionPane.showMessageDialog(this, "Cannot close account. Balance is not zero.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new CloseAccount("");
    }
}

