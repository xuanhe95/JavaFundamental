
/**
 * 在这里给出对类 CodonCount 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String,Integer> map;
    
    public CodonCount(){
        map=new HashMap<String,Integer>();
    }
    private void buildCodonMap(int start,String dna){
        map.clear();
        int count=0;
        for(int i=start;i<dna.length()-2;i+=3){
            String codon=dna.substring(i,i+3);
            if(map.containsKey(codon)){
                map.put(codon,map.get(codon)+1);
            }
            else{
                map.put(codon,1);
            }
        }
    }
    private String getMostCommonCodon(){
        int maxCount=0;
        String maxKey=null;
        for(String codon:map.keySet()){
            if(map.get(codon)>maxCount){
                maxCount=map.get(codon);
                maxKey=codon;
            }
        }
        return maxKey;
    }
    private void printCodonCounts(int start,int end){
        System.out.println("Counts of codons between "+ start +" and "+ end + " inclusive are:");
        for(String codon:map.keySet()){
            if(map.get(codon)>=start && map.get(codon)<=end){
                System.out.println(codon + "\t" + map.get(codon));
            }
        }
    }
    public void tester(){
        FileResource fr=new FileResource();
        String dna=fr.asString().trim().toUpperCase();
        buildCodonMap(0,dna);
        String commonCodon=getMostCommonCodon();
        System.out.println("Reading frame starting with 0 results in "+ map.size() +" unique codons");
        System.out.println("and most common codon is "+ commonCodon +" with count "+ map.get(commonCodon));
        printCodonCounts(1,10);
        
        
        buildCodonMap(1,dna);
        commonCodon=getMostCommonCodon();
        System.out.println("Reading frame starting with 1 results in "+ map.size() +" unique codons");
        System.out.println("and most common codon is "+ commonCodon +" with count "+ map.get(commonCodon));
        printCodonCounts(1,10);
        
        buildCodonMap(2,dna);
        commonCodon=getMostCommonCodon();
        System.out.println("Reading frame starting with 2 results in "+ map.size() +" unique codons");
        System.out.println("and most common codon is "+ commonCodon +" with count "+ map.get(commonCodon));
        printCodonCounts(1,10);
        
    
    }
    
    
}
