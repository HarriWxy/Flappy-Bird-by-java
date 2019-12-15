package src;

import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends BasicFrame implements Runnable{//主要的程序界面实现
	int i=0;
	boolean jumpflag=false;
	//注意左上角为(0,0)坐标点。
	
	public MainFrame() {
		// TODO Auto-generated constructor stub
		super();
		Bird_x=frame_width/3;
		Bird_y=frame_height/2;
		this.setVisible(true);
		listener();
		new Thread(this).start();//线程
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
		if (runable) {
			while (true) {
				try {
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
					Thread.sleep(100);//速度调低了一点原来是50现在是100
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		}
	public static void main(String[] args) {
		MainFrame test=new MainFrame();
	}

}
