package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteHistory {
    //写历史记录
    private int score;
    private int hisscore;
    WriteHistory(int score,int hisscore){
        this.score=score;
        this.hisscore=hisscore;
    }
    public void write() throws FileNotFoundException {
        File file= new File("flappybird hisscore");
        PrintWriter output=new PrintWriter(file);
        if(score>hisscore) {
            output.println(score);//第一行写当前得分
            output.println(hisscore);//第二行写历史最高分
        }
        //如果当前得分小于历史最高分，则读取历史最高分然后和当前得分一起写入文件
        else{
            Scanner input=new Scanner(file);
            int temp=input.nextInt();
            hisscore=input.nextInt();
            output.println(score);
            output.println(hisscore);
        }
    }
}
