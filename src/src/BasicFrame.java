package src;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public abstract class BasicFrame extends JFrame{
	Container c;
	JLabel score_lab;//得分标签
	int frame_width=720,frame_height=1080;//界面的大小
	ImageIcon back_img;
	public BasicFrame() {
		// TODO Auto-generated constructor stub
		super("Flappy Bird");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c=this.getContentPane();
		c.setLayout(new BorderLayout());
		back_img=new ImageIcon("image/bg.jpg");
		this.setSize(frame_width, frame_height);
		score_lab=new JLabel();
		score_lab.setOpaque(false);
		score_lab.setSize(new Dimension(40,40));
		score_lab.setLayout(new BorderLayout());
		c.add(score_lab);
		this.setVisible(true);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(back_img.getImage(), 0, 0, null);
	}
	//基本的界面框架是这样，然后在想需不需要弄一下根据窗口拖动大小变化的窗口
}
