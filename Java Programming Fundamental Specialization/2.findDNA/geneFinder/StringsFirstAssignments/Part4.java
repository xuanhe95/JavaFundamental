
/**
 * 在这里给出对类 Part4 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class Part4 {
   public void findLinks(){
   URLResource file = new  URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");         
   for (String word : file.lines()) { 
       
       int youtubeIndex=word.toLowerCase().indexOf("youtube.com");
       if(youtubeIndex!=-1){
        int start=word.lastIndexOf("\"",youtubeIndex);
        int end=word.indexOf("\"",youtubeIndex+1);
        String link=word.substring(start+1,end);
        
        System.out.println("Youtube link is "+ link);
        
        }
       
    }

   }

}
