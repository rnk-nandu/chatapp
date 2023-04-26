package chatapp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;
import javax. imageio.*;
import java.nio.file.*;
public class loginpage1{
    JFrame jf = new JFrame();
    JLabel l1,l2;
    JTextField t1;
    JPasswordField jp;
    JButton jb1;
    JButton jb2;
    JPanel p;

    String name;
    loginpage1(){
        jf.setSize(600,600);
        jf.setLayout(new BorderLayout());


        p=new JPanel();
        p.setLayout(null);
        jf.setBackground(Color.magenta);

        l1 = new JLabel("Enter Username");

        t1 = new JTextField(20);
       ;
        l2 = new JLabel("Enter Password");

        jp = new JPasswordField(20);

        jb1 = new JButton("Login");

        jb2 = new JButton("SignUp");



        ImageIcon i=new ImageIcon("img4.png");
        Image img = i.getImage();
        Image imageScale = img.getScaledInstance(300,170,Image.SCALE_SMOOTH);
        ImageIcon scaledImage = new ImageIcon(imageScale);
        JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(scaledImage); //Sets the image to be displayed as an icon
        //Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(150, 0, 300, 170);
        label.setBackground(Color.white);
        label.setOpaque(true);
        //l3.setBounds(250,5,200,200);
        l1.setBounds(180,250,100,30);
        t1.setBounds(300 ,250,150,30);
        l2.setBounds(180,300,100,30);
        jp.setBounds(300,300,150,30);



        jb1.setBounds(180,350,100,30);
        jb1.setBackground(Color.BLUE);
        jb1.setForeground(Color.white);
        jb2.setBounds(310,350,100,30);
        jb2.setBackground(Color.BLUE);
        jb2.setForeground(Color.white);
     //  p.add(l3);
        p.add(l1);
        p.add(t1);
       p.add(l2);
       p.add(jp);

        p.add(jb1);
       p.add(jb2);
        p.add(label);
        p.setBackground(new Color(255,255,255));
        eventHandle123();
        jf.add(p,BorderLayout.CENTER);
        jf.setVisible(true);
    }

    public void eventHandle123()
    {
        jb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand()=="Login") {
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db","root","2021@Hope");
                        Statement st = con.createStatement();
                        name=t1.getText();
                        String query = "select password from credentials where username = '"+name+"'";
                        
                        ResultSet rs = st.executeQuery(query);
                        String get_password="";
                        char[] passwordChars = jp.getPassword();
                        String password = new String(passwordChars);
                        while(rs.next()) {
                            get_password = rs.getString(1);
                        }
                        if(get_password!=null && get_password.equals(password))  {


                            jf.setVisible(false);

                            JOptionPane.showMessageDialog(null, "login success");
                            new client();

                        }
                        else {
                            JOptionPane.showMessageDialog(null, "username or password is incorrect");
                        }
                    }
                    catch(Exception e1) {
                        System.out.println(e1);
                    }
                }

            }

        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand()=="SignUp")
                {
                    new signingup();
                }

            }

        });
        jp.addKeyListener(new KeyListener() {

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
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db","root","2021@Hope");
                        System.out.println("database is connected");
                        Statement st = con.createStatement();
                        name=t1.getText();
                        String query = "select password from credentials where username = '"+name+"'";
                        System.out.println(name);
                        ResultSet rs = st.executeQuery(query);
                        String get_password="";
                        char[] passwordChars = jp.getPassword();
                        String password = new String(passwordChars);
                        while(rs.next()) {
                            get_password = rs.getString(1);
                            System.out.println("Password :"+get_password);
                            System.out.println("Password is :"+password);
                        }
                        if(get_password!=null && get_password.equals(password))  {


                            jf.setVisible(false);

                            JOptionPane.showMessageDialog(null, "login success");
                            new client();

                        }
                        else {
                            JOptionPane.showMessageDialog(null, "username or password is incorrect");
                        }
                    }
                    catch(Exception e1) {
                        System.out.println(e1);
                    }

                }

            }

        });
    }

    public static void main(String[] args) {

        new loginpage1();


    }

}
