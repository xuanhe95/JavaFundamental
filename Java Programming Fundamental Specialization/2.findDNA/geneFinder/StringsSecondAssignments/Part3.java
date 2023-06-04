
/**
 * 在这里给出对类 Part3 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part3 {
  
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
    

    public void printAllGenes(String dna){

        int startIdx = 0;
            while(true){
                String curGene=findGene(dna,startIdx);
                
                if(curGene.isEmpty()){
                    break;
                }
                
                System.out.println(curGene);
                
                startIdx = dna.indexOf(curGene,startIdx)+curGene.length();
            
            }

        
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
        printAllGenes(dna);
        System.out.println("Total number is "+countGenes(dna));
    }
    
    public void test(){
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
        
    }
    
}
