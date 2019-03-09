
import edu.duke.*;

import java.io.File;

import java.util.*;
public class part4 {
public String findURL(String URL){
         String search = "youtube.com";
         String result = null;
         URLResource ur = new URLResource(URL);
         for(String words : ur.words()){
         int pos = words.toLowerCase().indexOf(search.toLowerCase());
         char quote = '"';
         int firstIndex = words.lastIndexOf(quote,pos);
         if ( pos != -1 & firstIndex != -1 ){
            String found1 = words.substring( firstIndex + 1 ) ;
            int secondIndex = found1.indexOf("\"") ;
            String found2 = found1.substring( 0, secondIndex );
            result= found2;
            System.out.println(result);
            }
            else{
                result = "No Youtube Links";
                System.out.println(result);
            }
        }
        return result;
}
public void findlinks(){
        String url = ("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        URLResource ur = new URLResource(url);
        findURL(url);
  }
}
 

