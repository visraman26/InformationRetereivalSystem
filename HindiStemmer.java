package InformationRetreival;



import java.util.Arrays;
import static org.apache.lucene.analysis.util.StemmerUtil.*;


public class HindiStemmer {
   
//       public static void main(String args[]){
//           HindiStemmer hindiStemmer=new HindiStemmer();
//           String s="उपाध्याय";
//         
//             System.out.println("stem word : "+hindiStemmer.stemTheString(s)+"ooo");
//           
//          //  String newString1 = new String(returnCh);
//       // System.out.println("newString1 : " + newString1);
//   
//       } 
       public String stemTheString(String word)
       {
          
           char[] ch=word.toCharArray(); // word into array of words
           int n=stem(ch, ch.length); // calling stem method and getting the length of stem 
           char[] returnedStemArray = Arrays.copyOf(ch,n); //saving in array till the returned value of stem
           String stemOfWord=new String(returnedStemArray); // converting array into string
           return stemOfWord; // returning the stem word
       }
  public int stem(char buffer[], int len) {
    // 5
    if ((len > 6) && (endsWith(buffer, len, "ाएंगी")
        || endsWith(buffer, len, "ाएंगे")
        || endsWith(buffer, len, "ाऊंगी")
        || endsWith(buffer, len, "ाऊंगा")
        || endsWith(buffer, len, "ाइयाँ")
        || endsWith(buffer, len, "ाइयों")
        || endsWith(buffer, len, "ाइयां")
      ))
      return len - 5;
    
    
    // 4
    if ((len > 5) && (endsWith(buffer, len, "ाएगी")
        || endsWith(buffer, len, "ाएगा")
        || endsWith(buffer, len, "ाओगी")
        || endsWith(buffer, len, "ाओगे")
        || endsWith(buffer, len, "एंगी")
        || endsWith(buffer, len, "ेंगी")
        || endsWith(buffer, len, "एंगे")
        || endsWith(buffer, len, "ेंगे")
        || endsWith(buffer, len, "ूंगी")
        || endsWith(buffer, len, "ूंगा")
        || endsWith(buffer, len, "ातीं")
        || endsWith(buffer, len, "नाओं")
        || endsWith(buffer, len, "नाएं")
        || endsWith(buffer, len, "ताओं")
        || endsWith(buffer, len, "ताएं")
        || endsWith(buffer, len, "ियाँ")
        || endsWith(buffer, len, "ियों")
        || endsWith(buffer, len, "ियां")
        ))
      return len - 4;
    
    // 3
    if ((len > 4) && (endsWith(buffer, len, "ाकर")
        || endsWith(buffer, len, "ाइए")
        || endsWith(buffer, len, "ाईं")
        || endsWith(buffer, len, "ाया")
        || endsWith(buffer, len, "ेगी")
        || endsWith(buffer, len, "ेगा")
        || endsWith(buffer, len, "ोगी")
        || endsWith(buffer, len, "ोगे")
        || endsWith(buffer, len, "ाने")
        || endsWith(buffer, len, "ाना")
        || endsWith(buffer, len, "ाते")
        || endsWith(buffer, len, "ाती")
        || endsWith(buffer, len, "ाता")
        || endsWith(buffer, len, "तीं")
        || endsWith(buffer, len, "ाओं")
        || endsWith(buffer, len, "ाएं")
        || endsWith(buffer, len, "ुओं")
        || endsWith(buffer, len, "ुएं")
        || endsWith(buffer, len, "ुआं")
        ))
      return len - 3;
    
    // 2
    if ((len > 3) && (endsWith(buffer, len, "कर")
        || endsWith(buffer, len, "ाओ")
        || endsWith(buffer, len, "िए")
        || endsWith(buffer, len, "ाई")
        || endsWith(buffer, len, "ाए")
        || endsWith(buffer, len, "ने")
        || endsWith(buffer, len, "नी")
        || endsWith(buffer, len, "ना")
        || endsWith(buffer, len, "ते")
        || endsWith(buffer, len, "ीं")
        || endsWith(buffer, len, "ती")
        || endsWith(buffer, len, "ता")
        || endsWith(buffer, len, "ाँ")
        || endsWith(buffer, len, "ां")
        || endsWith(buffer, len, "ों")
        || endsWith(buffer, len, "ें")
        ))
      return len - 2;
    
    // 1
    if ((len > 2) && (endsWith(buffer, len, "ो")
        || endsWith(buffer, len, "े")
        || endsWith(buffer, len, "ू")
        || endsWith(buffer, len, "ु")
        || endsWith(buffer, len, "ी")
        || endsWith(buffer, len, "ि")
        || endsWith(buffer, len, "ा")
       ))
      return len - 1;
    return len;
  }
}
