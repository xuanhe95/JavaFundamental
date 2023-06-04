
/**
 * 在这里给出对类 DepthFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class DepthFilter implements Filter {
    private double depthMin;
    private double depthMax;
    public DepthFilter(double min,double max){
        depthMin=min;
        depthMax=max;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getDepth() > depthMin && qe.getDepth() < depthMax;
    }
    
    public String getName(){
        return "Depth Filter";
    }
}
