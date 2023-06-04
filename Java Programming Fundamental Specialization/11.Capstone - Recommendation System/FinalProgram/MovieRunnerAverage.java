
/**
 * 在这里给出对类 MovieRunnerAverage 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class MovieRunnerAverage {

    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings();
        int movieSize = sr.getMovieSize();
        int raterSize = sr.getRaterSize();
        System.out.println("Read data for " + movieSize + " movies.");
        System.out.println("Read data for " + raterSize + " raters.");
        
        ArrayList<Rating> myRatings = sr.getAverageRatings(12);
        for(Rating rt : myRatings){
            String id = rt.getItem();
            String title = sr.getTitle(id);
            String formatRatings = new DecimalFormat("0.00000").format(rt.getValue());
            System.out.println(formatRatings + "  " + title);
        }
        System.out.println("Ratings size is: " + myRatings.size());
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings();
        String id = sr.getID("The Maze Runner");
        
        System.out.println(sr.getAverageByID(id,1));
    }
    
    
}
