
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part2 {
    public String findSimpleGene(String gene,String startCodon,String stopCodon){
        int start = gene.toUpperCase().indexOf("ATG");
        if (start == -1){
        return "";
        }
        int end = gene.toUpperCase().indexOf("TAG",start+3);
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
        String test6="atgcccaaatag";
        
        String stt="ATG";
        String stp="TAG";
        
        System.out.println("Gene is "+findSimpleGene(test1,stt,stp));
        System.out.println("Gene is "+findSimpleGene(test2,stt,stp));
        System.out.println("Gene is "+findSimpleGene(test3,stt,stp));
        System.out.println("Gene is "+findSimpleGene(test4,stt,stp));
        System.out.println("Gene is "+findSimpleGene(test5,stt,stp));
        System.out.println("Gene is "+findSimpleGene(test6,stt,stp));
        
    }
}
