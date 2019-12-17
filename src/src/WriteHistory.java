package src;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class WriteHistory {
    //写历史记录
    private Integer score;
    private ArrayList<Integer> his_score=new ArrayList<>();//存已有的历史记录，已有的历史记录为降序
    WriteHistory(int score){
        this.score=score;
    }
    public void write() throws Exception {
        File file= new File("C:\\Users\\some people\\Desktop\\Flappy-Bird-by-java-degree2\\src\\src\\flappybird hisscore.txt");
        Scanner input=new Scanner(file);
        while (input.hasNextLine()){
            his_score.add(input.nextInt());//读取已有的历史记录
        }
        PrintWriter output=new PrintWriter(file);
        int n=0;//标记是否插入最新记录
        for(int i=0;i<his_score.size();i++){
            //通过比较大小找到位置插入最新记录
            if(score>his_score.get(i)&n==0) {
                output.println(score);
                n=1;
                i--;
            }
            else
                output.println(his_score.get(i));//输出历史记录
        }
        input.close();
        output.close();
    }
}
