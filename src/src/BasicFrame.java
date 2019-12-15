package src;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public abstract class BasicFrame extends JFrame{
	Container c;
	JLabel score_lab;//得分标签,可以不用标签，用paint画出来
	int frame_width=720,frame_height=1080;//界面的大小
	ImageIcon back_img,pipe_img;//背景图片,鸟和管道
	Image[]birds_img=new Image[4];
	int score,hisscore;//得分和历史得分
	JPanel center_pan;//中间按钮区域
	JButton start_but,history_but,help_but,exit_but;
	boolean runable=true;
	int Bird_x,Bird_y;//鸟的位置
	ArrayList<Integer> uptube=new ArrayList<Integer>();//上面管子的y坐标
	ArrayList<Integer> downtube=new ArrayList<Integer>();//下面管子的y坐标
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
		center_pan=new JPanel();
		center_pan.setLayout(new GridLayout());
		start_but=new JButton("开始游戏");
		history_but=new JButton("历史记录");
		help_but=new JButton("游戏帮助");
		exit_but=new JButton("退出");
		getbirdimage();
	}
	public void getbirdimage() {
		for (int i = 0; i < 3; i++) {
			birds_img[i]=new ImageIcon("image/"+i+".gif").getImage();
		}
		birds_img[3]=new ImageIcon("image/1.gif").getImage();
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
			@Override
			public void componentHidden(ComponentEvent e) {//当界面被隐藏的时候就暂停
				// TODO Auto-generated method stub
				super.componentHidden(e);
				runable=false;
				
			}
			@Override
			public void componentShown(ComponentEvent e) {//界面出现就恢复，但是好像没得用？看一下这个监听器是啥子用
				// TODO Auto-generated method stub
				super.componentShown(e);
				runable=true;
			}
		});
		exit_but.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		start_but.addActionListener(new ActionListener() {//存疑
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		help_but.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new HelpFrame();
			}
		});
		help_but.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	//基本的界面框架是这样，然后在想需不需要弄一下根据窗口拖动大小变化的窗口
}
