
package InformationRetreival;
/**
 *
 * @author Vishal Raman
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;
import java.util.Scanner;

public class InvertedIndex {

    /**
     * @param args the command line arguments
     */
    
        
       Map <String ,List<Integer>> map = new HashMap <String, List<Integer>>();
       Lab1 lab=new Lab1(); 
        
       public void index (String word,int docIndex )
       {     
           if(map.containsKey(word))
           {
               
               List<Integer> oldlist= map.get(word) ; // getting all the vaues of oldlist
               if(oldlist.contains(docIndex)) // checking whether it contains doc id or not
               {
                   //if it contains , then don't update the list
               }
               else
               {
               oldlist.add(docIndex);
               map.put(word, oldlist);
               }
           }
           else
           {
           List<Integer> newlist = new ArrayList<Integer>();
           newlist.add(docIndex);
           map.put(word, newlist);
           }
       
       }
       
       public void printIndex() throws IOException
       {
          lab.outputFile((HashMap) map,"inverted_Index");
           
//           
//            File file=new File("inverted_Index_binary.txt");
//            FileOutputStream fos = new FileOutputStream(file);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(map);
//            oos.close();
    
       }
     }
    

