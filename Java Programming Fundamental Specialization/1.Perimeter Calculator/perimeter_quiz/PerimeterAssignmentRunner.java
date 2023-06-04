import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int accum=0;
        for (Point point:s.getPoints()){
            accum +=1;
        }
        return accum;
    }

    public double getAverageLength(Shape s) {
        // Put code here
       
        double dis=0;
        
        Point prept = s.getLastPoint();
        
        for (Point curpt:s.getPoints()){
            dis=dis+curpt.distance(prept);
            prept=curpt;
        }
        double averlen=dis/getNumPoints(s);
        return averlen;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double lgside=0;
        Point prept=s.getLastPoint();
        for (Point curpt:s.getPoints()){
            double dis=curpt.distance(prept);
            prept=curpt;
            if(dis>lgside){
                lgside=dis;
            }    
        }
        return lgside;
    }
    
    public double getLargestX(Shape s) {
        // Put code here
        Point lstpt=s.getLastPoint();
        double lgx=lstpt.getX();
        for(Point point:s.getPoints()){
            double x = point.getX();
            if (x>lgx){
                lgx=x;
            }
        }
        return lgx;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double lgPeri=0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr=new FileResource(f);
            Shape s = new Shape(fr);
            double peri = getPerimeter(s);
            if(peri>lgPeri){
                lgPeri=peri;
            }
        }
        return lgPeri;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        DirectoryResource dr=new DirectoryResource();
        double maxPeri=0.0;
        for (File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            Shape s = new Shape(fr);
            double peri=getPerimeter(s);
            if(peri>maxPeri){
                maxPeri=peri;
                temp=f;
            }
            
        }
        return temp.getName();
    }
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int total=getNumPoints(s);
        double alength = getAverageLength(s);
        double lgside = getLargestSide(s);
        double lgx = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("number = " + total);
        System.out.println("average lenth = " + alength);
        System.out.println("longest side = " + lgside);
        System.out.println("largest X = " + lgx);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double lgPeri=getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter in Multiple Files is \"" + lgPeri +"\"");
        
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String s = getFileWithLargestPerimeter();
        System.out.println("File With Largest Perimeter is \""+ s + "\"");
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();       
    }
}
