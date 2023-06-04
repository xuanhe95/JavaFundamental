
/**
 * 在这里给出对类 MatchAllFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;
    public MatchAllFilter(){
        filters=new ArrayList<Filter>();
    }
    
    public void addFilter(Filter filter){
        filters.add(filter);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter filter:filters){
            if(!filter.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    
    public String getName(){
        String name="";
        for(Filter filter:filters){
            name += ("\n"+filter.getName());
        }
        return name;
    }
}
