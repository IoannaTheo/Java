
/**
 * Γράψτε μια περιγραφή της κλάσης part2 εδώ.
 * 
 * @author (Το όνομά σας) 
 * @version (Αριθμός έκδοσης ή ημερομηνία εδώ)
 */
public class part2 {
    public int howMany (String a, String b) {
        int startIndex = 0;
        int count = 0;
        int currIndex = 0;
        while (currIndex != -1) {
            currIndex = b.indexOf (a, startIndex);
            startIndex = currIndex + a.length();
            ++count;
        }
        return count-1;
    }
    public void test(){
    String b = "ATGAACGAATTGAATC";
    String a = "GAA";
    int find = howMany(a,b);
    System.out.println("Number of occurrence of GAA is " + find);
    }

}
