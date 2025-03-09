package JavaPbl;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class Signin extends Frame implements ActionListener {
    Label l1, l2, l3, l4, l5, l6, l7, l8;
    TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9;
    Button b1, b2, b3, b5;
    Connection con;
    Statement stmt;
    Signin() {
        l1 = new Label("Confrim Acc no:");
        l1.setBounds(50, 50, 100, 30);
        l2 = new Label("Withdraw:");
        l2.setBounds(50, 100, 100, 30);
        l3 = new Label("Deposit:");
        l3.setBounds(50, 150, 100, 30);
        tf1 = new TextField();
        tf1.setBounds(200, 50, 100, 30);
        tf2 = new TextField();
        tf2.setBounds(200, 100, 100, 30);
        tf3 = new TextField();
        tf3.setBounds(200, 150, 100, 30);
        b1 = new Button("Account Info");
        b1.setBounds(50, 200, 80, 30);
        b2 = new Button("Withdraw");
        b2.setBounds(150, 200, 80, 30);
        b3 = new Button("Deposit");
        b3.setBounds(250, 200, 80, 30);
        b5 = new Button("LOGOUT");
        b5.setBounds(450, 200, 80, 30);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b5.addActionListener(this);
        add(l1);
        add(l2);
        add(l3);
        add(tf1);
        add(tf2);
        add(tf3);
        add(b1);
        add(b2);
        add(b3);
        add(b5);
        setSize(600, 300);
        setLayout(null);
        setVisible(true);
    }
            public void actionPerformed(ActionEvent e) {
             if (e.getSource() == b1) {           
             String acc = tf1.getText();
             String query = "SELECT * FROM account WHERE account_number = ?";
                try {            	
                JFrame frame = new JFrame("Account Info");
                JLabel nameLabel,Account_number,Balancelabel,l;
                JTextField nameField,Accc,Bal;                 
                JLabel phoneLabel;
                JTextField phoneField;       
                setLayout(new FlowLayout());
                l=new JLabel("ACCOUT INFO");
                l.setBounds(90,40,600,30);
    	        frame.add(l);
                nameLabel = new JLabel("Name:");
                nameLabel.setBounds(100,140,100,30);
                frame.add(nameLabel);
                nameField = new JTextField(10);
                nameField.setBounds(300,140,300,30);
                frame.add(nameField);
                Account_number  = new JLabel("Account Number:");
                Account_number.setBounds(100,190,200,30);
                frame.add(Account_number);
                Accc = new JTextField(10);
                Accc.setBounds(300,190,300,30);
                frame.add(Accc);
                phoneLabel = new JLabel("Phone:");
                phoneLabel.setBounds(100,240,100,30);
                frame.add(phoneLabel);
                phoneField = new JTextField(10);
                phoneField.setBounds(300,240,300,30);
                frame.add(phoneField);
                Balancelabel=new JLabel("Balance");
                Balancelabel.setBounds(100,290,100,30);
                frame.add(Balancelabel);            
                Bal = new JTextField(10);
                Bal.setBounds(300,290,300,30);
                frame.add(Bal);
                Connection c=        DriverManager.getConnection("jdbc:mysql://localhost:3306/22r11a05j0","root","S umanth@123");
                PreparedStatement stmt = c.prepareStatement(query);
                ResultSet rs = stmt.executeQuery("SELECT * FROM account WHERE   account_number ='"+acc+"'");
                if (rs.next()) {
                    nameField.setText(rs.getString("name"));
                    Accc.setText(rs.getString("account_number"));
                    phoneField.setText(rs.getString("mobile"));
                    Bal.setText(rs.getString("deposit_amount"));
                }
                frame.setSize(800, 800);
                frame.setVisible(true);
            }  
            catch (SQLException ex) {
                ex.printStackTrace();
            }          
        } 
        else if (e.getSource() == b2) {           
        	String acc =tf1.getText(); 
            String new_amount =tf2.getText();
            int new_amount1 = Integer.parseInt(new_amount);            
            try {
            	Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/22r11a05j0","root","Sumanth@123");
            	Statement stmt =c.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT deposit_amount FROM account WHERE account_number = " + acc);
                int balance = 0;
                if (rs.next()) {
                    balance = rs.getInt("deposit_amount");                   
                }
                int balance1;
                balance1 = balance - new_amount1;
                String b = "UPDATE account SET deposit_amount = " + balance1 + " WHERE account_number = " + acc;
                 stmt.executeUpdate(b);
                 JOptionPane.showMessageDialog(null, "Cash withdraw Sucessfull");
            } catch (Exception a) {
            	System.out.println(e);
            }
        }
        else if(e.getSource()==b3)
        {	
            String acc =tf1.getText(); 
            String new_amount =tf3.getText();
            int new_amount1 = Integer.parseInt(new_amount);    
            try {
            	Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/22r11a05j0","root","Sumanth@123");
            	Statement stmt =c.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT deposit_amount FROM account WHERE account_number = " + acc);
                int balance = 0;
                if (rs.next()) 
                {
                    balance = rs.getInt("deposit_amount");
                    System.out.println(balance);
                }
                int balance1;
                balance1 = balance + new_amount1;
                String b = "UPDATE account SET deposit_amount = " + balance1 + " WHERE account_number = " + acc;
                 stmt.executeUpdate(b);
                 JOptionPane.showMessageDialog(null, "Cash Deposit sucessfull");
            } catch (Exception a) {
            	System.out.println(e);
            }            
        }
        else if(e.getSource()==b5)
        {
        	dispose();
        }   }
    public static void main(String[] args) {
              Signin l1= new Signin();    
    }
    }
