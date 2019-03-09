
/**
 * Γράψτε μια περιγραφή της κλάσης part3 εδώ.
 * 
 * @author (Το όνομά σας) 
 * @version (Αριθμός έκδοσης ή ημερομηνία εδώ)
 */
public class part3 {
    public int findStopCodon(String dna,int startIndex,String stopCodon){
        int currIndex = dna.indexOf(stopCodon,startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 ==0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon,currIndex +1);

            
            }
          }
          return -1;
    }
    
    public String findGene(String dna, int where){
    int startIndex = dna.indexOf("ATG", where);
    if (startIndex == -1){
        return "";
    }
    int taaIndex = findStopCodon(dna,startIndex,"TAA");
    int tagIndex = findStopCodon(dna,startIndex,"TAG");
    int tgaIndex = findStopCodon(dna,startIndex,"TGA");
    //int temp = Math.min(taaIndex,tagIndex);
    //int minIndex = Math.min(temp,tgaIndex);
    int minIndex = 0;
    if (taaIndex == -1 ||(tgaIndex != -1 &&tgaIndex < taaIndex)){
    minIndex = tgaIndex;
    }
    else {
    minIndex = taaIndex;
    }
    if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
    minIndex = tagIndex;
    }
    if (minIndex == -1){
     return "";
    }
return dna.substring(startIndex,minIndex + 3);
}
public void printAllGenes(String dna){
    int startIndex = 0;
    while ( true) {
        String currentGene = findGene(dna, startIndex);
        if (currentGene.isEmpty()){
            break;
        }
        System.out.println(currentGene);
        startIndex = dna.indexOf(currentGene, startIndex) + 
             currentGene.length();
     }
}
public int countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        String currentGene = "";
    while (true){
        currentGene = findGene(dna,startIndex);
        if(currentGene.isEmpty()){
        break;
        }
        count = count + 1;
        startIndex = dna.indexOf(currentGene,startIndex) + currentGene.length();
  
    }
    return count;
        
    }
    public void testcountGenes() {
      
        String dna = "CCCATGGCATAGCCCATGGTATACTAA";
        System.out.println("The DNA strand is " + dna);
        int amount = countGenes(dna);
        System.out.println(amount);
        //the answer should be 2
}
}
