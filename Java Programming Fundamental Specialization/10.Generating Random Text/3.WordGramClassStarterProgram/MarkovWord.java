
/**
 * 在这里给出对类 MarkovWord 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    Random myRandom;
    private int myOrder;
    
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words,WordGram target,int start){
        String[] source = new String[myOrder];
        System.arraycopy(words, start, source, 0, myOrder);
        WordGram wg = new WordGram(source,0,myOrder);
        
        for(int i=start;i<words.length-myOrder;i++){
            if(wg.equals(target)){
                return i;
            }
            wg = wg.shiftAdd(words[i+myOrder]);
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();

        for(int i=0;i<myText.length-myOrder;i++){
            int index = indexOf(myText,kGram,i);
            if(index!=-1){
                String nextWord = myText[index+myOrder];
                follows.add(nextWord);
                i=index+1;
                
            }
            else{
                return follows;
            }
        }
        
        return follows;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        
        for(int i=0;i<myOrder;i++){   // caltulate first myOrder-th words
            String key = myText[index+i];
            sb.append(key);
            sb.append(" ");
        }
        String[] source=sb.toString().split(" ");
        WordGram wg = new WordGram(source,0,myOrder);   // initialize wg
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(wg);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            wg = wg.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    
    public void testIndexOf(){
        String s = "this is just a test that yes this is a simple test and this is a test that another test for this is a test that very good test";
        String[] words = s.split(" ");
        String[] source = {"this","is"};
        String[] source2 = {"a","test"};
        WordGram wg = new WordGram(source,0,2);
        WordGram wg2 = new WordGram(source2,0,2);
        //System.out.println(indexOf(words,wg,0));
        //System.out.println(indexOf(words,wg,3));
        //System.out.println(indexOf(words,wg2,0));
        
        String[] source3 = {"a","test","that"};
        WordGram wg3 = new WordGram(source3,0,3);
        
        myText=words;
        
        System.out.println(getFollows(wg));
        System.out.println(getFollows(wg2));
        System.out.println(getFollows(wg3));

    }
    
}
