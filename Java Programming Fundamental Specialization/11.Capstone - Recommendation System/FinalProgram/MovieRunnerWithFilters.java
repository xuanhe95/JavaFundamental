
/**
 * 在这里给出对类 MovieRunnerWithFilters 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
import java.text.DecimalFormat;

public class MovieRunnerWithFilters {
    MovieDatabase database;
    ThirdRatings tr;
    int raterSize;
    public MovieRunnerWithFilters(){
        database = new MovieDatabase();
        database.initialize("ratedmoviesfull.csv");
        tr = new ThirdRatings("ratings.csv");
        raterSize = tr.getRaterSize();
    }
    

    public void printAverageRatings(){
        System.out.println("Read data for " + raterSize + " raters.");
        System.out.println("Read data for " + database.size() + " movies.");
        
        ArrayList<Rating> myRatings = tr.getAverageRatings(35);
        System.out.println("Found " + myRatings.size() + " movies.");
        Collections.sort(myRatings);
        for(Rating rt : myRatings){
            String id = rt.getItem();
            String title = database.getTitle(id);
            String formatRatings = new DecimalFormat("0.0000").format(rt.getValue());
            System.out.println(formatRatings + "  " + title);
        }
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings();
        String id = sr.getID("The Maze Runner");
        
        System.out.println(sr.getAverageByID(id,1));
    }
    
    public void printAverageRatingsByYear(){
        System.out.println("Read data for " + raterSize + " raters.");
        System.out.println("Read data for " + database.size() + " movies.");
        
        Filter filter = new YearAfterFilter(2000);
        ArrayList<Rating> myRatings = tr.getAverageRatingsByFilter(20,filter);

        System.out.println("Found " + myRatings.size() + " movies.");
        Collections.sort(myRatings);
        for(Rating rt : myRatings){
            String id = rt.getItem();
            String title = database.getTitle(id);
            String formatRatings = new DecimalFormat("0.0000").format(rt.getValue());
            System.out.println(formatRatings + " " + database.getYear(id) + " " + title);
        }
    }
    
    public void printAverageRatingsByGenre(){
        System.out.println("Read data for " + raterSize + " raters.");
        System.out.println("Read data for " + database.size() + " movies.");
        
        Filter filter = new GenreFilter("Comedy");
        ArrayList<Rating> myRatings = tr.getAverageRatingsByFilter(20,filter);
        
        System.out.println("Found " + myRatings.size() + " movies.");
        Collections.sort(myRatings);
        for(Rating rt : myRatings){
            String id = rt.getItem();
            String title = database.getTitle(id);
            String formatRatings = new DecimalFormat("0.0000").format(rt.getValue());
            System.out.println(formatRatings + "  " + title);
            System.out.println("    " + database.getGenres(id));
        }
    }
    
    public void printAverageRatingsByMinutes(){
        System.out.println("Read data for " + raterSize + " raters.");
        System.out.println("Read data for " + database.size() + " movies.");
        
        Filter filter = new MinutesFilter(105,135);
        ArrayList<Rating> myRatings = tr.getAverageRatingsByFilter(5,filter);

        System.out.println("Found " + myRatings.size() + " movies.");
        Collections.sort(myRatings);
        for(Rating rt : myRatings){
            String id = rt.getItem();
            String title = database.getTitle(id);
            String formatRatings = new DecimalFormat("0.0000").format(rt.getValue());
            int time = database.getMinutes(id);
            System.out.println(formatRatings + " Time: " + time + " " + title);
        }
    }
    
    public void printAverageRatingsByDirector(){
        System.out.println("Read data for " + raterSize + " raters.");
        System.out.println("Read data for " + database.size() + " movies.");
        
        Filter filter = new DirectorFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> myRatings = tr.getAverageRatingsByFilter(4,filter);

        System.out.println("Found " + myRatings.size() + " movies.");
        Collections.sort(myRatings);
        for(Rating rt : myRatings){
            String id = rt.getItem();
            String title = database.getTitle(id);
            String formatRatings = new DecimalFormat("0.0000").format(rt.getValue());
            System.out.println(formatRatings + "    " + title);
            System.out.println("    " + database.getDirector(id));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        System.out.println("Read data for " + raterSize + " raters.");
        System.out.println("Read data for " + database.size() + " movies.");
        
        AllFilters filters = new AllFilters();
        Filter filter1 = new YearAfterFilter(1990);
        Filter filter2 = new GenreFilter("Drama");
 
        filters.addFilter(filter1);
        filters.addFilter(filter2);
        ArrayList<Rating> myRatings = tr.getAverageRatingsByFilter(8,filters);

        System.out.println("Found " + myRatings.size() + " movies.");
        Collections.sort(myRatings);
        for(Rating rt : myRatings){
            String id = rt.getItem();
            String title = database.getTitle(id);
            String formatRatings = new DecimalFormat("0.0000").format(rt.getValue());
            System.out.println(formatRatings + " " + database.getYear(id) + " " + title);
            System.out.println("    " + database.getGenres(id));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        System.out.println("Read data for " + raterSize + " raters.");
        System.out.println("Read data for " + database.size() + " movies.");
        
        AllFilters filters = new AllFilters();
        Filter filter1 = new MinutesFilter(90,180);
        Filter filter2 = new DirectorFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
 
        filters.addFilter(filter1);
        filters.addFilter(filter2);
        ArrayList<Rating> myRatings = tr.getAverageRatingsByFilter(3,filters);
        

        System.out.println("Found " + myRatings.size() + " movies.");
        Collections.sort(myRatings);
        for(Rating rt : myRatings){
            String id = rt.getItem();
            String title = database.getTitle(id);
            String formatRatings = new DecimalFormat("0.0000").format(rt.getValue());
            System.out.println(formatRatings + " " + database.getMinutes(id) + " " + title);
            System.out.println("    " + database.getDirector(id));
        }
    }
    
}
