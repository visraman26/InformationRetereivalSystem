/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InformationRetreival;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Vishal Raman
 */
public class InverseDocumentFrequency {
     HashMap invIndex,indexOfFile;
     public InverseDocumentFrequency() throws FileNotFoundException, IOException, ClassNotFoundException {
          File file=new File("outputFile//inverted_Indexbinary.txt");
          FileInputStream fis = new FileInputStream(file);
          ObjectInputStream ois = new ObjectInputStream(fis);
          invIndex = (HashMap) ois.readObject(); // storing in the hashmap
          
          
          File file2=new File("outputFile//indexOfFilebinary.txt");
          FileInputStream fis2 = new FileInputStream(file2);
          ObjectInputStream ois2 = new ObjectInputStream(fis2);
          indexOfFile = (HashMap) ois2.readObject(); // storing in the hashmap
          
          
          
          
     
     }
    public double IDF(String word){
       List listOFDocCOntainsWords ;
      int  N=indexOfFile.size();
       listOFDocCOntainsWords=(List) invIndex.get(word);
       int noOfDocContainsWord=0;
       noOfDocContainsWord=listOFDocCOntainsWords.size();
       
      double idf;
       idf= Math.log10(N/noOfDocContainsWord);
       return idf;
       
      
    
    }
     
}
