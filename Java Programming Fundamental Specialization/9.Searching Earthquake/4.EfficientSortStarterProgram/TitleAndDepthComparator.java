
/**
 * 在这里给出对类 TitleAndDepthComparator 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1,QuakeEntry q2){
        String title1=q1.getInfo();
        String title2=q2.getInfo();
        int num=title1.compareTo(title2);
        if(num!=0){
            return num;
        }
        else{
            if(q1.getDepth()<q2.getDepth()){
                return -1;
            }
            else if(q1.getDepth()>q2.getDepth()){
                return 1;
            }
            return 0;
        }
    }

}
