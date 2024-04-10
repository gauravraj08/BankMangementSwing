package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class Menu extends JFrame {

    private JPanel contentPane;

    
    public Menu() {
        setTitle("Banking System");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 649, 474);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblBankingSystem = new JLabel("Banking System");
        lblBankingSystem.setHorizontalAlignment(SwingConstants.CENTER);
        lblBankingSystem.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblBankingSystem.setBounds(0, 69, 613, 59);
        contentPane.add(lblBankingSystem);

        JButton btnDepositToAccount = new JButton("Deposit To Account");
        btnDepositToAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform action for depositing to account
                //JOptionPane.showMessageDialog(getComponent(0), "Deposit To Account functionality goes here.");
                new Login();
            }
        });
        btnDepositToAccount.setBounds(217, 213, 194, 33);
        contentPane.add(btnDepositToAccount);

        JButton btnWithdrawFromAccount = new JButton("Withdraw From Account");
        btnWithdrawFromAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform action for withdrawing from account
                //JOptionPane.showMessageDialog(getComponent(0), "Withdraw From Account functionality goes here.");
                new Login();
            }
        });
        btnWithdrawFromAccount.setBounds(217, 256, 194, 33);
        contentPane.add(btnWithdrawFromAccount);

        JButton btnDisplayAccountList = new JButton("Display Account List");
        btnDisplayAccountList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform action for displaying account list
                //JOptionPane.showMessageDialog(getComponent(0), "Display Account List functionality goes here.");
                new displayList();
            }
        });
        btnDisplayAccountList.setBounds(217, 300, 194, 32);
        contentPane.add(btnDisplayAccountList);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Exit action
                JOptionPane.showMessageDialog(getComponent(0), "Thanks For Using");
                System.exit(0);
            }
        });
        btnExit.setBounds(217, 343, 194, 33);
        contentPane.add(btnExit);

        JButton btnAddAccount = new JButton("Add Account");
        btnAddAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform action for adding an account
                //JOptionPane.showMessageDialog(getComponent(0), "Add Account functionality goes here.");
                new Signup();
                dispose();
            }
        });
        btnAddAccount.setBounds(217, 166, 194, 36);
        contentPane.add(btnAddAccount);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/icon/1.png")));
        lblNewLabel.setBounds(397, 166, 216, 213);
        contentPane.add(lblNewLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();
    }
}
