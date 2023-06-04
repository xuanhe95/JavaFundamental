
/**
 * 在这里给出对类 Part3 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part3 {
    public boolean twoOccurrences(String a,String b){
        int accum=0;
        int total=b.length();
        int idx;
        
        while (b.indexOf(a)!=-1){
            idx=b.indexOf(a);
            b=b.substring(idx+a.length());
            accum=accum+1;
        }
        
        
        if (accum>1){
            
        return true;
        }
        
        
        else{
        return false;
        }
    }
    
    public String lastPart(String a,String b){
        
        int idx=b.indexOf(a);
        if (idx!=-1){
            b=b.substring(idx+a.length());
            return b;
        }
        
        else{
            return b;
        }
        
    }
    
     public void testTwoOccurrences(){
        String a1="a",b1="banana";
        String a2="zoo",b2="forest";
        String a3="zed",b3="zedzedzed";
        
        System.out.println(twoOccurrences(a1,b1));
        System.out.println(twoOccurrences(a2,b2));
        System.out.println(twoOccurrences(a3,b3));
        
    }
    
    public void testLastPart(){
        String a1="a",b1="banana";
        String a2="zoo",b2="forest";
        String a3="zed",b3="zedzedzed";
        
        System.out.println(lastPart(a1,b1));
        System.out.println(lastPart(a2,b2));
        System.out.println(lastPart(a3,b3));
        
    }
}
