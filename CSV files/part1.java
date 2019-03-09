
/**
 * Γράψτε μια περιγραφή της κλάσης part1 εδώ.
 * 
 * @author (Το όνομά σας) 
 * @version (Αριθμός έκδοσης ή ημερομηνία εδώ)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class part1 {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //countryInfo(parser,"Nauru");
        //listExportersTwoProducts(parser,"cotton","flowers");
        //numberOfExporters(parser,"cocoa");
        bigExporters(parser,"$999,999,999,999");
    }
    public void countryInfo(CSVParser parser,String country){
        for (CSVRecord record:parser){
            String Country = record.get("Country");
            String Export = record.get("Exports");
            String Value = record.get("Value (dollars)");
            if(Country.equals(country)){
                System.out.println(Country+": "+Export+": "+Value);
            }
            else{
                System.out.println("NOT FOUND");
            }
        }
    }
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println(country);
        
            }
        }  
    }
    public void numberOfExporters(CSVParser parser,String exportItem){
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)){
                ++count;
                
            }
        }
        System.out.println(count);
    }
    public void bigExporters(CSVParser parser,String amount){
        int minlength = 16;
        for (CSVRecord record : parser){
        String exportAmount = record.get("Value (dollars)");
           if (exportAmount.length()>minlength){
            System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
    
           }
        }
     }
}
