
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows=new ArrayList<String>();
        for(int i=0;i<myText.length()-1;i++){
            String curString = myText.substring(i,i+key.length());
            if(curString.equals(key)){
                String nextString = myText.substring(i+key.length(),i+1+key.length());
                follows.add(nextString);
            }
        }
        return follows;
    }

    
    abstract public String getRandomText(int numChars);

}
