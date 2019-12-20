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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;


@SuppressWarnings("serial")
public abstract class BasicFrame extends JFrame{
	//基本的界面，包括游戏的窗口监听器、图片的加载等基本元素
	Container c;
	int frame_width=720,frame_height=1080;//默认的界面的大小
	ImageIcon back_img;//背景图片
	Image[]birds_img;//鸟照片
	Image[]pipe_img;//管道的照片
	int score,hisscore;//得分和历史得分
	JPanel center_pan;//中间按钮区域
	boolean runable=false;//线程是否执行的一个标志位，主要用于控制
	int Bird_x,Bird_y;//鸟的位置
	ArrayList<Integer> uptube;//上面管子的y坐标
	ArrayList<Integer> downtube;//下面管子的y坐标
	ArrayList<Integer> xtube;//管子的横坐标
	Date now;//在开始游戏的按钮触发，用于日志的记录时间
	boolean exitthread=false;//线程结束的标志
	public BasicFrame() {
		// TODO Auto-generated constructor stub
		super("Flappy Bird");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c=this.getContentPane();
		c.setLayout(null);
		back_img=new ImageIcon("image/bg.jpg");
		this.setSize(frame_width, frame_height);//设置窗口基本情况
		this.setLocationRelativeTo(null);//居中
		this.setFont(new Font("宋体",Font.PLAIN,14));//宋体 14号
		try {
		      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
			e.printStackTrace();
		}//设置窗口跟随系统样式
		gethisscore();//获取历史最高分
	}
	private void gethisscore() {//获取文件中的历史最高分
		File file= new File("flappybird hisscore.txt");
		if (!file.exists()) {
			hisscore=0;
		}
		else {
			try {
				Scanner input=new Scanner(file);
				hisscore=Integer.valueOf(input.nextLine().substring(29));
				input.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
	}
	public void listener() {  //监听器
		this.addComponentListener(new ComponentAdapter() {  
			@Override
			public void componentResized(ComponentEvent e) {//窗口改变大小
				// TODO Auto-generated method stub
				super.componentResized(e);
				frame_height=getHeight();
				frame_width=getWidth();
				if (frame_width<720) {
					Bird_x=frame_width/3;
				}
				repaint();
			}
		});
		this.addWindowListener(new WindowAdapter() {//窗口事件监听器
			@Override
			public void windowDeactivated(WindowEvent e) {//使得线程不执行
				// TODO Auto-generated method stub
				super.windowDeactivated(e);
				runable=false;
			}
			@Override
			public void windowActivated(WindowEvent e) {//线程恢复执行
				// TODO Auto-generated method stub
				super.windowActivated(e);
				runable=true;
			}
			public void windowClosed(WindowEvent e) {//线程退出
				// TODO Auto-generated method stub
				super.windowClosed(e);
				exitthread=true;
			}
		});
		
	}
}
