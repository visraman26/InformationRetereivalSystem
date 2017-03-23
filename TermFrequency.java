/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InformationRetreival;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Vishal Raman
 */
public class TermFrequency {
    
    HashMap<String,HashMap<Integer,Integer>> docTermFreq= new HashMap<String,HashMap<Integer,Integer>> ();
    
    public void termFreq(String Word,int docID)
    {
       HashMap<Integer,Integer> temp = new HashMap();
        if(docTermFreq.containsKey(Word))
        {
            temp=docTermFreq.get(Word);
            if(temp.containsKey(docID))
            {
                int freq= temp.get(docID);
                freq++;
                temp.put(docID, freq);
                docTermFreq.put(Word, temp);
            }
            else
            {
                temp.put(docID, 1);
                docTermFreq.put(Word, temp);
            
            }
        
        }
        else
        {
            temp.put(docID,1);
            docTermFreq.put(Word,temp);
        
        }
        
    }
     public void printFile() throws IOException
     {
         Lab1 lab= new Lab1();
           lab.outputFile((HashMap) docTermFreq,"termFreq");
//          FileWriter out = new FileWriter("outputFile//termfreq.txt");  //create a file for output
//        for (Object key : docTermFreq.keySet()) {
//            out.write(key + "  " + docTermFreq.get(key) + "\n");
//        }

//        out.close();
         
     }
    
}
