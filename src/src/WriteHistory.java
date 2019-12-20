package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class WriteHistory {
    //写历史记录
    private ArrayList<Integer> his_score=new ArrayList<>();//单独存已有的历史记录，方便对新的记录进行插入，已有的历史记录为降序
    private ArrayList<String> datesandhisscore=new ArrayList<>();//存已有历史记录的时间和历史记录
    WriteHistory(){
    }
    public void write(int score, Date date) {
        File file= new File("flappybird hisscore.txt");
        Scanner input;
        if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			input = new Scanner(file);
			while (input.hasNextLine()){
	            datesandhisscore.add(input.nextLine());//读取已有的时间和历史记录
	            his_score.add(Integer.valueOf(datesandhisscore.get(datesandhisscore.size()-1).substring(29)));//单独将历史记录分数提取出来
	        }
	        PrintWriter output=new PrintWriter(file); 
	        int tag=0;
	        for(int i=0;i<his_score.size();i++){
	            //通过比较大小找到位置插入最新记录
	            if(score>=his_score.get(i)&tag==0) {
	                output.println(date.toString()+" "+score);
	                i--;
	                tag=1;
	            }
	            else {
	                output.println(datesandhisscore.get(i));//输出历史记录
	            }
	        }
	        if (tag==0) {
	        	//如果记录时是最大值，写该记录
	        	output.println(date.toString()+" "+score);
			}
	        output.close();
	        input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
