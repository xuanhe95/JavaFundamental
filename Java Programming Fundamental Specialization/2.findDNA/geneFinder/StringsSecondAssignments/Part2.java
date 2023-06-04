
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part2 {
    public int howMany(String a,String b){
        int total=0;
        int cur=0;
        while(b.indexOf(a,cur)!=-1){
            //Point a
            cur=b.indexOf(a,cur);
            //Point a+length a
            cur=cur+a.length();
            
            
            total=total + 1;
        }
        
        return total;
        
        
    }
    
    public void testHowMany(){
        
        String test1="ATGAACGAATTGAATC";
        String test2="ATAAAA";
        String sub1="GAA";
        String sub2="AA";
        
        System.out.println(howMany(sub1,test1));
        System.out.println(howMany(sub2,test2));
    }
}
