
/**
 * 在这里给出对类 MiniuesFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class MinutesFilter implements Filter{
    private int myMaxMinutes;
    private int myMinMinutes;
	
    public MinutesFilter(int minMinutes,int maxMinutes) {
        myMaxMinutes = maxMinutes;
        myMinMinutes = minMinutes;
    }
	
    @Override
    public boolean satisfies(String id) {
        if(MovieDatabase.getMinutes(id) >= myMinMinutes && MovieDatabase.getMinutes(id) <= myMaxMinutes){
            return true;
        }
        return false;
    }
}
