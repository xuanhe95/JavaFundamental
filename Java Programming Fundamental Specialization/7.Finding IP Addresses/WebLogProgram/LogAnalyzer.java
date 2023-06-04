
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records=new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr=new FileResource(filename);
         for(String line:fr.lines()){
             LogEntry le=WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList ipList=new ArrayList();
         for(LogEntry record:records){
             String curIp=record.getIpAddress();
             if(!ipList.contains(curIp)){
                ipList.add(curIp);
             }
         }
         return ipList.size();
     }
     
     public void printAllHigherThanNum(int num){
         for(LogEntry record:records){
             if(record.getStatusCode()>num){
                 System.out.println(record);
             }
         }
     }
     
     public int uniqueIPVisitsOnDay(String someday){
        ArrayList ipList=new ArrayList();
        String time;
        for(LogEntry record:records){
            String curIp=record.getIpAddress();
            time=record.getAccessTime().toString();
            if(time.contains(someday) && !ipList.contains(curIp)){
                ipList.add(curIp);
            }
        }
        return ipList.size();
     }
     
     public int countUniqueIPsInRange(int low,int high){
        ArrayList ipList=new ArrayList();
        for(LogEntry record:records){
             String curIp=record.getIpAddress();
             if(record.getStatusCode()>=low && record.getStatusCode()<=high && !ipList.contains(curIp)){
                 ipList.add(curIp);
             }
         }
        return ipList.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> map=new HashMap<String,Integer>();
         for(LogEntry record:records){
             String curIp=record.getIpAddress();
             if(!map.containsKey(curIp)){
                map.put(curIp,1);
             }
             else{
                map.put(curIp,map.get(curIp)+1);
             }
         }
         return map;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> map){
         int maxNumber=-1;
         for(String ip:map.keySet()){
             if(map.get(ip)>maxNumber){
                 maxNumber=map.get(ip);
             }
         }
         return maxNumber;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> map){
         ArrayList<String> maxIpList=new ArrayList<String>();
         int maxNum=0;
         for(String ip:map.keySet()){
            if(map.get(ip)>maxNum){
                maxNum=map.get(ip);
            }
         }
         for(String ip:map.keySet()){
            if(map.get(ip).equals(maxNum)){
                maxIpList.add(ip);
            }
         }
         return maxIpList;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> ipMap=new HashMap<String,ArrayList<String>>();
        String date;
        for(LogEntry record:records){
            String curIp=record.getIpAddress();
            date=record.getAccessTime().toString().substring(4,10);
            if(!ipMap.containsKey(date)){
                ArrayList<String> ipList=new ArrayList<String>();
                ipList.add(curIp);
                ipMap.put(date,ipList);
            }
            else{
                ArrayList<String> ipList=ipMap.get(date);
                ipList.add(curIp);
                ipMap.put(date,ipList);
            }
        }
        return ipMap;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> ipMap){
         int mostIpVisits=0;
         String mostDay=null;
         for(String date:ipMap.keySet()){
            int curDayVisits=ipMap.get(date).size();
                if (curDayVisits>mostIpVisits){
                    mostIpVisits=curDayVisits;
                    mostDay=date;
                }
            }
         return mostDay;
        }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> ipMap,String date){
         ArrayList<String> ipList=ipMap.get(date);
         HashMap<String,Integer> map=new HashMap<String,Integer>();
         for(String ip:ipList){
             if(!map.containsKey(ip)){
                map.put(ip,1);
             }
             else{
                map.put(ip,map.get(ip)+1);
             }
         }
         return iPsMostVisits(map);
     }
        
}
