
/**
 * 在这里给出对类 WordFrequencies 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords=new ArrayList<String>();
        myFreqs=new ArrayList<Integer>();   
    }
    private void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr=new FileResource();
        int index=0;
        
        for(String word:fr.words()){
            word=word.toLowerCase();
            if(!myWords.contains(word)){
                myWords.add(word);
                myFreqs.add(1);
            }
            else{
                index=myWords.indexOf(word);
                myFreqs.set(index,myFreqs.get(index)+1);
            }
        }
    }
    public void tester(){
        findUnique();
        int numberOfUniqueWords=myWords.size();
        System.out.println("Number of unique words: "+numberOfUniqueWords);
        for(int i=0;i<numberOfUniqueWords;i++){
            if(myFreqs.get(i)>20){
                System.out.println(myFreqs.get(i)+"\t"+myWords.get(i));
            }
        }
        String freqWord=myWords.get(findIndexOfMax());
        int freqNum=myFreqs.get(findIndexOfMax());
        System.out.println("The word that occurs most often and its count are: "+freqWord+" "+freqNum);
    }
    public int findIndexOfMax(){
        int maxIndex=-1;
        int maxFreqs=0;
        for(int i=0;i<myWords.size();i++){
            if(myFreqs.get(i)>maxFreqs){
                maxFreqs=myFreqs.get(i);
                maxIndex=i;
            }
        }
        return maxIndex;
    }
    
    
}
