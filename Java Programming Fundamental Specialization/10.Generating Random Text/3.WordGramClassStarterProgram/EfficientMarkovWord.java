
/**
 * 在这里给出对类 EfficientMarkovWord 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    Random myRandom;
    private int myOrder;
    private HashMap<Integer,ArrayList<String>> myMap;
    
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        myMap  = new HashMap();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        //printHashMapInfo();
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
        return myMap.get(kGram.hashCode());
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
    
    
    public void buildMap(){
        for(int i=0;i<myText.length-myOrder+1;i++){
            String[] source = new String[myOrder];
            System.arraycopy(myText, i, source, 0, myOrder);
            WordGram wg = new WordGram(source,0,myOrder);;
            Integer hashCode = wg.hashCode();

            String nextWord = "";
            if(i+myOrder < myText.length){
                nextWord = myText[i+myOrder];
                if(!myMap.containsKey(hashCode)){
                    ArrayList<String> follows = new ArrayList<String>();
                
                    follows.add(nextWord);
                    myMap.put(hashCode,follows);
                    wg = wg.shiftAdd(nextWord);
                }
                else{
                    ArrayList<String> follows = myMap.get(hashCode);
                    follows.add(nextWord);
                    myMap.put(hashCode,follows);
                    wg = wg.shiftAdd(nextWord);
                }
            }
            else if(!myMap.containsKey(hashCode)){
                ArrayList<String> follows = new ArrayList<String>();
                myMap.put(hashCode,follows);
            }
        }   
    }
    
    public void printHashMapInfo(){
        if(myMap.size()<50){
            for(Integer hashCode:myMap.keySet()){
                System.out.println("HashCode: "+hashCode+" "+myMap.get(hashCode));
            }
        }
        System.out.println("The number of keys is: " + myMap.size());
        int maxSize=0;
        Integer maxKey=0;
        
        for(Integer hashCode:myMap.keySet()){
            int curSize = myMap.get(hashCode).size();
            if(curSize>maxSize){
                maxSize = curSize;
                maxKey = hashCode;
            }
        }
        
        System.out.println("The maximum number of elements following a key is: "+maxSize);
    }
    
}
