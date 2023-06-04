
/**
 * 在这里给出对类 CaesarCipherTwoOO 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class CaesarCipherTwoOO {
    private String alphabet;
    private String shiftedAlphabet1,shiftedAlphabet2;
    private int mainKey1,mainKey2;
    
    public CaesarCipherTwoOO(int key1,int key2){
        alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1=alphabet.substring(key1)+alphabet.substring(0,key1);
        shiftedAlphabet2=alphabet.substring(key2)+alphabet.substring(0,key2);
        mainKey1=key1;
        mainKey2=key2;
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for(int i=0;i<encrypted.length();i++){
            char curChar=encrypted.charAt(i);
            boolean lower=Character.isLowerCase(curChar); 
            int idx=alphabet.indexOf(Character.toUpperCase(curChar));
            if(idx!=-1){
                if(i%2==1){
                    if(lower){
                    encrypted.setCharAt(i,Character.toLowerCase(shiftedAlphabet1.charAt(idx)));
                    }
                    else{
                    encrypted.setCharAt(i,shiftedAlphabet1.charAt(idx));
                    }
                }
                else{
                    if(lower){
                    encrypted.setCharAt(i,Character.toLowerCase(shiftedAlphabet2.charAt(idx)));
                    }
                    else{
                    encrypted.setCharAt(i,shiftedAlphabet2.charAt(idx));
                    }
                }
            }
        }

        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherTwoOO cc = new CaesarCipherTwoOO(26-mainKey1,26-mainKey2);
        String message=cc.encrypt(input);
        return message;
    }
    
    
    
}
