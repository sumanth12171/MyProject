package JavaPbl;
import javax.swing.*;
import java.sql.*;
import PBL.Login;
import PBL.Signup;
import JavaPbl.Signin;
import java.awt.*;
import java.awt.event.*;
public class Login1 extends JFrame implements ActionListener{
	    JLabel l1,l2,l3;
	    JTextField tf1;
	    JPasswordField pf2;
	    JButton b1,b2,b3; 
	    Login1(){
	        setTitle("ONLINE BANK MANAGMENT SYSTEM");
	        l1=new JLabel("Welcome to Gcet Bank");
	        l1.setFont(new Font("Osward", Font.BOLD, 38));
	        l1.setBounds(200,40,450,40);
	        add(l1);	        
	        l2 = new JLabel("CardNo/AccNo:");
	        l2.setFont(new Font("Raleway", Font.BOLD, 28));
	        l2.setBounds(125,150,375,30);
	        add(l2);        
	        tf1 = new JTextField(15);
	        tf1.setBounds(300,150,230,30);
	        tf1.setFont(new Font("Arial", Font.BOLD, 14));
	        add(tf1);        
	        l3 = new JLabel("PIN:");
	        l3.setFont(new Font("Raleway", Font.BOLD, 28));
	        l3.setBounds(125,220,375,30);
	        add(l3);	        
	        pf2 = new JPasswordField(15);
	        pf2.setFont(new Font("Arial", Font.BOLD, 14));
	        pf2.setBounds(300,220,230,30);
	        add(pf2);	        	        
	        b1 = new JButton("SIGN IN");
	        b1.setBackground(Color.BLACK);
	        b1.setForeground(Color.WHITE);	        
	        b2 = new JButton("CLEAR");
	        b2.setBackground(Color.BLACK);
	        b2.setForeground(Color.WHITE);	        
	        b3 = new JButton("SIGN UP");
	        b3.setBackground(Color.BLACK);
	        b3.setForeground(Color.WHITE);	        
	        setLayout(null);        
	        b1.setFont(new Font("Arial", Font.BOLD, 14));
	        b1.setBounds(300,300,100,30);
	        add(b1);        
	        b2.setFont(new Font("Arial", Font.BOLD, 14));
	        b2.setBounds(430,300,100,30);
	        add(b2);
	        b3.setFont(new Font("Arial", Font.BOLD, 14));
	        b3.setBounds(300,350,230,30);
	        add(b3);        
	        b1.addActionListener(this);
	        b2.addActionListener(this);
	        b3.addActionListener(this);        
	        setSize(800,480);
	        setLocation(550,200);
	        setVisible(true);
	    }
	        public void actionPerformed(ActionEvent e){
	        try{        
	        	if (e.getSource() == b1) {
	        		 Class.forName("com.mysql.jdbc.Driver");
	        		 Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/22r11a05j0","root","Sumanth@123");
	          	     Statement s =con.createStatement();
	        		try {
	                    PreparedStatement stmt = con.prepareStatement("select * from account where account_number=? and pin=?");
	                    String Acc =tf1.getText();
	                    String pass =pf2.getText();
	                    stmt.setString(1, Acc);
	                    stmt.setString(2, pass);
	                    ResultSet rs = stmt.executeQuery();
	                    if (rs.next()) {
	                    	 new Signin();
	                    } else {
	                    	JOptionPane.showMessageDialog(null, "Invalid password or account number");
	                    }
	                    con.close();
	                } catch (Exception e1) {
	                    System.out.println(e1);
	                }	
	            } 
	        	else if (e.getSource() == b2) {
	                tf1.setText("");
	                pf2.setText(""); 
	            } else if (e.getSource() == b3) {
	                new Signup1();
	                dispose();
	            }}
	        catch(Exception ae){
	            ae.printStackTrace();
	        } }
	    public static void main(String[] args){
	        new Login1().setVisible(true);
	    }	}
