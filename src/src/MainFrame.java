package src;

import java.awt.Desktop;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends BasicFrame implements Runnable{//��Ҫ�ĳ������ʵ��
	int i=0;
	int Bird_x=frame_width/3;//���λ��
	int Bird_y=frame_height/2;//
	ArrayList<Integer> uptube=new ArrayList<Integer>();//������ӵ�y����
	ArrayList<Integer> downtube=new ArrayList<Integer>();//������ӵ�y����
	//ע�����Ͻ�Ϊ(0,0)����㡣
	
	public MainFrame() {
		// TODO Auto-generated constructor stub
		super();
		new Thread(this).start();//�߳�
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
				Bird_y+=4;
				i=(i+1)%4;
				Thread.sleep(50);
				repaint();
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
	}

}
