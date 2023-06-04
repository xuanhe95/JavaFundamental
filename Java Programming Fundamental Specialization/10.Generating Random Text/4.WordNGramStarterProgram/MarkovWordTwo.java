
/**
 * 在这里给出对类 MarkovWordTwo 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){

        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1+" "+key2);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key1,key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words,String target1,String target2,int start){
        for(int i=start;i<words.length-1;i++){
            String word = words[i];
            String nextWord = words[i+1];
            if(word.equals(target1) && nextWord.equals(target2)){
                return i;
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(String key1,String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        for(int i=0;i<myText.length-1;i++){
            String curWord1 = myText[i];
            String curWord2 = myText[i+1];
            if(curWord1.equals(key1) && curWord2.equals(key2)){
                String nextWord = myText[i+2];
                follows.add(nextWord);
            }
        }
        return follows;
    }
}
