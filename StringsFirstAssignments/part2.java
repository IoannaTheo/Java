
/**
 * Γράψτε μια περιγραφή της κλάσης part2 εδώ.
 * 
 * @author (Το όνομά σας) 
 * @version (Αριθμός έκδοσης ή ημερομηνία εδώ)
 */
public class part2 {
    public String findSimpleGene(String dna,String startCodon,String stopCodon){
        //start codon is "ATG"
        //stop codon is "TAA"
        
        String result = "";
        startCodon="ATG";
        stopCodon="TAA";
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1){//no ATG
            return "";
        }
        int stopIndex = dna.indexOf(stopCodon,startIndex+3);
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
        String startCodon="ATG";
        String stopCodon= "TAA";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene.toUpperCase());
        
        dna = "ATGCGACGAAGGTCAGCA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene.toUpperCase());
        
        dna = "GTGCGACGAAGGTCAGCA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene.toUpperCase());
        
        dna = "GATGTGCGACGAAGGTCAGTAACA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene.toUpperCase());
        
        dna = "ATGGTGCGACGTAAAAGGTCAGCA";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + gene.toUpperCase());
        
        dna = "aaaatgtaacgt";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna.toUpperCase(),startCodon,stopCodon);
        System.out.println("Gene is " + gene.toLowerCase());
    }
}


