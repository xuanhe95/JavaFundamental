
/**
 * 在这里给出对类 FirstRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        String text = fr.asString();
        CSVParser parser = fr.getCSVParser();

        for(CSVRecord record:parser){
            String movieID = record.get("id");
            String movieTitle = record.get("title");
            String movieYear = record.get("year");
            String movieCountry = record.get("country");
            String movieGenre = record.get("genre");
            String movieDirector = record.get("director");
            int movieMinutes = Integer.parseInt(record.get("minutes"));
            String moviePoster = record.get("poster");
            Movie mv = new Movie(movieID,movieTitle,movieYear,movieGenre,movieDirector,movieCountry,moviePoster,movieMinutes);
            movies.add(mv);
        }
        
        return movies;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> raters = new ArrayList<Rater>();
        FileResource fr = new FileResource("data/"+filename);
        String text = fr.asString();
        CSVParser parser = fr.getCSVParser();
        
        HashMap<String,Integer> map = new HashMap();
        int indexOfRaters = 0;  //记录raters中的位置
        for(CSVRecord record:parser){
            String raterID = record.get("rater_id");
            String movieID = record.get("movie_id");
            double rateRating = Double.parseDouble(record.get("rating"));
            String rateTime = record.get("time");
            if(!map.containsKey(raterID)){  //map中没有当前raterID则创建新Rater。
                Rater rater = new EfficientRater(raterID);
                rater.addRating(movieID,rateRating);
                raters.add(rater);
                
                map.put(raterID,indexOfRaters); //将raterID以及对应在raters中的位置添加到map
                indexOfRaters += 1; //并更新raters中的目录
            }
            else{
                int indexOfCurRater = map.get(raterID); //map中含有当前raterID，获得其在raters中的目录
                Rater curRater = raters.get(indexOfCurRater);                
                curRater.addRating(movieID,rateRating); //取得并添加新rating到rater中
            }
        }
        
        return raters;
    }
    
    
    public void testLoadMovies(){
        //ArrayList<Movie> movies = loadMovies("ratedmovies_short.csv");
        ArrayList<Movie> movies = loadMovies("ratedmoviesfull.csv");
        String gener = "Comedy";
   
        int geners = 0;
        int minutes = 0;
        for(Movie movie:movies){
            if(movie.getGenres().contains(gener)){
                geners +=1;
            }
            if(movie.getMinutes()>150){
                minutes +=1;
            }
        }
        System.out.println("Selected by "  + gener + " geners: " + geners);
        System.out.println("Selected by minutes: " + minutes);
        
        HashMap<String,Integer> map = new HashMap();
        int maxNum = 0;
        for(Movie movie:movies){
            String director = movie.getDirector();
            if(!map.containsKey(director)){
                map.put(director,1);
                if(maxNum == 0){
                    maxNum = 1;
                }
            }
            else{
                int total = map.get(director);
                map.put(director,total+1);
                if(total+1 > maxNum){
                    maxNum = total +1;
                }
            }
        }
        System.out.println("Total number of max movies directed by one director: " + maxNum);
        for(String director:map.keySet()){
            int total = map.get(director);
            if(total == maxNum){
                System.out.println(director);
            }
        }
    }
    
    public void testLoadRaters(){
        //ArrayList<Rater> raters = loadRaters("ratings_short.csv");
        ArrayList<Rater> raters = loadRaters("ratings.csv");
        System.out.println("Total number of raters: " + raters.size());
       
        String raterNo = "193";
        int numOfID = 0;
        for(Rater rater : raters){
            if(rater.getID().equals(raterNo)){
                numOfID = rater.numRatings();
            }
        }
        System.out.println("Total ratings of No." + raterNo + " is: " + numOfID);

        int maxRating = 0;
        for(Rater rater : raters){
            if(rater.numRatings() > maxRating){
                maxRating = rater.numRatings();
            }
        }
        for(Rater rater : raters){
            if(rater.numRatings() == maxRating){
                System.out.println("Rater with maximum ratings is No." + rater.getID());
            }
        }
        System.out.println("Maximum number of ratings is: " + maxRating);
        
        String movieID = "1798709";
        int numOfRatings = 0;
        for(Rater rater : raters){
            ArrayList<String> movies = rater.getItemsRated(); 
            if(movies.contains(movieID)){
                numOfRatings+=1;
                System.out.println(rater.getID() +"'s rating on " + movieID + " is: " + rater.getRating(movieID));
            }
        }
        System.out.println("There are " + numOfRatings + " ratings on movie No." + movieID);
        
        ArrayList<String> allRatedMovies = new ArrayList<String>();
        for(Rater rater : raters){
            ArrayList<String> movies = rater.getItemsRated();
            for(String movie : movies){
                if(allRatedMovies.indexOf(movie) == -1){
                    allRatedMovies.add(movie);
                }
            }
        }
        
        System.out.println("All rated movies number is: " + allRatedMovies.size());
        
    }
}

