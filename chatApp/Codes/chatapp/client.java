package chatapp;


import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.border.EmptyBorder;




public class client {
	static JFrame jf=new JFrame();
	Socket socket;
	BufferedReader br;
	PrintWriter out;
	JLabel heading;
	JPanel messageBox = new JPanel();
	Box vertical = Box.createVerticalBox();
	JTextField input=new JTextField();
	JButton jb1=new JButton("send");
	JButton jb2=new JButton("Exit");
	JPanel jsp1=new JPanel();
	JPanel jnp1=new JPanel();
	
	JLabel text =new JLabel("Type Here : ");
	Font font=new Font("Times New Roman",Font.PLAIN,20);
	
	public client()
	{
		heading=new JLabel("Client");
//		heading.setText(null);
		messageBox.setLayout(new BorderLayout());
		
		System.out.println("Sending Request.");
		try {
			socket=new Socket("10.35.138.234",7755);
			System.out.println("Connection Done.");
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out=new PrintWriter(socket.getOutputStream());
			CreateGUI();
			eventHandle2();
			startReading();
			//startWriting();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void eventHandle2()
	{
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="send")
				{
					String msg=input.getText();
					JPanel p2 = format(msg);
					JPanel right = new JPanel(new BorderLayout());
					right.add(p2,BorderLayout.LINE_END);
					right.setBackground(Color.gray);
					vertical.add(right);
					vertical.add(Box.createVerticalStrut(15));
					
					messageBox.add(vertical,BorderLayout.PAGE_END);
					input.setText("");
					out.println(msg);
					jf.repaint();
					jf.invalidate();
					jf.validate();
					input.requestFocus();
					out.flush();
				}
				
			}
			
		});
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="Exit" && !socket.isClosed()) {
					System.exit(0);
					//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					try {
						socket.close();
						jf.setVisible(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
			
		});
		input.addKeyListener(new KeyListener() {
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
				//System.out.println("Key released."+e.getKeyCode());
				if(e.getKeyCode()==10 && !socket.isClosed())
				{
					String msg=input.getText();
					JPanel p2 = format(msg);
					JPanel right = new JPanel(new BorderLayout());
					right.add(p2,BorderLayout.LINE_END);
					right.setBackground(Color.gray);
					vertical.add(right);
					vertical.add(Box.createVerticalStrut(15));
					
					messageBox.add(vertical,BorderLayout.PAGE_END);
					input.setText("");
					out.println(msg);
					jf.repaint();
					jf.invalidate();
					jf.validate();
					input.requestFocus();
					out.flush();				
				}
				
			}
		});
	}
	public void CreateGUI()
	{
		jf.setTitle("Client End");
		jf.setSize(600,800);
		jf.setLocationRelativeTo(null);
		//client components setting.
		heading.setFont(font);
		messageBox.setFont(font);
		//messageBox.setEditable(false);
		input.setFont(font);
		input.setColumns(20);
		input.setPreferredSize(new Dimension(100,30));
		input.setHorizontalAlignment(SwingConstants.LEFT);
		jsp1.setLayout(new BorderLayout());
		text.setForeground(Color.white);
		jsp1.add(text,BorderLayout.WEST);
		jsp1.add(input);
		jsp1.setBackground(new Color(7, 94, 84));
		jsp1.add(jb1,BorderLayout.EAST);
		jb1.setBackground(new Color(7, 94, 84));
		jb1.setForeground(Color.white);
		jb1.setSize(100, 100);
		jb1.setHorizontalAlignment(SwingConstants.RIGHT);
		jb1.setBounds(500, 750, 100, 90);
		jb2.setSize(100, 100);
		jb2.setBackground(Color.red);
		jnp1.setLayout(new BorderLayout());
		jnp1.add(heading,BorderLayout.CENTER);
		jnp1.add(jb2,BorderLayout.EAST);
		//jb2.SetH
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setBackground(new Color(7, 94, 84));
		heading.setOpaque(true);
		heading.setBorder(new EmptyBorder(15,15,15,50));
		heading.setForeground(Color.white);
		//messageBox = new JPanel();
		messageBox.setBackground(Color.gray);
		messageBox.setOpaque(true);
		//jf.setUndecorated(true);
		jf.setLayout(new BorderLayout());
		jf.add(jsp1,BorderLayout.SOUTH);
		jf.add(jnp1,BorderLayout.NORTH);
		//jf.add(messageBox,BorderLayout.CENTER);
		JScrollPane scrollpane =new JScrollPane(messageBox);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		jf.getContentPane().add(scrollpane,BorderLayout.CENTER);
		
		
		//jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
	}
	public void startReading()
	{
		Runnable r1=()->{
			System.out.println("start reading.");
			try {
				while(!socket.isClosed())
				{
					
					String msg=br.readLine(); 
					JPanel p2 = format(msg); 
					JPanel left = new JPanel(new BorderLayout()); 
					left.add(p2,BorderLayout.LINE_START); 
					left.setBackground(Color.gray); 
					vertical.add(left); 
					vertical.add(Box.createVerticalStrut(15)); 
					messageBox.add(vertical,BorderLayout.PAGE_END); 
					input.setText(""); 
					jf.repaint(); 
					jf.invalidate(); 
					jf.validate(); 
					if(msg.equals("exit"))
					{
						System.out.println("Client ended the conversation.");
						socket.close();
						break;
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("Connection closed.");
			}
		};
		new Thread(r1).start();
	}
	public static JPanel format(String msg)
	{
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel temp=new JLabel("<html><p style=\"width: 150px\">" + msg + "</p></html>");
		temp.setBackground(new Color(46,139,87));
		temp.setForeground(Color.white);
		Font font=new Font("Klavika",Font.PLAIN,15);
		temp.setBorder(new EmptyBorder(15,15,15,50));
		temp.setFont(font);
		temp.setOpaque(true);
		panel.add(temp);
		//Calendar cal=Calendar.getInstance();
		LocalDateTime ld=LocalDateTime.now();
		String ldf=ld.format(DateTimeFormatter.ofPattern("HH:mm"));
		JLabel time=new JLabel();
		time.setText(ldf);
		time.setBackground(Color.gray);
		panel.setBackground(Color.gray);
		time.setOpaque(true);
		panel.add(time);
		return panel;
	}
	public static void main(String[] args) {
		System.out.println("This is client.");
		new client();

	}

}
