/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InformationRetreival;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Vishal Raman
 */
public class Weight {
    
     HashMap<String,HashMap<Integer,Double>> weightMatrix= new HashMap<String,HashMap<Integer,Double>>();
     HashMap termFreq;
     InverseDocumentFrequency idf=new InverseDocumentFrequency ();
     
      public static void main(String args[]) throws IOException, FileNotFoundException, ClassNotFoundException  { 
          Weight w=new Weight();
          w.createWeightMatrix();
     // w.printFile();
      
      }
      public Weight() throws FileNotFoundException, IOException, ClassNotFoundException {
     
       File file=new File("outputFile//termFreqbinary.txt");
          FileInputStream fis = new FileInputStream(file);
          ObjectInputStream ois = new ObjectInputStream(fis);
         termFreq = (HashMap) ois.readObject(); // storing in the hashmap
      }
    
    public void createWeightMatrix()
    {
        
        double wordIDF;
        
        
         for (Object keyWord : termFreq.keySet()) {
               
             wordIDF=idf.IDF((String) keyWord);
             HashMap<Integer,Double> temp =new HashMap();
            
            // temp=null;
             HashMap<Integer,Integer> wordFreqInDoc= (HashMap) termFreq.get(keyWord);
             for (Object keyDoc : wordFreqInDoc.keySet()) {
                 int docId=(int) keyDoc;
                 int TF=(int) wordFreqInDoc.get(keyDoc);
                 double weightInDoc;
                 weightInDoc=TF*wordIDF;
                // System.out.println(docId + "   "+ weightInDoc);
                 temp.put(docId, weightInDoc);
                
                 //System.out.print(temp);
              
                  
             }
           weightMatrix.put((String) keyWord,(HashMap)temp);
            
          
        //  System.out.println((String)keyWord);
          //   System.out.println(temp);
              temp.clear();
            
             
           
        }
          System.out.println(weightMatrix);
      
        
    }
    
    public void printFile() throws IOException
    {
        
//         Lab1 lab= new Lab1();
//         System.out.println("weight");
////           lab.outputFile((HashMap) weightMatrix,"weightMatrix");
//           String filename="weightMatrix";
//            FileWriter out = new FileWriter("outputFile//" + filename + ".txt");  //create a file for output
//        for (Object key : weightMatrix.keySet()) {
//            out.write(key + "  " + weightMatrix.get(key) + "\n");
//        }
//
//        out.close();
//
//        File file = new File("outputFile//" + filename + "binary" + ".txt");  // creating a binary file
//        FileOutputStream fos = new FileOutputStream(file);
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(weightMatrix);
//        fos.close();
//        oos.close();

           
    }
    
}
