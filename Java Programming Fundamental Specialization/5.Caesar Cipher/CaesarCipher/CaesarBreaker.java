
/**
 * 在这里给出对类 CaesarBreaker 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;
import edu.duke.*;
public class CaesarBreaker {
    public String decrypt(String encrypted,int key){
        CaesarCipher cc=new CaesarCipher();
        String message=cc.encrypt(encrypted,26-key);
        return message;
    }
    
    public int[] countLetters(String s){
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
    
    public int maxIndex(int[] counts){
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

    public int getKey(String s){
        int[] counts=countLetters(s);
        int idx=maxIndex(counts);
        if(idx<4){return idx+22;}
        else{return idx-4;}
    }
    
    public String halfOfString(String message,int start){
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
      
    public String merge(String message1,String message2){
        StringBuilder sb1=new StringBuilder(message1);
        StringBuilder sb2=new StringBuilder(message2);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<sb1.length()+sb2.length();i++){
            if(i%2==0){
            char curChar=sb1.charAt(i/2);
            sb.insert(i,curChar);
            }
            else{
            char curChar=sb2.charAt(i/2);
            sb.insert(i,curChar);
            }
        }
        String merge=sb.toString();
        return merge;
    }
    
    public String decryptTwoKeys(String encrypted){
        String message1=halfOfString(encrypted,0);
        String message2=halfOfString(encrypted,1);
        int key1=getKey(message1);
        int key2=getKey(message2);
        String decrypted1=decrypt(message1,key1);
        String decrypted2=decrypt(message2,key2);
        System.out.println("First Key:"+key1);
        System.out.println("Second Key:"+key2);
        return merge(decrypted1,decrypted2);
    }
    
    public String decryptTwoKeysWithInput(String encrypted,int key1,int key2){
        String message1=halfOfString(encrypted,0);
        String message2=halfOfString(encrypted,1);
        String decrypted1=decrypt(message1,key1);
        String decrypted2=decrypt(message2,key2);
        return merge(decrypted1,decrypted2);
    }
    
    public String decryptWithKey(){
        Scanner scanner=new Scanner(System.in);
        System.out.print("Input your key :");
        int key=Integer.parseInt(scanner.nextLine());
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted=decrypt(encrypted,key);
        return decrypted;
    }
    
    public void testDecryptTwoKeysWithInput(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        Scanner scanner1=new Scanner(System.in);
        System.out.print("Input your first key :");
        int key1=Integer.parseInt(scanner1.nextLine());
        Scanner scanner2=new Scanner(System.in);
        System.out.print("Input your second key :");
        int key2=Integer.parseInt(scanner2.nextLine());
        System.out.println(decryptTwoKeysWithInput(encrypted,key1,key2));
    }
    
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = decryptTwoKeys(encrypted);
        System.out.println("****************  Decrypted Text  ****************");
        System.out.println(decrypted);
    }
    
    public void testDecrypteWithKey(){
        System.out.println(decryptWithKey());
    }
    
    
}
