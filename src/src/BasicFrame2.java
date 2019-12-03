package src;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BasicFrame2 extends JFrame{
	int score;
	JPanel main_pan;   //Ö÷Ìå±³¾°
	JLabel back_lab;   //±³¾°Í¼Ïñ±êÇ©
	public BasicFrame2() {
		// TODO Auto-generated constructor stub
		super("Flappy Bird");
		score=0;
		main_pan=new JPanel();
		this.setSize(720, 1280);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(main_pan);
		main_pan.setLayout(null);
		main_pan.setOpaque(false);
		ImageIcon back_img=new ImageIcon("image/bg.jpg");
		back_lab=new JLabel(back_img);
		back_lab.setBounds(0, 0, back_img.getIconWidth(), back_img.getIconHeight());
		main_pan.add(back_lab);
		this.setVisible(true);
	}
	public static void main(String[] args) {//test
		new BasicFrame2();
	}
}
