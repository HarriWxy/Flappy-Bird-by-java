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
		if (frame_width>back_img.getIconWidth()) {       //(貌似可以用JFrame.setResizable(ture)替换，表示允许生成的窗体改变大小)
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
			//我不是很懂为什么有两个help监听器
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		history_but.addActionListener(new ActionListener() {//历史记录监听器
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				File file=new File("flappybird hisscore");
				if(!file.exists()){
					try {
						PrintWriter output=new PrintWriter(file);
						score=0;//如果不存在历史记录文件，创建文件并初始化当前得分为0
						hisscore=0;//初始化历史最高分为0
						output.println(0);
						output.println(0);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
				else{
					try {
						Scanner input=new Scanner(file);
						score=input.nextInt();
						hisscore=input.nextInt();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
				JFrame score_jf=new JFrame();//得分纪录界面
				score_jf.setSize(100,100);//大小应该要调，后面的大小和间距也应该要调
				score_jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				score_jf.setLocationRelativeTo(null);//居中
				score_jf.setFont(new Font("宋体",Font.PLAIN,14));//宋体 14号
				score_jf.setTitle("当前得分和历史最高分");
				FlowLayout fl=new FlowLayout(FlowLayout.LEFT,5,5); //流式布局 每个组件之间相隔5cm
				score_jf.setLayout(fl);//设置顶级容器的布局为流式布局
				ImageIcon icon=new ImageIcon("image/0.gif");//给界面设置个背景
				JLabel labpic=new JLabel(icon);
				Dimension dim1 = new Dimension(100,100);//设置大小
				labpic.setPreferredSize(dim1);
				score_jf.add(labpic);
				Dimension dim = new Dimension(70,40);//设置大小
				JLabel score_lb=new JLabel();
				score_lb.setText("当前得分："+score);//当前得分标签
				score_lb.setPreferredSize(dim);
				JLabel hisscore_lb=new JLabel();
				hisscore_lb.setText("历史最高："+hisscore);//历史最高得分标签
				hisscore_lb.setPreferredSize(dim);
				score_jf.add(score_lb);
				score_jf.add(hisscore_lb);
				score_jf.setVisible(true);//设置可见
			}
		});
	}
	//基本的界面框架是这样，然后在想需不需要弄一下根据窗口拖动大小变化的窗口
}
