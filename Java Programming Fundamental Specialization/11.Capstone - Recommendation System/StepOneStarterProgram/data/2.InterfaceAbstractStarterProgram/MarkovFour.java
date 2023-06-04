
/**
 * 在这里给出对类 MarkovFour 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;

public class MarkovFour extends AbstractMarkovModel {
	
    public MarkovFour() {
	myRandom = new Random();
    }
	
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
	
    public void setTraining(String s){
        myText = s.trim();
    }
	
    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows=new ArrayList<String>();
        for(int i=0;i<myText.length()-3;i++){//边界处理？
            String curString = myText.substring(i,i+key.length());
            if(curString.equals(key)){
                String nextString = myText.substring(i+key.length(),i+1+key.length());
                follows.add(nextString);
            }
        }
        return follows;
    }

    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index,index+4);
        sb.append(key);
        
        for(int k=0; k < numChars; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key=key.substring(1)+next;
        }
        return sb.toString();
    }
    
    public String toString(){
        String s = "MarkovModel of order 4";
        return s;
    }
}
