
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part1 {
    public String findSimpleGene(String gene){
        int start = gene.indexOf("ATG");
        if (start == -1){
        return "";
        }
        int end = gene.indexOf("TAG",start+3);
        if (end == -1){
        return "";
        }
        
        if ((start-end)%3==0){
        
        return gene.substring(start,end+3);
        }
        return "";
        
    }


    public void testSimpleGene(){
        String test1="ATGCCCAAATAG";
        String test2="CCCCCCCCC";
        String test3="ATGCCCCCCC";
        String test4="CCCCCCTAG";
        String test5="ATGCCCCTAG";
        
        System.out.println("Gene is "+findSimpleGene(test1));
        System.out.println("Gene is "+findSimpleGene(test2));
        System.out.println("Gene is "+findSimpleGene(test3));
        System.out.println("Gene is "+findSimpleGene(test4));
        System.out.println("Gene is "+findSimpleGene(test5));
        
    }
    
    
}
