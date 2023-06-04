
/**
 * 在这里给出对类 babyNames 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class babyNames {
    public void totalBirths(){
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser(false);
        int girlNamesNumber=0;
        int boyNamesNumber=0;
        int total=0;
        for(CSVRecord curRow:parser){
            if(curRow.get(1).equals("F")){
                girlNamesNumber++;
            }
            else{
                boyNamesNumber++;
            }

        }
        System.out.println("Girl names number: "+girlNamesNumber);
            System.out.println("Boy names number: "+boyNamesNumber);
            total=girlNamesNumber+boyNamesNumber;
            System.out.println("Total names number: "+total);
    }
    
    public int getRank(int year,String name,String gender){
        String fileName="yob"+year+".csv";
        FileResource fr=new FileResource("us_babynames/us_babynames_by_year/"+fileName);
        CSVParser parser=fr.getCSVParser(false);
        int sum=0;
        for (CSVRecord curRow:parser){
            if(gender.equals(curRow.get(1))){
                System.out.println(curRow.get(0));
                sum++;
                if(curRow.get(0).equals(name)){
                
                return sum;
                }            
            }
        }
        return -1;
    }
    
    public String getName(int year,int rank,String gender){
        String fileName="yob"+year+".csv";
        FileResource fr=new FileResource("us_babynames/us_babynames_by_year/"+fileName);
        CSVParser parser=fr.getCSVParser(false);
        int sumRank=0;
        for (CSVRecord curRow:parser){
            if(gender.equals(curRow.get(1))){
                sumRank++;
                if (sumRank==rank){
                    return curRow.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name,int year,int newYear,String gender){
        int curNameRank=getRank(year,name,gender);
        String nameInYear=getName(newYear,curNameRank,gender);
        String personalPronoun="";
        if(gender.equals("F")){
            personalPronoun="she";
        }
        else{personalPronoun="he";};
        System.out.println(name+" born in "+newYear+" would be "+ nameInYear +" if "+ personalPronoun + " was born in " + newYear + ".");;
    
    }
    
    public int getRankWithoutYear(CSVParser parser,String name,String gender){

        int sum=0;
        for (CSVRecord curRow:parser){
            if(gender.equals(curRow.get(1))){
                sum++;
                if(curRow.get(0).equals(name)){
                return sum;
                }            
            }
        }
        return -1;
    }
    
    public int yearOfHighestRank(String name,String gender){
        int highestRank=0;
        int year=0;
        String yearString="0";
        DirectoryResource dr=new DirectoryResource();
        for (File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVParser parser=fr.getCSVParser(false);
            int curRank=getRankWithoutYear(parser,name,gender);
            if (curRank==-1){
            continue;}
            if (highestRank==0){
                highestRank=curRank;
            }
            else{
                if(highestRank>curRank){
                    highestRank=curRank;
                    yearString=f.getName().substring(3,7);
                }
            }
        }
        
        year = Integer.parseInt(yearString);
        return year;
    }

    public double getAverageRank(String name,String gender){
        DirectoryResource dr=new DirectoryResource();
        double averageRank=-1.0;
        double totalRank=0;
        double totalFiles=0;
        for (File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVParser parser=fr.getCSVParser(false);
            totalRank+=getRankWithoutYear(parser,name,gender);
            totalFiles+=1;
        }
        
        if (totalFiles==-1.0){return averageRank;}
        averageRank=totalRank/totalFiles;
        return averageRank;
    }
    
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        String fileName="yob"+year+".csv";
        FileResource fr=new FileResource("us_babynames/us_babynames_by_year/"+fileName);
        CSVParser parser=fr.getCSVParser(false);
        int higherNumber=0;
        int rank=getRank(year,name,gender);
        int curRank=1;

        for(CSVRecord curRow:parser){
            
            if(rank>curRank && curRow.get(1).equals(gender)){
                System.out.println(curRow.get(2));
                System.out.println(curRank);
                curRank++;
                System.out.println(curRank);
                System.out.println(curRow.get(2));
                higherNumber+=Integer.parseInt(curRow.get(2));
                System.out.println(higherNumber);
            }
        
        }
        return higherNumber;
    }
    
    public void testYearOfHighestRank(String name,String gender){
        String year=String.valueOf(yearOfHighestRank(name,gender));
        if (year.equals("0")){
            year="No Data";}

        System.out.println("Highest year of rank is "+ year + ".");
    }
    
    public void testGetRank(int year,String name,String gender){
        System.out.println(getRank(year,name,gender));
    }
    
    public void testGetName(int year,int rank,String gender){
        System.out.println(getName(year,rank,gender));
    }
}
