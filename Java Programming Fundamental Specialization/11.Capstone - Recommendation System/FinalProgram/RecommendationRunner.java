
/**
 * 在这里给出对类 RecommendationRunner 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
import java.text.DecimalFormat;


public class RecommendationRunner implements Recommender{
    RaterDatabase raterDatabase;
    MovieDatabase database;
    FourthRatings fr;
    
    public RecommendationRunner(){
        database = new MovieDatabase();
        database.initialize("ratedmoviesfull.csv");
        raterDatabase = new RaterDatabase();
        raterDatabase.initialize("ratings.csv");
        fr = new FourthRatings("ratings.csv");
        
        System.out.println("Read data for " + raterDatabase.size() + " raters.");
        System.out.println("Read data for " + database.size() + " movies.");
    }
    
    public ArrayList<String> getItemsToRate(){
        ArrayList<String> items = new ArrayList<String>();
        Filter filter = new GenreFilter("Action");
        ArrayList<String> movies = MovieDatabase.filterBy(filter);
        for(int i=0;i<10;i++){
            items.add(movies.get(i));
        }
        return items;
    }
    public void printRecommendationsFor(String webRaterID){
        Filter filter = new GenreFilter("Action");
        ArrayList<Rating> myRatings = fr.getSimilarRatingsByFilter(webRaterID,20,5,filter);
        System.out.println("<h1>My Capstone Project</h1>");
        System.out.println("<h2>Found " + myRatings.size() + " movies.</h2>");
        System.out.println("<h2>Filtered by \"Action\" Genre.</h2>");
        myRatings.sort(Comparator.reverseOrder());
        int num = 0;
        
        System.out.println(
        "<style>"+
        "table{front-family: arial, sans-serif;border-collapse:collapse;width:100%}"+
        "td,th{border:1px solid black;text-align:left;padding:8px;}"+
        "</style>"
        
        );
        
        System.out.println(
        "<table><tr><th><b>Poster</b></th><th><b>Title</b></th><th><b>Genre</b></th><th><b>Year</b></th><th><b>Minutes</b></th><th><b>Country</b></th></tr>"
        );
        
        for(Rating rt : myRatings){
            if(num==10){
                break;
            }
            num+=1;
            String id = rt.getItem();
            String title = database.getTitle(id);
            String formatRatings = new DecimalFormat("0.00").format(rt.getValue());

            System.out.println("<tr>"+
            "<td><img src=\""+database.getPoster(id)+"\" width=\"90\" height=\"120\"></td>"+
            "<td><b>"+title+"</b></td>"+
            "<td>"+database.getGenres(id)+"</td>"+
            "<td>"+database.getYear(id)+"</td>"+
            "<td>"+database.getMinutes(id)+"</td>"+
            "<td>"+database.getCountry(id)+"</td>"+
            
            "</tr>");
        }
        
        System.out.println(
        "</table>"
        );
 
    }
    
}
