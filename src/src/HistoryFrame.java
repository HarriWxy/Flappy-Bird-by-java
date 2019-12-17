package src;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class HistoryFrame extends BasicFrame{//得分纪录界面
	//改！！！！写记录的也改！！！！
	JButton back_but=new JButton("返回");
	public HistoryFrame(int score, int hisscore) {
		// TODO Auto-generated constructor stub
		setTitle("FlappyBird History");
//		File hisfile=new File("flappybird hisscore");
//		if(!hisfile.exists()){
//			try {
//				PrintWriter output=new PrintWriter(hisfile);
//				score=0;//如果不存在历史记录文件，创建文件并初始化当前得分为0
//				hisscore=0;//初始化历史最高分为0
//				output.println(0);
//				output.println(0);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//		else{
//			try {
//				Scanner input=new Scanner(hisfile);
//				score=input.nextInt();
//				hisscore=input.nextInt();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
		
				
		this.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));//设置顶级容器的布局为流式布局
		Dimension dim = new Dimension(70,40);//设置大小
		JLabel score_lb=new JLabel();
		score_lb.setText("当前得分："+score);//当前得分标签
		score_lb.setPreferredSize(dim);
		JLabel hisscore_lb=new JLabel();
		hisscore_lb.setText("历史最高："+hisscore);//历史最高得分标签
		hisscore_lb.setPreferredSize(dim);
		this.add(score_lb);
		this.add(hisscore_lb);
		
		
		JPanel bottom_pan=new JPanel(new FlowLayout());
		bottom_pan.setOpaque(false);
		bottom_pan.setBounds(0,frame_height-10,frame_width,10);
		bottom_pan.add(back_but);
		c.add(bottom_pan);
		
		listener();
		this.setVisible(true);//设置可见
	}
	@Override
		public void listener() {
			// TODO Auto-generated method stub
			super.listener();
			back_but.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					dispose();
				}
			});
		}
	public static void main(String[] args) {
		new HistoryFrame(1, 2);
	} 
}
