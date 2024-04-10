package bank.management.system;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import java.awt.SystemColor;
import java.sql.ResultSet;

public class displayList extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultListModel<String> customerList = new DefaultListModel<>();

    /**
     * Create the frame.
     */
    public displayList() {
        setTitle("Customer List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 649, 474);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCustomerList = new JLabel("Customer List");
        lblCustomerList.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblCustomerList.setHorizontalAlignment(SwingConstants.CENTER);
        lblCustomerList.setBounds(0, 11, 623, 31);
        contentPane.add(lblCustomerList);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 66, 613, 358);
        contentPane.add(scrollPane);

        JList<String> list = new JList<>(customerList);
        scrollPane.setViewportView(list);

        // Retrieve customer information from the database and display it
        try {
            Connn c = new Connn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM signup"); // Adjust the query based on your database schema
            while (resultSet.next()) {
                String customerInfo = "Name: " + resultSet.getString("name") + ", DOB: " + resultSet.getString("DOB")+", Email: "+ resultSet.getString("email");
                customerList.addElement(customerInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(true);
    }

    public static void main(String[] args) {
        new displayList();
    }
}

