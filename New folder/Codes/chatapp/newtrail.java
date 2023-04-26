package chatapp;
import javax.swing.*;
import java.awt.*;
public class newtrail extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame(); //JFrame Creation       
        frame.setTitle("Add Image"); //Add the title to frame
        //frame.setLayout(null); //Terminates default flow layout
       
        JPanel jp=new JPanel();
        ImageIcon i=new ImageIcon("C:\\Users\\Kish\\Pictures\\Saved Pictures\\id.jpg");
        JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(i); //Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(50, 30, size.width, size.height); //Sets the location of the image
        jp.add(label);
        frame.add(jp,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminate program on close button
        frame.setBounds(100, 200, 1000, 1000); //Sets the position of the frame
        frame.setVisible(true); // Exhibit the frame
    }
   /* public static JLabel format(ImageIcon i) {
    	JLabel label =new JLabel();
    	label.setIcon(i);
    	Dimension size=label.getPreferredSize();
    	
    	return label;
    }*/
}