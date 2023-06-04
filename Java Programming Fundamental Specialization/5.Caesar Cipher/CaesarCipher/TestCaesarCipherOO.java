
/**
 * 在这里给出对类 TestCaesarCipherOO 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
import edu.duke.*;

public class TestCaesarCipherOO {
    private int[] countLetters(String s){
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] counts=new int[26];
        for(int i=0;i<s.length();i++){
            String curChar=s.substring(i,i+1);
            int idx=alphabet.indexOf(curChar.toUpperCase());
            if(idx<0){continue;}
            counts[idx]++;
        }
        return counts;
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
    
    public void simpleTest(){
        FileResource fr=new FileResource();
        String text=fr.asString();
        CaesarCipherOO cc=new CaesarCipherOO(18);
        breakCaesarCipher(text);
    }
    
    public void breakCaesarCipher(String input){
        int key;
        int[] counts=countLetters(input);
        int idx=maxIndex(counts);
        if(idx<4){key=idx+22;}
        else{key=idx-4;}
        CaesarCipherOO cc=new CaesarCipherOO(key);
        String decrypted=cc.decrypt(input);
        System.out.println(decrypted);
        System.out.println(key);
    }
}
