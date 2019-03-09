
/**
 * Γράψτε μια περιγραφή της κλάσης part3 εδώ.
 * 
 * @author (Το όνομά σας) 
 * @version (Αριθμός έκδοσης ή ημερομηνία εδώ)
 */
public class part3 {
    public boolean twoOccurrences(String stringa,String stringb){
        int i = 0;
		int count = 0; 
		
		while((i = stringb.indexOf(stringa, i)) != -1){
		    i += stringa.length();
		        count ++; 
		    }
			if (count >= 2) {
				return true;
			}
			else {
				return false;
			}
        }
    public void testing(){
        String stringa = "atg";
        String stringb = "atgcagtgcggcatgggtatggcg";
        boolean x = twoOccurrences(stringa,stringb);
        System.out.println("String a = "+ stringa);
        System.out.println("String b = " + stringb);
        System.out.println("x = "+ x);
        String lastPart3 = lastPart(stringa,stringb);
        System.out.println("The part of the string after "+ stringa +"in "+ stringb+"is "+lastPart3);
        
        stringa = "no";
        stringb = "yes or no?";
        boolean y = twoOccurrences(stringa,stringb);
        System.out.println("String a = "+ stringa);
        System.out.println("String b = " + stringb);
        System.out.println("x = "+ y);
        String lastPart2 = lastPart(stringa,stringb);
        System.out.println("The part of the string after "+ stringa +"in "+ stringb+"is "+lastPart2);
        
        stringa = "yes";
        stringb = "yes i do,or yes i don't?";
        boolean z = twoOccurrences(stringa,stringb);
        System.out.println("String a = "+ stringa);
        System.out.println("String b = " + stringb);
        System.out.println("x = "+ z);
        String lastPart1 = lastPart(stringa,stringb);
        System.out.println("The part of the string after "+ stringa +"in "+ stringb+"is "+lastPart1);
  
    }
    public String lastPart(String stringa,String stringb){
        int num = stringb.indexOf(stringa);
        int lena = stringa.length();
         if(num != -1) {
         String New = stringb.substring(num+lena);
         return New;
         }
         else {
          return stringb;
         }
    }
}

