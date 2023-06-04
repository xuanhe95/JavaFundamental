
/**
 * 在这里给出对类 LargestQuakes 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;

public class LargestQuakes {
    
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //for(QuakeEntry qe:list){
        //    System.out.println(qe);
        //}
        System.out.println("read data for "+list.size()+" quakes.");
        
        ArrayList<QuakeEntry> largestQuake=getLargest(list,50);
        for(QuakeEntry qe:largestQuake){
            System.out.println(qe);
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int index=0;
        double maxMagnitude=0;
        int curIndex=0;
        for(QuakeEntry qe:data){
            if(qe.getMagnitude()>maxMagnitude){
                maxMagnitude=qe.getMagnitude();
                index=curIndex;
            }
            curIndex++;
        }
        return index;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,int howMany){
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy=quakeData;
        for(int i=0;i<howMany;i++){
            int index=indexOfLargest(copy);
            ret.add(copy.get(index));
            copy.remove(copy.get(index));
        }
        return ret;
    }
    
}
