package src;

import java.awt.Desktop;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends BasicFrame implements Runnable{//主要的程序界面实现
	int i=0;
	public MainFrame() {
		// TODO Auto-generated constructor stub
		super();
		new Thread(this).start();//线程
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(birds_img[i], frame_width/3, frame_height/2, this);
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				repaint();
				i=(i+1)%3;
				Thread.sleep(50);
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
	public static void main(String[] args) {
		MainFrame test=new MainFrame();
//		test.openfile();
	}

}
