package JavaPbl;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;

import PBL.Signup;

public class Signup1 extends JFrame implements ActionListener{
	
	    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l;
	    JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8;
	    JPasswordField pf2;
	    JButton b;
	    JRadioButton r1,r2;
	    Signup1()
	    {
	    	setLayout(new FlowLayout());
	    	l = new JLabel("APPLICATION FORM NO :");
	        l.setFont(new Font("Raleway", Font.BOLD, 25)); 
	        l1 = new JLabel("NAME:");
	        l1.setFont(new Font("Raleway", Font.BOLD, 14));
	        tf1 = new JTextField(15);
	        tf1.setFont(new Font("Arial", Font.BOLD, 14));
            l2 = new JLabel("DOB");
	        l2.setFont(new Font("Raleway", Font.BOLD, 14));        
	        tf2 = new JTextField(15);
	        tf2.setFont(new Font("Arial", Font.BOLD, 14));      
	        l3 = new JLabel("Mobile No:");
	        l3.setFont(new Font("Raleway", Font.BOLD, 14));        
	        tf3 = new JTextField(15);
	        tf3.setFont(new Font("Arial", Font.BOLD, 14));	        
	        l4= new JLabel("Addhar no");
	        l4.setFont(new Font("Raleway", Font.BOLD, 14));    
	        tf4 = new JTextField(15);
	        tf4.setFont(new Font("Arial", Font.BOLD, 14));  
	        l8 = new JLabel("Gender:");
	        l8.setFont(new Font("Raleway", Font.BOLD, 14));	        
	        tf8 = new JTextField(15);
	        tf8.setFont(new Font("Arial", Font.BOLD, 14));
	        l5 = new JLabel("Deposit_amount");
	        l5.setFont(new Font("Raleway", Font.BOLD, 14));	        
	        tf5 = new JTextField(15);	       
	        tf5.setFont(new Font("Arial", Font.BOLD, 14));	        
	        l6= new JLabel("Set Acco NO");
	        l6.setFont(new Font("Raleway", Font.BOLD, 14));	        
	        tf6 = new JTextField(15);
	        tf6.setFont(new Font("Arial", Font.BOLD, 14));	        
	        l7= new JLabel("Set PIN");
	        l7.setFont(new Font("Raleway", Font.BOLD, 14));        
	        tf7 = new JPasswordField(15);
	        tf7.setFont(new Font("Arial", Font.BOLD, 14));	        
	        b = new JButton("SUBMIT");
	        b.setBackground(Color.BLACK);
	        b.setForeground(Color.WHITE);        
	        setLayout(null);	        
	        l.setBounds(90,40,600,30);
	        add(l);
	        l1.setBounds(100,140,100,30);
	        add(l1);	        
	        tf1.setBounds(300,140,300,30);
	        add(tf1);	        
	        l2.setBounds(100,190,200,30);
	        add(l2);	        
	        tf2.setBounds(300,190,300,30);
	        add(tf2);	        
	        l3.setBounds(100,240,100,30);
	        add(l3);	        
	        tf3.setBounds(300,240,300,30);
	        add(tf3);	        
	        l8.setBounds(100,290,100,30);
	        add(l8);	        
	        tf8.setBounds(300,290,300,30);
	        add(tf8);	        
	        l4.setBounds(100,340,200,30);
	        add(l4);	        
	        tf4.setBounds(300,340,300,30);
	        add(tf4);	        
	        l5.setBounds(100,390,200,30);
	        add(l5);	       
	        tf5.setBounds(300,390,300,30);
	        add(tf5);	      
	        l6.setBounds(100,440,100,30);
	        add(l6);	        
	        tf6.setBounds(300,440,300,30);
	        add(tf6);	        
	        l7.setBounds(100,490,200,30);
	        add(l7);   
	        tf7.setBounds(300,490,300,30);
	        add(tf7);	        
	        b.setBounds(235,650,180,40);
	        add(b);	        
	        b.addActionListener(this);    
	        setSize(700,880);
	        setLocation(550,200);
	        setVisible(true);	        
	    }	    
	    public static void main(String[] args){
	        new Signup1().setVisible(true);
	    }
	public void actionPerformed(ActionEvent e) {		 
	try {
Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/22r11a05j0","root","Sumanth@123");    	  
		      	   Statement s =c.createStatement();				
s.executeUpdate("CREATE TABLE IF NOT EXISTS account (name varchar(10), DOB varchar(10),mobile int, gender VARCHAR(255),addhar varchar (20),deposit_amount int,account_number varchar(20),pin varchar(10))");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String name =tf1.getText();
			String DOB =tf2.getText();
			String Mobile =tf3.getText();
			String Gender =tf8.getText();
			String Addhar =tf4.getText();
			String deposit =tf5.getText();
			String Accno =tf6.getText();
			String PIN =tf7.getText();			
			try {
				Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/22r11a05j0","root","Sumanth@123");	  
		      	   Statement s =c.createStatement();
				if(e.getSource()==b)
				{
if(tf6.getText().equals("")||tf7.getText().equals("")||tf5.getText().equals("")||tf4.getText().equals("")||tf3.getText().equals("")||tf2.getText().equals("")||tf1.getText().equals("")||tf8.getText().equals(""))
        JOptionPane.showMessageDialog(null, "Fill all the required fields");					
					else
					{
						Conn c1 = new Conn();
		                String q1 = "insert into account values('"+name+"','"+DOB+"','"+Mobile+"','"+Gender+"','"+Addhar+"','"+deposit+"','"+Accno+"','"+PIN+"')";
		                s.executeUpdate(q1);
		                dispose();
		               new Login1().setVisible(true);
					}				}						}
			catch(Exception ae)
			{
				ae.printStackTrace();
			}	}
}
