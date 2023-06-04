
/**
 * 在这里给出对类 Assignment 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Assignment {
    public void tester(){
        FileResource fr=new FileResource("export/exportdata.csv");
        CSVParser parser=fr.getCSVParser();
    }
    
    public String countryInfo(CSVParser parser,String country){
        String exports="";
        String value="";
        for (CSVRecord record:parser){
            String getCountry=record.get("Country");
            if (getCountry.equals(country)){
                exports=record.get("Exports");
                value=record.get("Value (dollars)");
                return country+": "+ exports + ": " + value;
            }
        }
        return "NOT FOUND";
    }
    
    public void testCountryInfo(){
        FileResource fr=new FileResource("exports/exportdata.csv");
        CSVParser parser=fr.getCSVParser();
        System.out.println(countryInfo(parser,"Germany"));
        System.out.println(countryInfo(parser,"Nauru"));
    }
    
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
        
        for(CSVRecord record:parser){
            String exports = record.get("Exports");
            if( exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        } 
    }
    
    public int numberOfExporters(CSVParser parser,String exportItem){
        int total=0;
        for(CSVRecord record:parser){ 
            String exports = record.get("Exports");
            if(exports.contains(exportItem)){
                total++;
            }

        }
        return total;
    }
    
    public void bigExporters(CSVParser parser,String amount){
        
        for(CSVRecord record:parser){   
            String value=record.get("Value (dollars)");
            if (value.length()>("$"+amount).length()){
                System.out.println(record.get("Country") + " " + value);
            }
        }

    }
    
    
    public void testListExportersTwoProducts(){
        FileResource fr=new FileResource("exports/exportdata.csv");
        CSVParser parser=fr.getCSVParser();
        listExportersTwoProducts(parser,"cotton","flowers");
    }
    
    public void testNumberOfExporters(String item){
        FileResource fr=new FileResource("exports/exportdata.csv");
        CSVParser parser=fr.getCSVParser();
        System.out.println("number of exporters: "+numberOfExporters(parser,item));
    }
    
    public void testBigExporters(){
        FileResource fr=new FileResource("exports/exportdata.csv");
        CSVParser parser=fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");    
    }
    
}
