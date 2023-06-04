
/**
 * 在这里给出对类 FourthRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;

public class FourthRatings {
    
    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public FourthRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
    }

    public double getAverageByID(String id,int minimumRaters){
        int numOfRaters = 0;
        double totalRatings = 0.0;
        for(Rater rater : RaterDatabase.getRaters()){
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
    
    public double dotProduct(Rater me,Rater r){
        ArrayList<String> myRatedMovies = me.getItemsRated();   //获取我的电影
        double dotProduct = 0;
        for(String movieID : myRatedMovies){    //遍历电影计算点乘积
            double myRating = me.getRating(movieID) - 5;
            if(r.hasRating(movieID)){   //另一个rater也评价过这部电影，则计算二者点乘
                double rRating = r.getRating(movieID) - 5;
                dotProduct += myRating * rRating;
            }
        }
        return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> similarities = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        raters.remove(me);  //比较时排除自己！！！
        for(Rater rater : raters){
            double product = dotProduct(me,rater);
            if(product > 0){
                Rating similarRating = new Rating(rater.getID(),product);    //评分 评分者及相似度
                similarities.add(similarRating);
            }
        }
        similarities.sort(Comparator.reverseOrder());
        return similarities;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id,int numSimilarRaters,int minimalRaters){
        return getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,new TrueFilter());
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters,int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> weightedRatings = new ArrayList<Rating>();    //加权评分
        ArrayList<Rating> similarRatings = getSimilarities(id); //根据Rater id获得相似度评分列表
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);  //获得movies的id列表
        
        int similarRatingsSize = similarRatings.size();
        if(similarRatingsSize < numSimilarRaters){  //防止Rater id评分列表长度不足时，后面的访问越界
            numSimilarRaters = similarRatingsSize;
        }
        for(String movieID : movies){
            double weightedRating = 0.0;
            double numOfRaters = 0.0;
            for(int i=0;i<numSimilarRaters;i++){   //只取前numSimilarRaters的评分
                double similarRating = similarRatings.get(i).getValue();    //获得rater id的相似度评分
                String raterID = similarRatings.get(i).getItem();
                
                double raterRating = RaterDatabase.getRater(raterID).getRating(movieID);
                if(raterRating > 0){    //rater未评价的时候返回-1，排除
                    weightedRating += similarRating * raterRating;    //计算加权评分
                    numOfRaters+=1; //计算加权评分的总数
                }
            }
            weightedRating /= numOfRaters;
            if(numOfRaters >= minimalRaters){
                Rating weighted = new Rating(movieID,weightedRating);
                weightedRatings.add(weighted);
            }
        }
        return weightedRatings;
    }
    
}
