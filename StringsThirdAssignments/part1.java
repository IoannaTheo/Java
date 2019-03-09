
/**
 * Γράψτε μια περιγραφή της κλάσης part1 εδώ.
 * 
 * @author (Το όνομά σας) 
 * @version (Αριθμός έκδοσης ή ημερομηνία εδώ)
 */
import edu.duke.*;
import java.io.File;
public class part1 {
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
public StorageResource getAllGenes(String dna){
    StorageResource geneList = new StorageResource();
    int startIndex = 0;
    while ( true) {
        String currentGene = findGene(dna, startIndex);
        if (currentGene.isEmpty()){
            break;
        }
        geneList.add(currentGene);
        startIndex = dna.indexOf(currentGene, startIndex) + 
             currentGene.length();
     }
     return geneList;
}
public void testOn(String dna){
    System.out.println("Testing printAllGenes on "+dna);
    StorageResource genes = getAllGenes(dna);
    for (String g:genes.data()){
        System.out.println(g);
}
}
public void test(){
    testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    testOn("");
    testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
}
public int howManyC (String dna) {
   int countc=0;
        int start=0;
        while(true){
         int pos=dna.indexOf("C",start);//returns index
         if(pos==-1){
             break;
            }
         countc=countc+1;
         start=pos+1; //update start right after index pos
        }
        return countc;
}
public void testhowManyC() {
    String dna = "ATTGAAACCCGGGTGAA";
    int Ctimes = howManyC(dna);
    System.out.println("Times of C in stringb is "+ Ctimes);//3
}
public int howManyG (String dna) {
    int countg=0;
        int start=0;
        while(true){
         int pos=dna.indexOf("G",start);//returns index
         if(pos==-1){
            break;
        }
         countg=countg+1;
         start=pos+1; //update start right after index pos
        }
        return countg;
}
public void testhowManyG() {
    String dna = "ATTGAATTCCGGGAGGTGAA";
    int Gtimes = howManyG(dna);
    System.out.println("Times of G in stringb is "+ Gtimes);//7
}
public float cgRatio(String dna){ // apparently works well
        float ratio=(float)(howManyC(dna)+ howManyG(dna))/dna.length();
        return  ratio;
}
public void testcgRatio(){
    String dna = "ATGCGCTGGCGTCA";
    float Ratio = cgRatio(dna);
    System.out.println("CG ratio = "+ Ratio);
}
public int countCTG(String dna){
    int startIndex = dna.indexOf("CTG");
    int count = 0;
    if (startIndex == -1){
        return -1;
    }
    while (startIndex !=-1 && startIndex < dna.length()){
        ++count;
        startIndex = dna.indexOf("CTG",startIndex + 3);
    }
    return count;
}
public void testcountCTG(){
    String dna = "CTGATGCTG";
    int firstcount = countCTG(dna);
    System.out.println("Number of CTG in this strand is " + firstcount); //2
    dna = "TGCTGAACTGACTG";
    int secondcount = countCTG(dna);
    System.out.println("Number of CTG in this strand is " + secondcount); //3
}
public void processGenes (StorageResource sr){
      int count = 0;
        for(String s:sr.data()){
          if(s.length()>60){
              count++;
              System.out.println("gene is : "+s);
              System.out.println("gene number: "+count);
            }
      }
      int ratiocount = 0;
      for (String s : sr.data()){
            float ratio = cgRatio(s);
            if (ratio > 0.35){
                ratiocount++;
                System.out.println(s);
             
            }
            System.out.println("ratiocount: "+ratiocount);
       }
}
public void testProcessGenes () {
        FileResource fr = new FileResource("GRch38dnapart.fa.txt");
        String dna = fr.asString().toUpperCase();
        System.out.println("total genes : "+getAllGenes(dna).size());
        processGenes(getAllGenes(dna));
        System.out.println("total genes : "+getAllGenes(dna).size());
        System.out.println("CTGcounts : "+countCTG(dna));
}
}
 
