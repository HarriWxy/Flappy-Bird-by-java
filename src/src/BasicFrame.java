package src;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;


@SuppressWarnings("serial")
public abstract class BasicFrame extends JFrame{
	Container c;
	JLabel score_lab;//得分标签,可以不用标签，用paint画出来
	int frame_width=720,frame_height=1080;//界面的大小
	ImageIcon back_img,pipe_img;//背景图片,鸟和管道
	Image[]birds_img=new Image[4];
	int score,hisscore;//得分和历史得分
	JPanel center_pan;//中间按钮区域
	boolean runable=true;
	int Bird_x,Bird_y;//鸟的位置
	ArrayList<Integer> uptube;//上面管子的y坐标
	ArrayList<Integer> downtube;//下面管子的y坐标
	public BasicFrame() {
		// TODO Auto-generated constructor stub
		super("Flappy Bird");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c=this.getContentPane();
		c.setLayout(new BorderLayout());
		back_img=new ImageIcon("image/bg.jpg");
		this.setSize(frame_width, frame_height);
		this.setLocationRelativeTo(null);//居中
		this.setFont(new Font("宋体",Font.PLAIN,14));//宋体 14号
		try {
		      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(back_img.getImage(), 0, 0, this);
		if (frame_width>back_img.getIconWidth()) {      
			g.drawImage(back_img.getImage(), back_img.getIconWidth(), 0, this);//加了如果界面放大之后右边补
		}
	}
	public void listener() {
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				super.componentResized(e);
				frame_height=getHeight();
				frame_width=getWidth();
				Bird_x=frame_width/3;
				repaint();
			}
		});
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowDeactivated(e);
				runable=false;
			}
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowActivated(e);
				runable=true;
			}
		});
		
	}
}
