
/**
 * 在这里给出对类 GenreFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class DirectorFilter implements Filter{
    private String[] myDirectors;
    
    public DirectorFilter(String director) {
        myDirectors = director.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        for(String director:myDirectors){
            if(MovieDatabase.getDirector(id).contains(director)){
                return true;
            }
        }
        return false;
    }

}