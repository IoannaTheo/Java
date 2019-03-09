
/**
 * Γράψτε μια περιγραφή της κλάσης Part1 εδώ.
 * 
 * @author (Το όνομά σας) 
 * @version (Αριθμός έκδοσης ή ημερομηνία εδώ)
 */
public class Part1 {
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
    
    public void testFindStopCodon(){
        //            012345678901234567890123
        String dna = "agtgtaaagTAAagttgaagaTAA";
        int dex = findStopCodon(dna,0,"TAA");
        if (dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna,9,"TAA");
        if (dex != 21)System.out.println("error on 21");
        dex =findStopCodon(dna,1,"TAA");
        if (dex != -1)System.out.println("error on 24");
        dex = findStopCodon(dna,0,"TAG");
        if (dex != -1)System.out.println("error on 24 TAG");
        System.out.println("tests finished");
        
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
public void testFindGene(){
    String dna = "ATGCCCGGGAAATAACCC";
    //String gene = findGene(dna);
    //System.out.println("DNA strand = "+dna);
    //System.out.println("Gene = "+gene);
    
    // no gene
    dna = "GTAAAAGGGGTAGAG";
    //gene = findGene(dna);
    //System.out.println("DNA strand = "+dna);
    //System.out.println("Gene = "+gene);
    
    //     0123456789012345 no gene
    dna = "ATGCCCGGGAAAATAA";
    //gene = findGene(dna);
    //System.out.println("DNA strand = "+dna);
    //System.out.println("Gene = "+gene);
    
    //     01234567890123456789 TGA
    dna = "CCATGGGGCCCTTTTGAAAA";
    //gene = findGene(dna);
    //System.out.println("DNA strand = "+dna);
    //System.out.println("Gene = "+gene);
    
    //     012345678901234567  2 stop codons
    dna = "ATGGGGCCCTAGCGCTAA";
    //gene = findGene(dna);
    //System.out.println("DNA strand = "+dna);
    //System.out.println("Gene = "+gene);
    
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
public void testOn(String dna){
    System.out.println("Testing printAllGenes on "+dna);
    printAllGenes(dna);
}
public void test(){
    testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    testOn("");
    testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
}
}


