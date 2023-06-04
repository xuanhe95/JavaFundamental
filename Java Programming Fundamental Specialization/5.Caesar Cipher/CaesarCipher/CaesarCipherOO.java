
/**
 * 在这里给出对类 CaesarCipherPrivate 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class CaesarCipherOO {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
   
    public CaesarCipherOO(int key){
        alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet=alphabet.substring(key)+alphabet.substring(0,key);
        mainKey=key;
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        
        for(int i=0;i<encrypted.length();i++){
            char curChar=encrypted.charAt(i);
            boolean lower=Character.isLowerCase(curChar); 
            int idx=alphabet.indexOf(Character.toUpperCase(curChar));
            if(idx!=-1){
                if(lower){
                    encrypted.setCharAt(i,Character.toLowerCase(shiftedAlphabet.charAt(idx)));
                }
                else{
                    encrypted.setCharAt(i,shiftedAlphabet.charAt(idx));
                }
                
            }
        }

        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherOO cc = new CaesarCipherOO(26-mainKey);
        String message=cc.encrypt(input);
        return message;
    }
    
}
