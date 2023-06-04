
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("UniqueIPData/short-test_log");
        la.printAll();
    }
    
    public void testUniqueIPs(){
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("UniqueIPData/short-test_log");
        int uniqueIPs=la.countUniqueIPs();
        System.out.println(uniqueIPs);
    }
    
    public void testUniqueIUnPVisitsOnDay(){
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("CountingVisitsData/weblog1_log");
        int size1=la.uniqueIPVisitsOnDay("Mar 17");
        int size2=la.uniqueIPVisitsOnDay("Sep 30");
        System.out.println(size1+"\t"+size2);
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("CountingVisitsData/weblog1_log");
        la.printAllHigherThanNum(400);

    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("CountingVisitsData/weblog1_log");
        int count1=la.countUniqueIPsInRange(200,299);
        int count2=la.countUniqueIPsInRange(300,399);
        System.out.println(count1+"\t"+count2);
    }
    
    public void testAll(){
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("CountingVisitsData/weblog3-short_log");
        HashMap<String,Integer> map=la.countVisitsPerIP();
        
        int maxNumberVisitsByIp=la.mostNumberVisitsByIP(map);
        System.out.println("Test mostNumberVisitsByIp: "+maxNumberVisitsByIp);
        
        ArrayList<String> mostVisits=la.iPsMostVisits(map);
        System.out.println("Test iPsMostVisits: "+mostVisits);
        
        HashMap<String,ArrayList<String>> ipMap=la.iPsForDays();
        String date=la.dayWithMostIPVisits(ipMap);
        System.out.println("Test dayWithMostIPVisits: "+date);
        
        ArrayList<String> ipList=la.iPsWithMostVisitsOnDay(ipMap,"Sep 30");
        System.out.println("Test iPsWthMostVisitsOnDay: "+ipList);
        
    }
    
    public void testQuiz(){
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("CountingVisitsData/weblog1_log");
        HashMap<String,Integer> map=la.countVisitsPerIP();
        
        int maxNumberVisitsByIp=la.mostNumberVisitsByIP(map);
        System.out.println("Test mostNumberVisitsByIp: "+maxNumberVisitsByIp);
        
        ArrayList<String> mostVisits=la.iPsMostVisits(map);
        System.out.println("Test iPsMostVisits: "+mostVisits);
        
        HashMap<String,ArrayList<String>> ipMap=la.iPsForDays();
        String date=la.dayWithMostIPVisits(ipMap);
        System.out.println("Test dayWithMostIPVisits: "+date);
        
        ArrayList<String> ipList=la.iPsWithMostVisitsOnDay(ipMap,"Mar 17");
        System.out.println("Test iPsWthMostVisitsOnDay: "+ipList);
        
    }
    
    public void testQuiz2(){
        LogAnalyzer la=new LogAnalyzer();
        la.readFile("CountingVisitsData/weblog2_log");
        
        int count=la.countUniqueIPs();
        System.out.println("Test countUniqueIPs: "+count);
        
        int count2=la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println("Test uniqueIPVisitsOnDay: "+count2);
        
        int count3=la.countUniqueIPsInRange(200,299);
        System.out.println("Test countUniqueIPsInRange : "+count3);
        
        HashMap<String,Integer> map=la.countVisitsPerIP();
        int count4=la.mostNumberVisitsByIP(map);
        System.out.println("Test mostNumberVisitsByIP : "+count4);
        
        ArrayList<String> mostVisits=la.iPsMostVisits(map);
        System.out.println("Test iPsMostVisits: "+mostVisits);
        
        HashMap<String,ArrayList<String>> ipMap=la.iPsForDays();
        String date=la.dayWithMostIPVisits(ipMap);
        System.out.println("Test dayWithMostIPVisits: "+date);
        
        ArrayList<String> ipList=la.iPsWithMostVisitsOnDay(ipMap,"Sep 30");
        System.out.println("Test iPsWthMostVisitsOnDay: "+ipList);
        
    }
}
