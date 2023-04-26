package chatapp;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Arrays;

import javax.swing.*;
class signingup
{
	JFrame jf1;
	JButton b2;
	JButton b1;
	JTextField  jtf;
	JPasswordField jpf1;
	JPasswordField jpf2;
	PrintWriter outing;
	
	signingup()
	{
		jf1=new JFrame();
		JLabel l1=new JLabel("Create Account");
		JLabel l2=new JLabel("Enter Username : ");
		JLabel l3=new JLabel("Set Password : ");
		JLabel l4=new JLabel("Re-Enter Password : ");
		JPanel jp1=new JPanel();
		jtf=new JTextField();
		jpf1=new JPasswordField();
		jpf2=new JPasswordField();
		b1=new JButton("Sign up");
		b2=new JButton("Back");
		jp1.setLayout(null);
		l1.setBounds(200,200,100,100);
		l2.setBounds(70,218,160,150);
		jtf.setBounds(200,280,160,25);
		l3.setBounds(70,269,160,150);
		jpf1.setBounds(200,330,160,25);
		l4.setBounds(70,316,160,150);
		jpf2.setBounds(200,380,160,25);
		b2.setBounds(200,450,100,30);
		b1.setBounds(350,450,100,30);
		jp1.add(l1);
		jp1.add(l2);
		jp1.add(jtf);
		jp1.add(l3);
		jp1.add(jpf1);
		jp1.add(l4);
		jp1.add(jpf2);
		jp1.add(b1);
		jp1.add(b2);
		jf1.add(jp1,BorderLayout.CENTER);
		jf1.setSize(600,700);
		jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf1.setVisible(true);
		
		
		
		eventHandling();
		
	}
	public void eventHandling()
	{
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="Sign up")
				{
					String uname=jtf.getText();
					char[] p1=jpf1.getPassword();
					String pp=new String(p1);
					String ps1=pp;
					char[] p2=jpf2.getPassword();
					String pp2=new String(p2);
					String ps2=pp2;
					if((uname.isEmpty()) || (ps1.length()==0) || (ps2.length()==0)) {
						JOptionPane.showMessageDialog(null, "username or password all are required. please fill");
					}
					else if (Arrays.equals(p1, p2))
					{
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/db","root","2021@Hope");
							PreparedStatement pst=cn.prepareStatement("insert into credentials values(?,?)");
							pst.setString(1, uname);
							pst.setString(2, ps1);
							pst.execute();
							JOptionPane.showMessageDialog(null, "Credentials Created.");
							jf1.setVisible(false);
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Passwords might not same please try again.");
					}
				}
				
				
			}
			
		});
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="Back") {
					jf1.setVisible(false);
					//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					try {
						jf1.setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
			
			
		});
		jpf2.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
					if(e.getKeyCode()==10)
					{
						String uname=jtf.getText();
						char[] p1=jpf1.getPassword();
						String pp=new String(p1);
						String ps1=pp;
						char[] p2=jpf2.getPassword();
						String pp2=new String(p2);
						String ps2=pp2;
						if((uname.isEmpty()) || (ps1.length()==0) || (ps2.length()==0)) {
							JOptionPane.showMessageDialog(null, "username or password all are required. please fill");
						}
						else if (Arrays.equals(p1, p2))
						{
							try {
								Class.forName("com.mysql.cj.jdbc.Driver");
								Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/db","root","2021@Hope");
								PreparedStatement pst=cn.prepareStatement("insert into credentials values(?,?)");
								pst.setString(1, uname);
								pst.setString(2, ps1);
								pst.execute();
								JOptionPane.showMessageDialog(null, "Credentials Created.");
								jf1.setVisible(false);
								
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Passwords might not same please try again.");
						}
				}
				
			}
			
		});
	}
	public static void main(String[] args)
	{
		//new signingup();
	}
}
