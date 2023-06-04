
/**
 * 在这里给出对类 CaesarCipher 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.util.*;

public class CaesarCipher {
    public String encrypt(String text,int key){
        StringBuilder encrypted = new StringBuilder(text);
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet=alphabet.substring(key)+alphabet.substring(0,key);
        for(int i=0;i<encrypted.length();i++){
            char curChar=encrypted.charAt(i);
            boolean lower=Character.isLowerCase(curChar); 
            int idx=alphabet.indexOf(Character.toUpperCase(curChar));
            if(idx!=-1){
                if(lower){
                    encrypted.setCharAt(i,Character.toLowerCase(shiftedAlphabet.charAt(idx)));
                }
                else{
                    encrypted.setCharAt(i,shiftedAlphabet.charAt(idx));
                }
                
            }
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String text,int key1,int key2){
        StringBuilder encrypted = new StringBuilder(text);
        String text1=encrypt(text,key1);
        String text2=encrypt(text,key2);
        for(int i=0;i<text.length();i++){
            if (i%2==0){
                encrypted.setCharAt(i,text1.charAt(i));
            }
            else{
                encrypted.setCharAt(i,text2.charAt(i));
            }
        }
        return encrypted.toString();
    }
    
    public void testEncrypted(){
        Scanner scanner=new Scanner(System.in);
        System.out.print("Input your key: ");
        int key=Integer.parseInt(scanner.nextLine());
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public void testEncryptTwoKeys(){
        Scanner scanner1=new Scanner(System.in);
        System.out.println("Input your first key: ");
        Scanner scanner2=new Scanner(System.in);
        System.out.println("Input your second key: ");
        int key1=Integer.parseInt(scanner1.nextLine());
        int key2=Integer.parseInt(scanner2.nextLine());
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encryptTwoKeys(message, key1,key2);
        System.out.println("key is " + key1+" and "+key2 + "\n" + encrypted);
    }
    
}
