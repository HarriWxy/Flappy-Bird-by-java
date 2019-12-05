package src;

import java.awt.Container;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public abstract class BasicFrame extends JFrame{
	Container c;
	JLabel back_lab;   //±³¾°Í¼Ïñ±êÇ©
	public BasicFrame() {
		// TODO Auto-generated constructor stub
		super("Flappy Bird");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c=this.getContentPane();
		c.setLayout(null);
		ImageIcon back_img=new ImageIcon("image/bg.jpg");
		back_lab=new JLabel(back_img);
		back_lab.setBounds(0, 0, back_img.getIconWidth(), back_img.getIconHeight());
		this.setSize(back_img.getIconWidth(), back_img.getIconHeight());
		c.add(back_lab);
		this.setVisible(true);
	}
}
