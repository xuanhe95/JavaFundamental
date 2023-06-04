
/**
 * 在这里给出对类 MagnitudeFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class MagnitudeFilter implements Filter {
    private double magMin;
    private double magMax;
    public MagnitudeFilter(double min,double max){
        magMin=min;
        magMax=max;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax;
    }
    
    public String getName(){
        return "Magnitude Filter";
    }

}

