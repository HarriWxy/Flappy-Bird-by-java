package src;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends BasicFrame implements Runnable{//主要的程序界面实现
	int i=0;
	boolean jumpflag=false;
	//注意左上角为(0,0)坐标点。
	JButton start_but,history_but,help_but,exit_but;
	public MainFrame() {
		// TODO Auto-generated constructor stub
		super();
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
		downtube=new ArrayList<Integer>();//下面管子的y坐标
		uptube=new ArrayList<Integer>();//上面管子的y坐标
		Bird_x=frame_width/3;
		Bird_y=frame_height/2;
		this.setVisible(true);
		getbirdimage();
		listener();
		new Thread(this).start();//线程
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
		g.drawImage(birds_img[i], Bird_x, Bird_y, this);
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				if (runable) {
					if (!jumpflag) {
						if (Bird_y<frame_height-54) {
							Bird_y+=4;
						}
						else {
							Bird_y=frame_height-50;
						}
						repaint();
					}
					else {
						if (Bird_y>65) {
							Bird_y-=35;
						}
						else {
							Bird_y=35;
						}
							jumpflag=false;
							repaint();
					}
					i=(i+1)%4;
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
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					super.mouseClicked(arg0);
					jumpflag=true;
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
			history_but.addActionListener(new ActionListener() {//历史记录监听器
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					new HistoryFrame(1, 1);
				}
			});
		}
	public static void main(String[] args) {
		MainFrame test=new MainFrame();
	}

}
