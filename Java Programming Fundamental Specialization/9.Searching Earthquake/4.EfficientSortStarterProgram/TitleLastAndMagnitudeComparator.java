
/**
 * 在这里给出对类 TitleLastAndMagnitudeComparator 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1,QuakeEntry q2){
        String[] info1=q1.getInfo().split(" ");
        String[] info2=q2.getInfo().split(" ");
        int num=info1[info1.length-1].compareTo(info2[info2.length-1]);
        if(num!=0){
            return num;
        }
        else{
            double mag1=q1.getMagnitude();
            double mag2=q2.getMagnitude();
            if(mag1<mag2){
                return -1;
            }
            else if(mag1>mag2){
                return 1;
            }
            return 0;
        }
    
    }
}
