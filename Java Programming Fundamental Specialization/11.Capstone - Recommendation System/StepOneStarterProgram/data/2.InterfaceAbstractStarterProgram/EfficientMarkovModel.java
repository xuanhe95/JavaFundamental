
/**
 * 在这里给出对类 EfficientMarKovModel 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int keyLength;
    private HashMap<String,ArrayList<String>> map;

    public EfficientMarkovModel(int n) {
	myRandom = new Random();
	keyLength = n;
	map = new HashMap();
    }
	
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
	
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }
    
    public void buildMap(){
        for(int i=0;i<myText.length()-keyLength+1;i++){
            String curString = myText.substring(i,i+keyLength);
            String nextString = "";
            if(i+keyLength<myText.length()){
                nextString = myText.substring(i+keyLength,i+1+keyLength);
            }
            if(!map.containsKey(curString)){
                ArrayList<String> follows=new ArrayList<String>();
                follows.add(nextString);
                map.put(curString,follows);
            }
            else{
                ArrayList<String> follows=map.get(curString);
                follows.add(nextString);
                map.put(curString,follows);
            }
        }
    }
    
    public ArrayList<String> getFollows(String key){
        return map.get(key);
    }

    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-keyLength);
        String key = myText.substring(index,index+keyLength);
        sb.append(key);
        
        for(int k=0; k < numChars; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows==null){
               break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key=key.substring(1)+next;
        }
        return sb.toString();
    }
    
    public void printHashMapInfo(){
        int maxSize = 0;
        ArrayList<String> maxKey= new ArrayList<String>();
        ArrayList<String> maxFollow = new ArrayList<String>();
        for(String key:map.keySet()){
            ArrayList curFollow=map.get(key);
            int followSize = curFollow.size();
            if(followSize>maxSize){
                maxSize = curFollow.size();
                maxFollow = curFollow;
            }
            if(map.size()<50){
                System.out.println("Key: "+key+" "+curFollow);
            }
        }
        System.out.println("The number of keys in the HashMap is: "+map.size());
        System.out.println("The largest value in HashMap's size is: "+maxSize);
        
        for(String key:map.keySet()){
            ArrayList curFollow=map.get(key);
            int followSize = curFollow.size();
            if(followSize==maxSize){
                System.out.println("The largest value in HashMap's key is: "+key);
            }
        }
        
    }
    
    public String toString(){
        String s = "EfficientMarkovModel of order "+keyLength;
        return s;
    }
}
