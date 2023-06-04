
/**
 * 在这里给出对类 PhraseFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class PhraseFilter implements Filter{
    private String position;
    private String text;
    public PhraseFilter(String where,String phrase){
        position=where;
        text=phrase;
    }
    public boolean satisfies(QuakeEntry qe){
        String title=qe.getInfo();
        if(position.equals("start")){
            String startTitle=title.substring(0,text.length());
            return startTitle.equals(text);
        }
        else if(position.equals("end")){
            String endTitle=title.substring(title.length()-text.length(),title.length());
            return endTitle.equals(text);
        }
        else if(position.equals("any")){
            return title.indexOf(text)!=-1;
        }
        return false;
    }
    
    public String getName(){
        return "Phrase Filter";
    }
}
