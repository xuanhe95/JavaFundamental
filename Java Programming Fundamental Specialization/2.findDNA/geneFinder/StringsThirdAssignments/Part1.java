
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
public class Part1 {
    
   public int findStopCodon(String dna, int startIndex, String stopCodon){
        int curStopCodon = dna.indexOf(stopCodon,startIndex);
        while (curStopCodon!=-1){
            if ((curStopCodon-startIndex)%3==0){
                return curStopCodon;
            }
            else{
                curStopCodon = dna.indexOf(stopCodon,curStopCodon+1);
            }
        }  
        return dna.length();
    }
    
   public String findGene(String dna,int where){
        int startIdx = dna.indexOf("ATG",where);
        if (startIdx==-1){
            return "";
        }
        int taaIdx=findStopCodon(dna,startIdx,"TAA");
        int tagIdx=findStopCodon(dna,startIdx,"TAG");
        int tgaIdx=findStopCodon(dna,startIdx,"TGA");
        int minIdx=Math.min(taaIdx,Math.min(tagIdx,tgaIdx));
        if (minIdx==dna.length()){
            return "";
        }
        return dna.substring(startIdx,minIdx+3);
    }
    

    public StorageResource getAllGenes(String dna){
        StorageResource sr=new StorageResource();
        int startIdx = 0;
            while(true){
                String curGene=findGene(dna,startIdx);
                if(curGene.isEmpty()){
                    break;
                }
                sr.add(curGene);
                startIdx = dna.indexOf(curGene,startIdx)+curGene.length();
            }
        return sr;
        }
        
        
    public double cgRatio(String dna){
        int total=0;
        for (int i=0;i<dna.length();i++){
            char g="G".charAt(0);
            char c="C".charAt(0);
            if(dna.charAt(i)==g|| dna.charAt(i)==c){
                total++;
            }
        }
        double t=total;
        double ratio=t/dna.length();
        return ratio;  
    }

    public int countGenes(String dna){
        int startIdx=0;
        int total=0;
        while(true){
            String curGene=findGene(dna,startIdx);
            if(curGene.isEmpty()){
            return total;
            }
            total+=1;
            startIdx=dna.indexOf(curGene,startIdx)+curGene.length();
        }
    }    
    
    public int countCTG(String dna){
        int total=0;
        int curCodon=dna.indexOf("CTG");
        if (curCodon==-1){return -1;}
        while(true){
        if (curCodon==-1){break;}
        total+=1;
        curCodon=dna.indexOf("CTG",curCodon+1);
        }
        return total;
    }
    
    
    public void processGenes(StorageResource sr){
        //print all the Strings in sr that are longer than 9 characters
        //print the number of Strings in sr that are longer than 9 characters
        int longerThan9=0;
        for(String s:sr.data()){
        if(s.length()>60){
            System.out.println("Long Genes: "+s);
            longerThan9++;
        }
        }
        System.out.println("Total number of long Genes: "+longerThan9);
        //print the Strings in sr whose C-G-ratio is higher than 0.35 and its number.
        int highRatio=0;
        for(String s:sr.data()){
        if(cgRatio(s)>0.35){
            System.out.println("High CG-ratio: "+s);
            highRatio++;
        }
        }
        System.out.println("Total number of high CG-ratio: "+highRatio); 
        //print the length of the longest gene in sr. 
        String longestGene="";
        for(String k:sr.data()){
        if (k.length()>longestGene.length()){
        longestGene=k;
        }
        }
        int lengthOfLongestGene=longestGene.length();
        System.out.println("Longest Gene: "+longestGene);
        System.out.println("Lenth of Longest Gene: "+lengthOfLongestGene);     
    }
    
    public void testProcessGenes(){
        
        FileResource fr=new FileResource();
        String dna=fr.asString();
        StorageResource sr= getAllGenes(dna.toUpperCase());
        processGenes(sr);
    }
        
    public void testFindStopCodon(){
        String dna1="ATGAAACCCGGGTAACCAATG";
        String dna2="CCCATGAAACCCGGGTAGCCAATG";
        String dna3="CCCATGAAACCCGGGTAGCCAATG";
        
        
        String taa="TAA";
        String tag="TAG";
        String tga="TGA";
        
        System.out.println(findStopCodon(dna1,dna1.indexOf("ATG"),taa));
        System.out.println(findStopCodon(dna2,dna2.indexOf("ATG"),tag));
        System.out.println(findStopCodon(dna3,dna3.indexOf("ATG"),tga));
    
    }
    
    public void testFindGene(){
        //           0123456789012345678901234567890
        String dna1="ATGAAACCCGGGTAACCAATG";
        String dna2="CCCATGAAACCCGGGTAGCCAATG";
        //              v                  v    v
        String dna3="CCCATGAAACCCGGGAAGCCACATGAATGA";
        String dna4="CCCAAAATGACTTATTAGGGAATG";
        String dna5="ATGACTGTAGTAATGTGAATGA";
        String dna6="ATGATCTAATTTATGCTGCAACGGTGAAGA";
        
        System.out.println(findGene(dna1,0));
        System.out.println(findGene(dna2,0));
        System.out.println(findGene(dna3,0));
        System.out.println(findGene(dna4,0));
        System.out.println(findGene(dna5,0));
        System.out.println(findGene(dna6,0));

    }
    
    public void testOn(String dna){
        System.out.println("Testing printAllGene on " + dna);
        getAllGenes(dna);
        System.out.println("Total number is "+countGenes(dna));
    }
    
    public void test(){
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");    
    }
    
    public void testGetAllGenes(){
        FileResource fr=new FileResource();
        String geneString=fr.asString();
        StorageResource sr= getAllGenes(geneString);
        for(String s:sr.data()){
        System.out.println(s);
        }
    }
    
    public void testCountGenes(){
        FileResource fr=new FileResource();
        String geneString=fr.asString();
        System.out.println(countGenes(geneString));
    }
    
    public void testCountCTG(){
        FileResource fr=new FileResource();
        String geneString=fr.asString();
        System.out.println(countCTG(geneString));
    }
    
}
    
    
    

