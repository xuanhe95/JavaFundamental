import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb=new StringBuilder();
        for(int i=whichSlice;i<message.length();i+=totalSlices){
            String curSlice=message.substring(i,i+1);
            sb.append(curSlice);
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc=new CaesarCracker(mostCommon);
        for(int i=0;i<klength;i++){
            String message=sliceString(encrypted,i,klength);
            key[i]=cc.getKey(message);
        }
        return key;
    }
    
    public HashSet readDictionary(FileResource fr){
        HashSet<String> dic=new HashSet<String>();
        for(String line:fr.lines()){
            dic.add(line.toLowerCase());
        }
        return dic;
    }
    
    public int countWords(String message,HashSet<String> dictionary){
        int count=0;
        String[] words=message.split("\\W");
        for(String word:words){
            if(dictionary.contains(word.toLowerCase())){
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted,HashSet<String> dictionary){
        int maxCount=0;
        String decrypted=null;
        int keyLength=0;
        char commonChar=mostCommonCharIn(dictionary);
        for(int i=1;i<=100;i++){
            int[] curKey=tryKeyLength(encrypted,i,commonChar);
            VigenereCipher vc=new VigenereCipher(curKey);
            String curDecrypted=vc.decrypt(encrypted);
            int curCount=countWords(curDecrypted,dictionary);
   
            if(curCount>maxCount){
                maxCount=curCount;
                decrypted=curDecrypted;
                keyLength=i;
            }
        }
        return decrypted;
    }
    
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> map=new HashMap();
        StringBuilder sb=new StringBuilder();
        for(String word:dictionary){
            sb.append(word);
        }
        String allWords=sb.toString().toLowerCase();
        int[] occur=new int[26];
        for(int i=0;i<allWords.length();i++){
            char curChar=allWords.charAt(i);
            int idx=curChar-'a';
            if(idx>=0 && idx<26){
                occur[idx]++;
            }
        }
        int maxOccur=0;
        int idxOfOccur=0;
        for(int i=0;i<26;i++){
            int curOccur=occur[i];
            if(curOccur>maxOccur){
                maxOccur=curOccur;
                idxOfOccur=i;
            }
        }
        char mostCommon=(char)(idxOfOccur+'a');
        return mostCommon;
    }
    
    public void breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages){
        int maxCount=0;
        String decrypted=null;
        String rightLanguage=null;

        for(String language:languages.keySet()){
            HashSet<String> dictionary=languages.get(language);
            String message=breakForLanguage(encrypted,dictionary);
            int curCount=countWords(message,dictionary);
            if(curCount>maxCount){
                maxCount=curCount;
                decrypted=message;
                rightLanguage=language;
            }
        }
        System.out.println(decrypted);
        System.out.println("***********************************************************");
        System.out.println("Your file's language is: " + rightLanguage);
    }
    
    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        System.out.println("Please select your dictionaries.");
        DirectoryResource dr=new DirectoryResource();
        HashMap<String,HashSet<String>> languages=new HashMap<String,HashSet<String>>();
        for(File file:dr.selectedFiles()){
            String language=file.getName();
            FileResource fr=new FileResource(file);
            HashSet<String> dictionary=readDictionary(fr);
            languages.put(language,dictionary);
            System.out.println("Processing " + language + " dictionary.");
        }
        System.out.println("Done!");
        System.out.println("***********************************************************");
        System.out.println("Please select your file.");
        FileResource fr=new FileResource();
        String encrypted=fr.asString();
        System.out.println("***********************************************************");
        breakForAllLangs(encrypted,languages);
    }
    
}
