
/**
 * 在这里给出对类 DistanceFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class DistanceFilter implements Filter{
    private Location location;
    private double disMax;
    public DistanceFilter(Location where,double max){
        disMax=max;
        location=where;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return location.distanceTo(qe.getLocation()) < disMax;
    }
    
    public String getName(){
        return "Distance Filter";
    }
}
