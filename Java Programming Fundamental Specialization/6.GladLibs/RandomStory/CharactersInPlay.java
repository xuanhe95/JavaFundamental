
/**
 * 在这里给出对类 CharactersInPlay 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String> name;
    private ArrayList<Integer> counts;
    public CharactersInPlay(){
        name=new ArrayList<String>();
        counts=new ArrayList<Integer>();
    }
    private void update(String person){
        int index=0;
        person=person.toUpperCase();
        if(!name.contains(person)){
            name.add(person);
            counts.add(1);
        }
        else{
            index=name.indexOf(person);
            counts.set(index,counts.get(index)+1);
        }
    }
    private void findAllCharacters(){
        name.clear();
        counts.clear();
        FileResource fr=new FileResource();
        for(String line:fr.lines()){
            if(line.indexOf(".")!=-1){
                int index=line.indexOf(".");
                String person=line.substring(0,index);
                update(person);
            }
        }
    
    }
    public void tester(){
        findAllCharacters();
        charactersWithNumParts(10,100);
    }
    public void charactersWithNumParts(int num1,int num2){
        int total=0;
        for(String character:name){
            total=counts.get(name.indexOf(character));
            if(total>=num1 && total<=num2){
                System.out.println(character.trim()+"\t"+counts.get(name.indexOf(character)));
            }
        }
    }
    
}
