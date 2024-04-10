package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

@SuppressWarnings("serial")
public class mini extends JFrame implements ActionListener {
    String pin;
    JButton button;
    JTextArea label1;
    JLabel label3;
    JLabel label4;

    public mini(String pin){
        this.pin = pin;
        getContentPane().setBackground(new Color(145, 149, 246));
        setSize(600,400);
        setLocation(20,20);
        setLayout(null);

        // Load bank logo
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/bank2.png")); // Path to your bank logo
        Image icon12 = icon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon icon13 = new ImageIcon(icon12);
        JLabel icon14 = new JLabel(icon13);
        icon14.setBounds(20, 5, 150, 80);
        add(icon14);

        JLabel bankNameLabel = new JLabel("Baka Banker's");
        bankNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        bankNameLabel.setBounds(240, 20, 300, 40);
        add(bankNameLabel);

        label1 = new JTextArea();
        label1.setBounds(20,140,550,200);
        label1.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(label1);
        scrollPane.setBounds(20, 140, 550, 200);
        add(scrollPane);

        JLabel label2 = new JLabel("Mini Statement");
        label2.setFont(new Font("System", Font.BOLD,15));
        label2.setBounds(250,70,200,20);
        add(label2);

        label3 = new JLabel();
        label3.setBounds(20,90,300,20);
        add(label3);

        label4 = new JLabel();
        label4.setBounds(20,360,300,20);
        add(label4);

        JLabel lblSerial = new JLabel("S No");
        lblSerial.setBounds(30, 120, 80, 20);
        add(lblSerial);

        JLabel lblDay = new JLabel("Day");
        lblDay.setBounds(98, 120, 60, 20);
        add(lblDay);

        JLabel lblDate = new JLabel("Date");
        lblDate.setBounds(160, 120, 80, 20);
        add(lblDate);

        JLabel lblTime = new JLabel("Time");
        lblTime.setBounds(215, 120, 80, 20);
        add(lblTime);

        JLabel lblAction = new JLabel("Action");
        lblAction.setBounds(322, 120, 80, 20);
        add(lblAction);

        JLabel lblAmount = new JLabel("Amount");
        lblAmount.setBounds(390, 120, 80, 20);
        add(lblAmount);

        try{
            Connn c = new Connn();
            ResultSet resultSet = c.statement.executeQuery("select * from login where pin = '"+pin+"'");
            while (resultSet.next()){
                label3.setText("Account Number:  "+ resultSet.getString("cardno").substring(0,4) + "XXXXXXXX"+ resultSet.getString("cardno").substring(12));
            }
        }catch (Exception e ){
            e.printStackTrace();
        }

        try{
            int balance =0;
            int serialNumber = 1; // Counter for serial number
            Connn c = new Connn();
            ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '"+pin+"'");

            StringBuilder transactions = new StringBuilder();
            while (resultSet.next()){
                String transaction = serialNumber++ + ".\t" + // Add serial number to transaction
                        resultSet.getString("date") + "\t" +" "+
                        resultSet.getString("type") + "\t" +" "+
                        resultSet.getString("amount") + "\n";

                transactions.append(transaction);

                if (resultSet.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(resultSet.getString("amount"));
                }else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
            label1.setText(transactions.toString()); // Set all transactions to text area
            label4.setText("Your Total Balance is Rs "+balance);
        }catch (Exception e){
            e.printStackTrace();
        }

        button = new JButton("Download PDF");
        button.setBounds(230,420,150,25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            createPDF(label1.getText(), label3.getText(), label4.getText());
        } else {
            setVisible(false);
        }
    }

    static void createPDF(String transactions, String cardNumber, String balance) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Mini_Statement.pdf"));
            document.open();

            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/bank2.png")); // Path to your bank logo
            Image bankLogo = icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(bankLogo, null);
            document.add(image);

            // Add bank name label to PDF
            Paragraph bankName = new Paragraph("Baka Banker's", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD));
            bankName.setAlignment(Element.ALIGN_CENTER);
            document.add(bankName);

            document.add(new Paragraph("Mini Statement\n\n"));
            document.add(new Paragraph("Account Number: " + cardNumber + "\n\n"));
            document.add(new Paragraph("Transactions:\n" + transactions + "\n\n"));
            document.add(new Paragraph(balance));
            document.close();
            JOptionPane.showMessageDialog(null, "PDF generated successfully!");
        } catch (DocumentException | FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new mini("");
    }
}
