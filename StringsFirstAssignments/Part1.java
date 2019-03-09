
/**
 * Γράψτε μια περιγραφή της κλάσης Part1 εδώ.
 * 
 * @author (Το όνομά σας) 
 * @version (Αριθμός έκδοσης ή ημερομηνία εδώ)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        //start codon is "ATG"
        //stop codon is "TAA"
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){//no ATG
            return "";
        }
        int stopIndex = dna.indexOf("TAA",startIndex+3);
        if (stopIndex == -1){//no TAA
            return "";
        }
        if ((stopIndex - startIndex) % 3 == 0){
            result = dna.substring(startIndex,stopIndex+3);
        }
        else {
            result = "";
        }
    
        
        return result;
    }
    public void testSimpleGene(){
        String dna = "GGGATCAAAGTGACTAA";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATGCGACGAAGGTCAGCA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "GTGCGACGAAGGTCAGCA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "GATGTGCGACGAAGGTCAGTAACA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "ATGGTGCGACGTAAAAGGTCAGCA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
    }
}

