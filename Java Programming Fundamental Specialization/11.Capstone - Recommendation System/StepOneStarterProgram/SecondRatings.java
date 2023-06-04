
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile,String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID(String id,int minimumRaters){
        int numOfRaters = 0;
        double totalRatings = 0.0;
        for(Rater rater : myRaters){
            ArrayList<String> movies = rater.getItemsRated(); 
            if(movies.contains(id)){
                numOfRaters+=1;
                totalRatings+=rater.getRating(id);
            }
        }

        if(numOfRaters >= minimumRaters){
            double averageRatings = totalRatings/numOfRaters;
            return averageRatings;
        }
        else{
            return 0.0;
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimumRaters){
        ArrayList<Rating> myRatings = new ArrayList<Rating>();
        for (Movie movie : myMovies){
            double averageRatings = getAverageByID(movie.getID(),minimumRaters);
            if(averageRatings == 0.0){
                continue;
            }
            Rating rt = new Rating(movie.getID(),averageRatings);
            myRatings.add(rt);
        }
        return myRatings;
    }
    
    public String getTitle(String id){
        for(Movie mv : myMovies){
            if(mv.getID().equals(id)){
                return mv.getTitle();
            }
        }
        return "The movie of " + id + " was not found!";
    }
    
    public String getID(String title){
        for(Movie mv : myMovies){
            if(mv.getTitle().equals(title)){
                return mv.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
    
   
    
    
    
}
