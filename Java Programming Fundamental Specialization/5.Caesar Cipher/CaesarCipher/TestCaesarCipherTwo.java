
/**
 * 在这里给出对类 TestCaesarCipherTwo 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.util.*;

public class TestCaesarCipherTwo {
    private String halfOfString(String message,int start){
        StringBuilder sb=new StringBuilder();
        int flag=start%2;
        if(start==0){
            for(int i=0;i<message.length();i++){
                if(i%2==flag){
                    sb.append(message.charAt(i));
                }
            }
            return sb.toString();
        } 
        else{
            for(int i=0;i<message.length();i++){
                if(i%2==flag){
                sb.append(message.charAt(i));
                }
            }
            return sb.toString();
        }
    }
    
    private int maxIndex(int[] counts){
        int large=0;
        int largeIdx=0;
        for(int i=0;i<counts.length;i++){
            if(counts[i]>large){
                largeIdx=i;
                large=counts[i];
            }
        }
        return largeIdx;
    }
    
    private int[] countLetters(String input){
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] counts=new int[26];
        for(int i=0;i<input.length();i++){
            String curChar=input.substring(i,i+1);
            int idx=alphabet.indexOf(curChar.toUpperCase());
            if(idx<0){continue;}
            counts[idx]++;
        }
        return counts;
    }
    
    public void simpleTest(){
        FileResource fr=new FileResource();
        String text=fr.asString();
        CaesarCipherTwoOO cc=new CaesarCipherTwoOO(17,3);
        String encrypted=cc.encrypt(text);

        breakCaesarCipher(encrypted);
    }
    
    public int getKey(String input){
        int[] counts=countLetters(input);
        int idx=maxIndex(counts);
        if(idx<4){return idx+22;}
        else{return idx-4;}
    }
    
    public void breakCaesarCipher(String input){
        String message1=halfOfString(input,1);
        String message2=halfOfString(input,0);
        int key1=getKey(message1);
        int key2=getKey(message2);
        CaesarCipherTwoOO cc=new CaesarCipherTwoOO(key1,key2);
        System.out.println(cc.decrypt(input));
        System.out.println("Key1 is " + key1);
        System.out.println("Key2 is " + key2);
    
    }
}
