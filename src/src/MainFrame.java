package src;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends BasicFrame implements Runnable{//主要的程序界面实现
	int i=0;
	int back_x=0;
	boolean jumpflag=false;
	boolean playing=false;
	//注意左上角为(0,0)坐标点。
	JButton start_but,history_but,help_but,exit_but;
	public MainFrame() {
		// TODO Auto-generated constructor stub
		super();
		birds_img=new Image[4];
		pipe_img=new Image[2];
		score_lab=new JLabel();
		score_lab.setOpaque(false);
		score_lab.setSize(new Dimension(40,40));
		score_lab.setLayout(new GridLayout(2,1));
		score_lab.setOpaque(false);
		center_pan=new JPanel(new GridLayout(4,1));
		start_but=new JButton("开始游戏");
//		start_but.setSize(new Dimension(10,50));
		history_but=new JButton("历史记录");
//		history_but.setSize(new Dimension(10,50));
		help_but=new JButton("游戏帮助");
//		help_but.setSize(new Dimension(10,50));
		exit_but=new JButton("退出");
//		exit_but.setSize(new Dimension(10,50));
		center_pan.add(start_but);
		center_pan.add(help_but);
		center_pan.add(history_but);
		center_pan.add(exit_but);
		downtube=new ArrayList<Integer>();//下面管子的y坐标
		uptube=new ArrayList<Integer>();//上面管子的y坐标
		Bird_x=frame_width/3;
		Bird_y=frame_height/2;
		this.setVisible(true);
		getimages();
		listener();
		new Thread(this).start();//线程
	}
	public void getimages() {
		for (int i = 0; i < 3; i++) {
			birds_img[i]=new ImageIcon("image/"+i+".gif").getImage();
		}
		birds_img[3]=new ImageIcon("image/1.gif").getImage();
		for (int i = 0; i < 2; i++) {
			pipe_img[i]=new ImageIcon("image/pic"+i+".png").getImage();
		}
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		if (playing) {
			g.drawImage(back_img.getImage(), back_x, 0, this);
			g.drawImage(back_img.getImage(), back_img.getIconWidth()+back_x, 0, this);
			if (frame_width>back_img.getIconWidth()) {      
				g.drawImage(back_img.getImage(), 2*back_img.getIconWidth()+back_x, 0, this);//加了如果界面放大之后右边补
				
			}
			g.drawImage(birds_img[i], Bird_x, Bird_y, this);
		}
		else {
			center_pan.setOpaque(false);
			center_pan.setBounds(frame_width/2-100, 100, 200, 500);
			c.add(center_pan);
			validate();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int wait=0;
		while (true) {
			try {
				if (runable&playing) {
					if (Bird_y<frame_height-54) {
						Bird_y+=4;
					}
					else {
						Bird_y=frame_height-50;
					}
					if (wait%5==0) {
						double up=Math.random();
						if (up<0.5) {
							uptube.add((int)Math.round((frame_height*Math.random()/2)));
						}
						else {
							downtube.add(frame_height-(int)Math.round((frame_height*Math.random()/2)));
						}
//						xtube.add(1);
					}
//					xtube.add(frame_width);
					back_x-=10;
					if (back_x==-720) {
						back_x=frame_width;
					}
					repaint();
					i=(i+1)%4;
					wait++;
				}
					
					Thread.sleep(100);//速度调低了一点原来是50现在是100
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public void openfile() {
		File i=new File("image/0.gif");
		try {
			Desktop.getDesktop().open(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
		public void listener() {
			// TODO Auto-generated method stub
			super.listener();
			this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub
					super.keyTyped(arg0);
					if (arg0.getKeyChar()=='p') {//暂停按钮
						playing=!playing;
					}
					if (arg0.getKeyChar()==' ') {
						if (Bird_y>65) {
							Bird_y-=35;
						}
						else {
							Bird_y=35;
						}
						repaint();
					}
					else {
						
					}
				}
			});
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					super.mouseClicked(arg0);
					if (Bird_y>65) {
						Bird_y-=35;
					}
					else {
						Bird_y=35;
					}
					repaint();
				}
			});
			exit_but.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					dispose();
				}
			});
			start_but.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					removeAll();
					runable=true;
					playing=true;
					now=new Date();
					repaint();
				}
			});
			help_but.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					new HelpFrame();
				}
			});
			history_but.addActionListener(new ActionListener() {//历史记录监听器
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
//					new HistoryFrame(0);
				}
			});
		}
	public static void main(String[] args) {
		MainFrame test=new MainFrame();
	}

}
