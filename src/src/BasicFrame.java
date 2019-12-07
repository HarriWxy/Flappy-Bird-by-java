package src;

import java.awt.Container;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public abstract class BasicFrame extends JFrame{
	Container c;
	JLabel back_lab;   //����ͼ���ǩ
	JLabel score_lab;//�÷ֱ�ǩ
	int frame_width=720,frame_height=1080;//����Ĵ�С
	public BasicFrame() {
		// TODO Auto-generated constructor stub
		super("Flappy Bird");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c=this.getContentPane();
		c.setLayout(null);
		ImageIcon back_img=new ImageIcon("image/bg.jpg");
		
		this.setSize(frame_width, frame_height);
		c.add(back_lab);
		score_lab=new JLabel();
		score_lab.setOpaque(false);
		score_lab.setBounds(frame_width/2-20, frame_height/3-20, 40, 40);
		this.setVisible(true);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(null, 0, 0, 0, 0, null);
	}
}
