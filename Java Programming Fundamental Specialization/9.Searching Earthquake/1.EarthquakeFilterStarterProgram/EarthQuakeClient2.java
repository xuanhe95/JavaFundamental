import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        Filter f1 = new MagnitudeFilter(4.0,5.0); 
        Filter f2 = new DepthFilter(-35000.0,-12000.0);
        ArrayList<QuakeEntry> magFiltered = filter(list,f1); 
        ArrayList<QuakeEntry> depthFiltered = filter(magFiltered,f2);
        for (QuakeEntry qe: depthFiltered) { 
            System.out.println(qe);
        } 
        //Location tokyo= new Location(35.42, 139.43);
        //Filter f3 = new DistanceFilter(tokyo,10000000.0);
        //Filter f4 = new PhraseFilter("end","Japan");
        //ArrayList<QuakeEntry> disFiltered = filter(list,f3);
        //ArrayList<QuakeEntry> phraseFiltered = filter(disFiltered,f4);
        //for (QuakeEntry qe: phraseFiltered) { 
        //    System.out.println(qe);
        //} 
        
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        Location tulsaOklahoma = new Location(36.1314,-95.9372);
        Location denverColorado = new Location(39.7392, -104.9903);
        Location billundDenmark = new Location(55.7308, 9.1153);
        
        MatchAllFilter maf=new MatchAllFilter();
        Filter f1=new MagnitudeFilter(0.0,5.0);
        Filter f2=new DepthFilter(-4000.0,-2000.0);
        Filter f3=new PhraseFilter("any","e");
        Filter f4=new DistanceFilter(billundDenmark,3000000);
        //maf.addFilter(f1);
        maf.addFilter(f2);
        //maf.addFilter(f3);
        //maf.addFilter(f4);
        ArrayList<QuakeEntry> filtered = filter(list,maf);
        for(QuakeEntry qe:filtered){
            System.out.println(qe);
        }
        System.out.println("Filters used are: "+maf.getName());
        System.out.println("Total number is: "+filtered.size());
    }
     
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
