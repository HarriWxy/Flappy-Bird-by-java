package src;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends BasicFrame implements Runnable{//主要的程序界面实现
	int i=0;
	int back_x=0;//背景的相对位移
	boolean jumpflag=false;//鸟是否在跳跃
	boolean playing=false;//游戏是否在玩
	boolean paintbird=false;//跳跃的时候只用重新画鸟，避免反复刷新
	WriteHistory his_wri;
	//注意左上角为(0,0)坐标点。
	JButton start_but,history_but,help_but,exit_but;
	public MainFrame() {
		// TODO Auto-generated constructor stub
		super();
		his_wri=new WriteHistory();
		birds_img=new Image[4];
		pipe_img=new Image[2];//
		center_pan=new JPanel(new GridLayout(4,1));//设置中间按钮区域为网格型，不过比较丑
		start_but=new JButton("开始游戏");
		history_but=new JButton("历史记录");
		help_but=new JButton("游戏帮助");
		exit_but=new JButton("退出");
		center_pan.add(start_but);
		center_pan.add(help_but);
		center_pan.add(history_but);
		center_pan.add(exit_but);
		downtube=new ArrayList<Integer>();//下面管子的y坐标
		uptube=new ArrayList<Integer>();//上面管子的y坐标
		xtube=new ArrayList<>();	
		Bird_x=frame_width/3;
		Bird_y=frame_height/2;
		this.setVisible(true);
		getimages();
		listener();
		new Thread(this).start();//开始线程
	}
	public void getimages() {//获取鸟和管道的图片
		for (int i = 0; i < 3; i++) {
			birds_img[i]=new ImageIcon("image/"+i+".gif").getImage();
		}
		birds_img[3]=new ImageIcon("image/1.gif").getImage();
		for (int i = 0; i < 2; i++) {
			pipe_img[i]=new ImageIcon("image/pic"+(i+1)+".png").getImage();
		}
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		if (playing) {
			for (int i = 0; i < frame_width/720+2; i++) {//画背景
				g.drawImage(back_img.getImage(), back_x+720*i, 0, this);
			}
			g.drawImage(birds_img[i], Bird_x, Bird_y, this);//画鸟
			if (!paintbird) {    //降低重复刷新管道图片导致界面闪烁
				for (int i = 0; i < xtube.size(); i++) {
					g.drawImage(pipe_img[0], xtube.get(i), uptube.get(i), this);
					g.drawImage(pipe_img[1], xtube.get(i), downtube.get(i), this);
				}
			}
			g.setFont(new Font("宋体",Font.PLAIN,30));
			g.drawString("历史最高分:"+hisscore, 40, 60);
			g.drawString("当前得分:"+score, 40, 90);
		}
		else {//当游戏未开始之前画出按钮组件
			g.drawImage(back_img.getImage(), 0, 0, this);
			if (frame_width>back_img.getIconWidth()) {      
				g.drawImage(back_img.getImage(), back_img.getIconWidth(), 0, this);//加了如果界面放大之后右边补
			}
			c.removeAll();
			center_pan.setOpaque(false);
			center_pan.setBounds(frame_width/2-100, 100, 200, 500);
			c.add(center_pan);
			validate();
		}
	}
	public void write_his() {//写历史记录，初始化数据
		hisscore=hisscore>score?hisscore:score;
		his_wri.write(score, now);
		score=0;
		uptube.clear();
		downtube.clear();
		xtube.clear();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int wait=0;
		score=0;
		while (true) {
			try {
				if (runable&playing) {
					if (Bird_y<frame_height-54) {//鸟是否到底部
						Bird_y+=4;
					}
					else {
						Bird_y=frame_height-50;
					}
					if (wait%30==0) {//等待过后产生管道
						uptube.add((int)Math.round(frame_height*Math.random()/2)-630);
						downtube.add((int)Math.round(500*Math.random())+frame_height/2+20);
						xtube.add((int)frame_width);
					}
					back_x=(back_x-10)%720;
					for (int i = 0; i < xtube.size()-1; i++) {
						if (xtube.get(i)<-140) {//去掉多余的数据
							uptube.remove(0);
							downtube.remove(0);
							xtube.remove(0);
						}
						if (xtube.get(i)+140>=Bird_x-10 &xtube.get(i)+140<Bird_x) {//水平位置判断之后分数增加
							score++;
						}
						if (xtube.get(i)>=Bird_x-140 & xtube.get(i)<=Bird_x+40) {//水平位置判断
							 if (Bird_y<uptube.get(i)+600|Bird_y+30>downtube.get(i)) {//竖直位置判断
								 playing=false;
								 int m=JOptionPane.showConfirmDialog(this, "是否重新开始？", "Game Over!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								 if (m==0) {
									write_his();
									playing=true;
									now=new Date();
								}
								 else {
									 write_his();
									 playing=false;
								}
								 repaint();
								 break;
							}
						}
						xtube.set(i, xtube.get(i)-10);
					}
					repaint();
					i=(i+1)%4;
					wait++;
				}
					if (exitthread) {//退出线程
						break;
					}
					Thread.sleep(100);//线程停止0.1s
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public void birdjump() {//鸟的跳跃
		if (Bird_y>65) {
			Bird_y-=35;
		}
		else {
			Bird_y=35;
		}
		paintbird=true;
		repaint();
		paintbird=false;
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
					birdjump();
				}
				else {
					
				}
			}
		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//鸟跳跃
				// TODO Auto-generated method stub
				super.mouseClicked(arg0);
				birdjump();
			}
		});
		exit_but.addActionListener(new ActionListener() {//退出
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		start_but.addActionListener(new ActionListener() {//开始
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				c.remove(center_pan);
				runable=true;
				playing=true;
				now=new Date();
				repaint();
			}
		});
		help_but.addActionListener(new ActionListener() {//帮助界面
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new HelpFrame();
			}
		});
		history_but.addActionListener(new ActionListener() {//历史记录界面
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				new HistoryFrame();
			}
		});
	}
	public static void main(String[] args) {
		MainFrame test=new MainFrame();
	}

}
