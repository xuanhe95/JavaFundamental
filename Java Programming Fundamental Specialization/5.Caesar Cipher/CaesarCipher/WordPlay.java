
/**
 * 在这里给出对类 WordPlay 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class WordPlay {
    public boolean isVowel(Character ch){
        if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'){
            return true;
        }
        return false;
    }
    
    public String replaceVowels(String phrase,Character ch){
        StringBuilder sb=new StringBuilder(phrase);
        for(int i=0;i<phrase.length();i++){
            Character curChar=sb.charAt(i);
            if(isVowel(curChar)){
                sb.setCharAt(i,ch);
            }
        }
        return sb.toString();
    }
    
    public String emphasize(String phrase,Character ch){
        StringBuilder sb=new StringBuilder(phrase);
        for(int i=0;i<phrase.length();i++){
            Character curChar=sb.charAt(i);
            if(curChar.equals(ch)){
                sb.setCharAt(i,Character.toUpperCase(ch));
            }
        }
        return sb.toString();
    }
    
    public void test(){
        String phrase="Mary Bella Abracadabra";
        Character a=new Character('a');
        Character b=new Character('b');
        Character punc=new Character('*');
        System.out.println(replaceVowels(phrase,punc));
        System.out.println(emphasize(phrase,a));
    
    }

    
}
