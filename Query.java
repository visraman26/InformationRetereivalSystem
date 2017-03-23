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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Vishal Raman
 */
public class Query {
    
      public static void main(String args[]) throws Exception {
          Query query = new Query();
        
          String word="सरकार"; // change the word of which you want 
           File file=new File("outputFile//wordCountbinary.txt");
            FileInputStream fis = new FileInputStream(file);
          ObjectInputStream ois = new ObjectInputStream(fis);
          HashMap hm = (HashMap) ois.readObject(); // storing in the hashmap
          fis.close();
          ois.close();
          query.frequencyOfWord(word, hm);
          query.documentsContainsWord(word);
          
          
      
      }
    public void frequencyOfWord(String word,HashMap hash)
    {
        int frequency=0;
        if(hash.containsKey(word))
        {
            frequency = (int) hash.get(word);
            System.out.println(word +" : "+frequency);
            
        }
        else
        {
             System.out.println("word does not exist");
        }
        
  
    }
    
     public void documentsContainsWord(String word) throws FileNotFoundException, IOException, ClassNotFoundException
     {
         
           File file=new File("outputFile//inverted_Indexbinary.txt");
            FileInputStream fis = new FileInputStream(file);
          ObjectInputStream ois = new ObjectInputStream(fis);
          HashMap invIndexMap = (HashMap) ois.readObject();
          ois.close();
         List indexOfDocuments=null;
           
           
           if(invIndexMap.containsKey(word))
           {
              
               indexOfDocuments=(List) invIndexMap.get(word);
               System.out.println(word +" : "+indexOfDocuments);
           }
            else
        {
             System.out.println("word does not exist");
        }
         
         
        
     }
    
}
