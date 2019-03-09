import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
/**
 * Γράψτε μια περιγραφή της κλάσης BabyNames εδώ.
 * 
 * @author (Το όνομά σας) 
 * @version (Αριθμός έκδοσης ή ημερομηνία εδώ)
 */

public class BabyNames {
    public void totalBirths (FileResource fr){
        int totalBirths = 0;
        int girlNames = 0;
        int boyNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
          int numBorn = Integer.parseInt(rec.get(2));
          totalBirths += numBorn;
          if (rec.get(1).equals("M")){
              ++boyNames;
            }
          else {
              ++girlNames; 
            }
        }
        int totalNames = girlNames + boyNames;
        System.out.println("total births = "+totalBirths);
        System.out.println("girlNames = "+girlNames);
        System.out.println("boyNames = "+boyNames);
        System.out.println("total names = "+totalNames);
    }
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    public int getRank (int year, String name, String gender){
        int Rank = -1;
        int tempRank = 0;
        FileResource fr = new FileResource("us_babynames_by_year/yob"+ year +".csv");
        for (CSVRecord rec : fr.getCSVParser(false)){
           if (rec.get(1).equals(gender)){
               tempRank++;
            if (rec.get(0).equals(name)){
               Rank = tempRank;
               break;
            }
           }
           else {
               Rank = -1;
            }
       }
       return Rank;
    }
    public void testGetRank(){
       int Rank = getRank(1971,"Frank","M");
       System.out.println("Frank,M,Rank = "+Rank);
    }
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob"+ year +".csv");
        CSVParser parser = fr.getCSVParser(false);
        String name = null;
        for(CSVRecord rec : parser){
            String reqName = rec.get(0);
            int currRank = getRank(year, reqName, gender);
            if(rec.get(1).equals(gender)){
                if(currRank == rank){
                    name =rec.get(0);
                    break;
                }
            }
            else {
                name = "NO NAME";
            }
        }
        return name;
    }
    public void testgetName(){
       String name = getName(1982,450,"M");
       System.out.println("The male name with rank = 450 is "+name);
    }
    public void whatIsNameInYear(String name, int year, int newYear,
    String gender) {
       DirectoryResource dr = new DirectoryResource(); 
       for (File f : dr.selectedFiles()) {
        // Defining variables
        String newname = ""; // inital string
        int rank = getRank(year, name, gender);
        if (rank != -1) { // rank of baby exists
            newname = getName(newYear, rank, gender);
            if (newname.equals("NO NAME")) {
                newname = "no one";
            }
            if (gender.equals("M")) { // male baby
            System.out.println(name + " born in " + year + " would be " + newname
            + " if he was born in " + newYear);
            }
            else if (gender.equals("F")) { // female baby
            System.out.println(name + " born in " + year + " would be " + newname
            + " if she was born in " + newYear);
            }
        }
        else if (rank == -1) {
            newname = "no one";
            System.out.println("NO NAME");
        }
        
       }
    }
    public void testwhatIsNameInYear(){
        whatIsNameInYear("Owen",1974,2014,"M");
    }
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int currentRank = 0;
        int highestRank =0;
        int currentYear = 0;
        int highestYear = 0;
        for (File f:dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String filename = f.getName();
            currentYear = Integer.parseInt(filename.substring(3,7));
            currentRank = getRank(currentYear, name, gender);
            if (highestYear == 0){
                highestYear = currentYear;
                highestRank = currentRank;
            }
            if (currentRank < highestRank && currentRank != -1) {
                highestRank = currentRank;
                highestYear = currentYear;
            }     
        }
        return highestYear;
    }
    public void testyearOfHighestRank () {
        int year = yearOfHighestRank("Mich","M");
        System.out.println("The year with the highest ranking is "+ year);
    }
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource(); 
        int count = 0;
        int total = 0;
        double avgrank = 0;
        for (File f : dr.selectedFiles()){
           FileResource fr = new FileResource(f);
           int year = Integer.parseInt(f.getName().substring(3,7));
           int rank = getRank(year,name,gender);
           if (rank>0){
            total = total + rank;
            count = count + 1;
            avgrank = (double)total/count;
            }
        }
        return avgrank;
    }
    public void testgetAverageRank(){
        double avgrank = getAverageRank("Robert","M");
        System.out.println("Average ranking for Robert ="+avgrank);
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int totalBirths = 0;
        int rank = getRank(year, name, gender);
        FileResource fr = new FileResource("us_babynames_by_year/yob"+ year +".csv");
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : fr.getCSVParser(false)) {
  	    if (rec.get(1).equals(gender)) {
		rank--;
		if (rank > 0) {
			totalBirths += Integer.parseInt(rec.get(2));
                 }
	    }
        }
        return totalBirths;
    }
    public void testGetTotalBirthsRankedHigher(){
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        int total = getTotalBirthsRankedHigher(year,name,gender);
        System.out.println("The total births ranked higher of the name "+name+" is "+total);
    }
}
