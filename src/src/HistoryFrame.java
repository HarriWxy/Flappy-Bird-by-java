package src;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class HistoryFrame {
	public HistoryFrame(int score, int hisscore) {
		// TODO Auto-generated constructor stub
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
}
