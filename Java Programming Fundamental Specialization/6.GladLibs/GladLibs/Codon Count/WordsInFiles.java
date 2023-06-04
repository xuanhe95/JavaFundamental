
/**
 * 在这里给出对类 WordsInFiles 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String,ArrayList<String>> map;
    public WordsInFiles(){
        map=new HashMap<String,ArrayList<String>>();
    }
    private void buildWordFileMap(){
        map.clear();
        DirectoryResource dr=new DirectoryResource();
        for(File file:dr.selectedFiles()){
            addWordsFromFile(file);
        }
    }
    private void addWordsFromFile(File f){
        FileResource fr=new FileResource(f);
        for(String word:fr.words()){
            String fileName=f.getName();
            if(map.containsKey(word)){
                if(!map.get(word).contains(fileName)){
                    map.get(word).add(fileName);
                }
            }
            else{
                ArrayList<String> fileNames=new ArrayList<String>(); 
                fileNames.add(fileName);
                map.put(word,fileNames);
            }
        }
    }
    private int maxNumber(){
        int maxNumber=0;
        int curNumber=0;
        for(String word:map.keySet()){
            curNumber=map.get(word).size();
            if(curNumber>maxNumber){
                maxNumber=curNumber;
            }
        }
        return maxNumber;
    }
    private void printFilesln(String word){
        System.out.print("\""+word + "\" appears in " + map.get(word).size() + " times in the files:");
        for(String fileName:map.get(word)){
            System.out.print("\t"+fileName);
        }
        System.out.println("");
    }
    public void tester(){
        buildWordFileMap();
        int maxNumber=maxNumber();
        for(String word:map.keySet()){
            if(map.get(word).size()==maxNumber){
                printFilesln(word);
            }
        }
        System.out.println("There are "+map.size()+" words in files");
    }
    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> wordsList=new ArrayList<String>();
        for(String word:map.keySet()){
            if(map.get(word).size()==number){
                wordsList.add(word);
            }
        }
        return wordsList;
    }
    
    public void quiz(int num){  
        buildWordFileMap();
        int total=wordsInNumFiles(num).size();
        System.out.println("There are "+total+" words in "+num+" of all files");
        System.out.println(wordsInNumFiles(num));
    }
    
    public void quiz2(){
        buildWordFileMap();
        printFilesln("sea");
    
    }
    
}
