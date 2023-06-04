
/**
 * 在这里给出对类 ThirdRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class ThirdRatings {

    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String id : movies){
            double averageRatings = getAverageByID(id,minimumRaters);
            if(averageRatings == 0.0){
                continue;
            }
            Rating rt = new Rating(id,averageRatings);
            myRatings.add(rt);
        }
        return myRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimumRaters,Filter filterCriteria){
        ArrayList<Rating> myRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String id : movies){
            double averageRatings = getAverageByID(id,minimumRaters);
            if(averageRatings == 0.0){
                continue;
            }
            Rating rt = new Rating(id,averageRatings);
            myRatings.add(rt);
        }
        return myRatings;
    }
    
    
    
}
