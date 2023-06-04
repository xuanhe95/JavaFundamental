
/**
 * 在这里给出对类 Weather 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class Weather {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord minSoFar=null;
        for (CSVRecord curRow:parser){
            minSoFar=minOfTwo(minSoFar,curRow); 
        }
        return minSoFar;
    }
    
    public String fileWithColdestTemperature(){
        CSVRecord minSoFar=null;
        String fileName="";  
        DirectoryResource dr=new DirectoryResource();
        
        for (File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVParser parse=fr.getCSVParser();
            CSVRecord curRow=coldestHourInFile(parse);
            CSVRecord minOfTwo=minOfTwo(minSoFar,curRow);
            if(minSoFar!=minOfTwo){
                minSoFar=minOfTwo;
                fileName=f.getPath();
            }
        }

        return fileName;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord minHumidityRecord=null;
        
        for(CSVRecord curRow:parser){
            
            String curHumidityString=curRow.get("Humidity");
            if (curHumidityString.equals("N/A")){
                continue;
            }
            
            if(minHumidityRecord==null){
                minHumidityRecord=curRow;
            }
            else{
                double minHumidity=Double.parseDouble(minHumidityRecord.get("Humidity"));
                double curHumidity=Double.parseDouble(curRow.get("Humidity"));
                if(minHumidity>curHumidity){
                    minHumidityRecord=curRow;
                }
                
            }
        
        } 
        return minHumidityRecord;
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord minSoFar=null;
        DirectoryResource dr=new DirectoryResource();
        for (File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVParser parse=fr.getCSVParser();
            CSVRecord curRow=lowestHumidityInFile(parse);
            minSoFar=minOfTwoHumidity(minSoFar,curRow);

        }
        return minSoFar;
    }
    
    public CSVRecord minOfTwo(CSVRecord minSoFar,CSVRecord curRow){
        if (minSoFar==null){
                minSoFar=curRow;
            }  
        else{
            double curTemp = Double.parseDouble(curRow.get("TemperatureF"));
            double minTemp = Double.parseDouble(minSoFar.get("TemperatureF"));
                
                if (curTemp<minTemp && curTemp!=-9999){
                    minSoFar=curRow;
                }  
            }
            
        return minSoFar;
    }
    
    public CSVRecord minOfTwoHumidity(CSVRecord minSoFar,CSVRecord curRow){
        if (minSoFar==null){
                minSoFar=curRow;
            }        
        else{
            double curHumidity = Double.parseDouble(curRow.get("Humidity"));
            double minHumidity = Double.parseDouble(minSoFar.get("Humidity"));
                
                if (curHumidity<minHumidity){
                    minSoFar=curRow;
                }     
            }
            
        return minSoFar;
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double sum=0;
        double sumTemp=0;
        for(CSVRecord curRow:parser){
            double temp = Double.parseDouble(curRow.get("TemperatureF"));
            sumTemp+=temp;
            sum++;
        }
        double averageTemp=sumTemp/sum;
        return averageTemp;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
        
        double sum=0;
        double sumTemp=0;
        for (CSVRecord curRow:parser){
            Double humidity=Double.parseDouble(curRow.get("Humidity"));
            if (humidity>value){
                 double temp = Double.parseDouble(curRow.get("TemperatureF"));
                 sumTemp+=temp;
                 sum++;
                }
            }
        if(sumTemp==0){return 0;}    
            
        double averageTemp=sumTemp/sum;
        return averageTemp;
        }
    
    
    public void testAverageTemperatureInFile(){
        
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        
        System.out.println("Average temerature in file is " + averageTemperatureInFile(parser));
    
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        double averageTempWithHighHumidity=averageTemperatureWithHighHumidityInFile(parser,80);
        if(averageTempWithHighHumidity==0){
            System.out.println("No temperature with that humidity");
        }
        else{
        System.out.println("Average temperature when high humidity is " + averageTempWithHighHumidity);
        }
    }
    
    public void testColderHourInFile(){
        
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        
        System.out.println(coldestHourInFile(parser).get("TemperatureF"));
    }
    
     public void testFileWithColdestTemperature(){
        String fileName=fileWithColdestTemperature();
        //Change directory's name
        int idx=fileName.lastIndexOf("\\");
        String fn=fileName.substring(idx+1);
        System.out.println("Coldest day was in file \""+fn+"\"");
        
        FileResource fr=new FileResource(fileName);
        CSVParser parser=fr.getCSVParser();  
        System.out.println("Coldest temperature is " + coldestHourInFile(parser).get("TemperatureF"));
    
    }
    
    public void testColdestTemperatureInFile(){
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        
        System.out.println("Coldest hour in file is " + coldestHourInFile(parser).get("TemperatureF"));
        
    }
    
    public void testLowestHumidityInFile(){
        
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);

        String time=csv.get("DateUTC");
        System.out.println("Lowest Humidity was "+ csv.get("Humidity")+" at " + time);
    
    }
    
    public void testLowestHumidityInManyFeils(){
        CSVRecord csv = lowestHumidityInManyFiles();
        String time=csv.get("DateUTC");
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+ " at "+ time);
    }
    
    
}
