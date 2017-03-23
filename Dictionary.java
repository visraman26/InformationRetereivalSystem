
package InformationRetreival;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Vishal Raman
 */
public class Dictionary {
    
     ArrayList<String> dictionaryList = new ArrayList<String>();
     Dictionary() throws FileNotFoundException
     {
         File f=new File ("files2/hindiStopWords.txt"); // reading the stopword file
         Scanner scan =new Scanner(f);
      
                while(scan.hasNext())
                {
                    String word= scan.next();
                    this.dictionaryList.add(word.trim());  // storing into list
                }
     
     }
     
 public boolean IsWordStopword(String words)
    {
        words=words.trim();
        if(this.dictionaryList.contains(words)) // checking whether it is a stopword or not
            return true;
        else
            return false;
    
    }
    
}
