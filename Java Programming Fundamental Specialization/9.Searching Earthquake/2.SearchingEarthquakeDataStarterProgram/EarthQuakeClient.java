import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData){
            if(qe.getMagnitude()>magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData){
            if(from.distanceTo(qe.getLocation())<distMax){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,double minDepth,double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData){
            if(qe.getDepth()>minDepth && qe.getDepth()<maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,String where,String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData){
            String curInfo=qe.getInfo();
            String start=curInfo.substring(0,phrase.length());
            String end=curInfo.substring(curInfo.length()-phrase.length(),curInfo.length());
            if(where.equals("start") && start.equals(phrase)){
                answer.add(qe);
            }
            else if(where.equals("end") && end.equals(phrase)){
                answer.add(qe);
            }
            else if(where.equals("any") && curInfo.indexOf(phrase)!=-1){
                answer.add(qe);
            }
            
        }
        return answer;
    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes.");
        
        ArrayList<QuakeEntry> filteredList=filterByMagnitude(list,5.0);
        for(QuakeEntry qe:filteredList){
            System.out.println(qe);
        }
        System.out.println("Found "+filteredList.size()+" quakes that match that criteria.");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes.");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        
        ArrayList<QuakeEntry> filteredList=filterByDistanceFrom(list,1000*1000,city);
        for(QuakeEntry qe:filteredList){
            double distanceInMeters=city.distanceTo(qe.getLocation());
            System.out.println(distanceInMeters/1000+ " " +qe.getInfo());
        }
        System.out.println("Found "+filteredList.size()+" quakes that match that criteria.");
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes.");
        
        ArrayList<QuakeEntry> filteredList=filterByDepth(list,-10000.0,-8000.0);
        for(QuakeEntry qe:filteredList){
            System.out.println(qe);
        }
        System.out.println("Found "+filteredList.size()+" quakes that match that criteria.");
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes.");
        
        //String where="end";
        //String phrase="California";
        String where="any";
        String phrase="Creek";
        //String where="start";
        //String phrase="Explosion";

        ArrayList<QuakeEntry> filteredList=filterByPhrase(list,where,phrase);
        for(QuakeEntry qe:filteredList){
            System.out.println(qe);
        }
        System.out.println("Found " + filteredList.size() + " quakes that match " + phrase + " at " + where + ".");
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
