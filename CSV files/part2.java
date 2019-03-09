
/**
 * Γράψτε μια περιγραφή της κλάσης part2 εδώ.
 * 
 * @author (Το όνομά σας) 
 * @version (Αριθμός έκδοσης ή ημερομηνία εδώ)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class part2 {
    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            largestSoFar = getlargestOfTwo(currentRow,largestSoFar);
        }
        return largestSoFar;
    }
    public void testHottestInDay(){
        FileResource fr = new FileResource("nc_weather/2015/weather-2015-01-02.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was "+largest.get("TemperatureF") +" at " +largest.get("TimeEST"));
    }
    public CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getlargestOfTwo(currentRow,largestSoFar);
        }
        return largestSoFar;
    }
    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was "+largest.get("TemperatureF")+ " at "+largest.get("DateUTC"));
    }
    public CSVRecord getlargestOfTwo (CSVRecord currentRow,CSVRecord largestSoFar){
        if (largestSoFar == null){
                largestSoFar = currentRow;
        }
        else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if (currentTemp > largestTemp){
                    largestSoFar = currentRow;
                }
       }
     return largestSoFar;
    }
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser) {
            coldestSoFar = getcoldestOfTwo(currentRow,coldestSoFar);
        }
        return coldestSoFar;
    }
    public CSVRecord getcoldestOfTwo (CSVRecord currentRow,CSVRecord coldestSoFar){
        if (coldestSoFar == null){
                coldestSoFar = currentRow;
        }
        else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTemp < coldestTemp && currentTemp != -9999){
                    coldestSoFar = currentRow;
                }
       }
     return coldestSoFar;
    }
    public void testColdestHourInFile(){
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was "+coldest.get("TemperatureF") +" at " +coldest.get("DateUTC"));
    }
    public String fileWithColdestTemperature(){
        CSVRecord coldestSoFar = null;
        String filename = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = getcoldestOfTwo(currentRow,coldestSoFar);
            if (coldestSoFar == currentRow) {
                filename = f.getName();
            }
        }
      return filename; 
    }
    public void testFileWithColdestTemperature(){
        String coldestfile = fileWithColdestTemperature();
        FileResource file = new FileResource("./nc_weather/2013/"+coldestfile);
        CSVParser parser = file.getCSVParser();
        CSVRecord coldest = coldestHourInFile(file.getCSVParser());
        System.out.println("Coldest day was in file "+coldestfile);
        System.out.println("Coldest temperature on that day was "+coldest.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were :");
        for (CSVRecord record : file.getCSVParser()) {
            System.out.println(record.get("DateUTC")+" "+record.get("TemperatureF"));
        }
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser){
             String humidity = currentRow.get("Humidity");
             if (lowestSoFar == null){
                lowestSoFar = currentRow;
                }
             if (!humidity.equals("N/A")){
                double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity"));
                if (lowestSoFar !=null && currentHumidity < lowestHumidity){
                  lowestSoFar = currentRow;
                }
            }
            if (humidity.equals("N/A")){
               lowestSoFar = lowestSoFar;
            }
        }
        return lowestSoFar;
    }
    public void testLowestHumidityInFile() {
       FileResource fr = new FileResource();
       CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
       System.out.println("lowest humidity was " + smallest.get
       ("Humidity") + " at " + smallest.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInManyFiles() {
       CSVRecord lowestSoFar = null;
       DirectoryResource dr = new DirectoryResource();
       // iterate over files
       for (File f : dr.selectedFiles()) {
           FileResource fr = new FileResource(f);
           // use method to get smallest in file
           CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
           if (lowestSoFar == null) {
               lowestSoFar = currentRow;
           }
           else {
               double currentHumidity = Double.parseDouble(currentRow.get
               ("Humidity"));
               double lowestHumidity = Double.parseDouble(lowestSoFar.get
               ("Humidity"));
               // Check if currentRow's humidity < lowestSoFar's
               if (currentHumidity < lowestHumidity) {
                   // If so,update lowestSoFar to currentRow
                   lowestSoFar = currentRow;
               }
           }
       }
       // The lowestSoFar is the answer
       return lowestSoFar;
    }
    public void testLowestHumidityInManyFiles() {
       CSVRecord lowest = lowestHumidityInManyFiles();
       System.out.println("Lowest humidity was " + lowest.get("Humidity") + " at " + lowest.get
       ("DateUTC"));
    }
    public double averageTemperatureinFile (CSVParser parser){
        CSVRecord alltemps = null;
        double avg = 0;
        double count = 0;
        double sum = 0;
        for(CSVRecord currentRow: parser){
                alltemps = currentRow;
                String temp = currentRow.get("TemperatureF");
                double curr = Double.parseDouble(currentRow.get("TemperatureF"));
                if (!temp.equals("-9999")){
                    count++;
                    sum = curr + sum;
                } 
            }   
        avg = sum/count;
        return avg;
    }
    public void testAverageTemperatureinFile(){
        FileResource fr = new FileResource();
        double avg = averageTemperatureinFile(fr.getCSVParser());
        System.out.println("Average temp in the file was " + avg);       
    }   
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
        //value = 80;
        CSVRecord alltemps = null;
        double avg = 0;
        double count = 0;
        double sum = 0;
        for(CSVRecord currentRow: parser){
                alltemps = currentRow;
                String temp = currentRow.get("TemperatureF");
                String humidity = currentRow.get("Humidity");
                double curr = Double.parseDouble(currentRow.get("TemperatureF"));
                double hum = Double.parseDouble(currentRow.get("Humidity"));
                if (!temp.equals("-9999") && hum >= value){
                    count++;
                    sum = curr + sum;
                    avg = sum/count;
                    //System.out.println("Average temp when high Humidity is "+avg);
                }
            }     
        return avg;
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        double avg = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
        if (avg!= 0){
           System.out.println("Average temp when high Humidity is " + avg); 
        }
        else {
            System.out.println("No temperatures with that humidity");
        }
      }
}
