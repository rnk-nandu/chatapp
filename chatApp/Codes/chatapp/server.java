package chatapp;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

class server extends JFrame{
	static JFrame jf=new JFrame();
	JPanel messageBox = new JPanel();
	Box vertical = Box.createVerticalBox();
	JLabel heading;
	JTextField input=new JTextField();
	JButton jb=new JButton("send");
	JButton jb2=new JButton("Exit");
	JPanel jsp1=new JPanel();
	JPanel jnp1=new JPanel();
	JLabel text =new JLabel("Type Here : ");
	Font font=new Font("Times New Roman",Font.PLAIN,20);

	ServerSocket s;
	Socket socket;
	BufferedReader br;
	PrintWriter out;
	public server(){
	heading = new JLabel("server");
	messageBox.setLayout(new BorderLayout());
	try {
		s=new ServerSocket(7255);

		socket=s.accept();

		br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out=new PrintWriter(socket.getOutputStream());
		CreateGUI();
		eventHandle();
		startReading();
		}catch (Exception e) {
			e.printStackTrace();
			}
	}
public void eventHandle() {
	jb.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb)
		{

			String msg=input.getText();
			JPanel p2 = format(msg);
			JPanel right = new JPanel(new BorderLayout());
			right.add(p2,BorderLayout.LINE_END);
			right.setBackground(Color.gray);
			vertical.add(right);
			vertical.add(Box.createVerticalStrut(15));
			messageBox.add(vertical,BorderLayout.PAGE_END);
			out.println(msg);
			input.setText("");
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
			//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.exit(0);
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
		if(e.getKeyCode()==10)
		{

			String msg=input.getText();
			JPanel p2 = format(msg);
			JPanel right = new JPanel(new BorderLayout());
			right.add(p2,BorderLayout.LINE_END);
			right.setBackground(Color.gray);
			vertical.add(right);
			vertical.add(Box.createVerticalStrut(15));
			messageBox.add(vertical,BorderLayout.PAGE_END);
			out.println(msg);
			input.setText("");
			jf.repaint();
			jf.invalidate();
			jf.validate();
			input.requestFocus();
			out.flush();
			}
		}

	});

}
public void CreateGUI() {
	jf.setTitle("Server End");
	jf.setSize(600,800);
	jf.setLocationRelativeTo(null);
	heading.setFont(font);
	messageBox.setFont(font);
	input.setFont(font);
	input.setColumns(20);
	input.setPreferredSize(new Dimension(100,30));
	input.setHorizontalAlignment(SwingConstants.LEFT);
	jsp1.setLayout(new BorderLayout());
	text.setForeground(Color.white);
	jsp1.add(text,BorderLayout.WEST);
	jsp1.add(input);
	jsp1.setBackground(new Color(7, 94, 84));
	jsp1.add(jb,BorderLayout.EAST);
	jnp1.setLayout(new BorderLayout());
	jnp1.add(heading,BorderLayout.CENTER);
	jb2.setBackground(Color.red);
	jnp1.add(jb2,BorderLayout.EAST);
	jb.setSize(100, 100);
	jb.setBackground(new Color(7, 94, 84));
	jb.setForeground(Color.white);
	jb.setHorizontalAlignment(SwingConstants.RIGHT);
	jb.setBounds(500, 750, 100, 90);
	heading.setHorizontalAlignment(SwingConstants.CENTER);
	heading.setBackground(new Color(7, 94, 84));
	heading.setOpaque(true);
	heading.setBorder(new EmptyBorder(15,15,15,50));
	heading.setForeground(Color.white);
	jf.setLayout(new BorderLayout());
	jf.add(jsp1,BorderLayout.SOUTH);
	jf.add(jnp1,BorderLayout.NORTH);
	messageBox.setBackground(Color.gray);
	messageBox.setOpaque(true);
	//jf.setUndecorated(true);
	JScrollPane scrollpane =new JScrollPane(messageBox);
	scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
	jf.getContentPane().add(scrollpane,BorderLayout.CENTER);
	jf.setVisible(true);
}
public void startReading() {
	Runnable r1=()->{
		System.out.println("Reader Started.");
		try {
		while(true && !socket.isClosed())
		{
		messageBox.setLayout(new BorderLayout());
		String msg=br.readLine();
		//System.out.println("client :"+msg);

		JPanel panel = format(msg);
		//System.out.println("client :"+msg);
		JPanel left = new JPanel(new BorderLayout());
		left.setBackground(Color.gray);
		left.add(panel, BorderLayout.LINE_START);
		vertical.add(left);

		vertical.add(Box.createVerticalStrut(15));
		messageBox.add(vertical, BorderLayout.PAGE_END);

		jf.validate();
		if(msg.equals("exit"))
		{
		System.out.println("Client ended the conversation.");
		socket.close();
		break;
		}
		}
		}catch(Exception e)
		{
		//System.out.println("Connection closed reading");
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
	System.out.println("This is Server Going to start.");
	new server();
}

}