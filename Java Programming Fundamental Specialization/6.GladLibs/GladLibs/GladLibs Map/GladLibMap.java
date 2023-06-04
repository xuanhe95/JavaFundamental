import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> map;
    
    private ArrayList<String> usedList;
    private ArrayList<String> usedLabels;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        map = new HashMap<String,ArrayList<String>>();
        usedLabels = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedList = new ArrayList<String>();
    }
    
    public GladLibMap(String source){
        map = new HashMap<String,ArrayList<String>>();
        usedLabels = new ArrayList<String>();
        initializeFromSource(source);
        myRandom = new Random();
        usedList = new ArrayList<String>();
        
    }
    
    private void initializeFromSource(String source) {
        String[] types = {"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
        for (String type:types){
            map.put(type,readIt(source+"/"+type+".txt"));
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (!usedLabels.contains(label)){
            usedLabels.add(label);
        }
        for (String type:map.keySet()){
            if("number".equals(label)){
                return ""+myRandom.nextInt(50)+5;
            }
            else if(type.equals(label)){
                return randomFrom(map.get(type));
            }
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (usedList.contains(sub)){
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedList.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        usedList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("");
        System.out.println("*************************************");
        System.out.println("There are "+totalWordsInMap()+" words can pick.");
        System.out.println("There are "+totalWordsConsidered()+" words considered.");
    }
    
    private int totalWordsInMap(){
        int total = 0;
        for(String type:map.keySet()){
            total+=map.get(type).size();
        }
        return total;
    }
    
    private int totalWordsConsidered(){
        int total = 0;
        for(String type:map.keySet()){
            if(usedLabels.contains(type)){
                total+=map.get(type).size();
            }
        }
        return total;
    }
    


}
