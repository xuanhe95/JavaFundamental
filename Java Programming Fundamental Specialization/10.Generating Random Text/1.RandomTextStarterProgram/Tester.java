
/**
 * 在这里给出对类 Tester 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows(){
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> follows = markov.getFollows("es");
        for(String text:follows){
            System.out.println(text);
        }
        System.out.println("Size is: "+follows.size());
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("he");
        
        System.out.println("Size is: "+follows.size());
    }
	
    
}
