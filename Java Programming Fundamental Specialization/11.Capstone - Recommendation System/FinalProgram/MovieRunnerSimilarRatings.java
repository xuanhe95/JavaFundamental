
/**
 * 在这里给出对类 MovieRunnerSimilarRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;
import java.text.DecimalFormat;

public class MovieRunnerSimilarRatings {
    RaterDatabase raterDatabase;
    MovieDatabase database;
    FourthRatings fr;

    
    public MovieRunnerSimilarRatings(){
        database = new MovieDatabase();
        database.initialize("ratedmoviesfull.csv");
        raterDatabase = new RaterDatabase();
        raterDatabase.initialize("ratings.csv");
        fr = new FourthRatings("ratings.csv");
        
        System.out.println("Read data for " + raterDatabase.size() + " raters.");
        System.out.println("Read data for " + database.size() + " movies.");
    }
    
    public void printAverageRatings(){ 
        ArrayList<Rating> myRatings = fr.getAverageRatings(35);
        print (myRatings);
    }
    
    public void printSimilarRatings(){
        ArrayList<Rating> myRatings = fr.getSimilarRatings("71",20,5);
        print(myRatings);
    }
    public void printSimilarRatingsByGenre(){
        Filter filter = new GenreFilter("Mystery");
        ArrayList<Rating> myRatings = fr.getSimilarRatingsByFilter("964",20,5,filter);
        print(myRatings);
    }
    public void printSimilarRatingsByDirector(){
        Filter filter = new DirectorFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> myRatings = fr.getSimilarRatingsByFilter("120",10,2,filter);
        print(myRatings);
    }
    public void printSimilarRatingsByGenreAndMinutes(){
        AllFilters filter = new AllFilters();
        Filter filter1 = new GenreFilter("Drama");
        Filter filter2 = new MinutesFilter(80,160);
        filter.addFilter(filter1);
        filter.addFilter(filter2);
        ArrayList<Rating> myRatings = fr.getSimilarRatingsByFilter("168",10,3,filter);
        print(myRatings);
    }
    public void printSimilarRatingsByYearAfterAndMinutes(){
        AllFilters filter = new AllFilters();
        Filter filter1 = new YearAfterFilter(1975);
        Filter filter2 = new MinutesFilter(70,200);
        filter.addFilter(filter1);
        filter.addFilter(filter2);
        ArrayList<Rating> myRatings = fr.getSimilarRatingsByFilter("314",10,5,filter);
        print(myRatings);
    }
    
    
    public void print(ArrayList<Rating> myRatings){
        System.out.println("Found " + myRatings.size() + " movies.");
        myRatings.sort(Comparator.reverseOrder());
        int num = 0;
        for(Rating rt : myRatings){
            if(num==5){
                break;
            }
            num+=1;
            String id = rt.getItem();
            String title = database.getTitle(id);
            String formatRatings = new DecimalFormat("0.00").format(rt.getValue());
            System.out.println(formatRatings + " " + database.getYear(id) + " " + title);
            System.out.println("    " + database.getGenres(id));
        }
    }
    
    
    
}
