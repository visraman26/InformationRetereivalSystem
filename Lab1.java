/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InformationRetreival;

import com.sun.xml.internal.ws.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.HashMap;

import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;



/**
 *
 * @author Vishal Raman
 */
public class Lab1 {

    HashMap<Integer, String> fileList = new HashMap<Integer, String>();  // document id hashmap
    int indexOfFile = 1;
    HashMap wordCountHashMap = new HashMap();

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception { 
        Lab1 lab = new Lab1();
        Dictionary dictionary = new Dictionary();
        //HindiStemmerLight hindiStemmerLight = new HindiStemmerLight();
        InvertedIndex invIndex = new InvertedIndex();
        HindiStemmer hindiStemmer = new HindiStemmer();
        TermFrequency tf=new TermFrequency();
        Weight weight=new Weight();

        String[] words = null;
        //hashMap for saving frequency
        File folder = new File("files//hindi//hindi//"); //inside this folder , all files are there
        File[] listOfFiles = folder.listFiles(); //getting the list of all the files
        //Scanner scan=new Scanner(System.in);

        for (File file : listOfFiles) {
            if (file.isFile()) {
                lab.createIndexOfFile(file.getName());//reading the files from folder and creating index of files
            }
        }
        lab.outputFile(lab.fileList, "indexOfFile"); // storing the index of file in a .txt file

        // System.out.println(file.getName()); // for printing the file
        for (Object docID : lab.fileList.keySet()) {  // reading each document by doc ID

            try {
                //File fXmlFile = new File("files//" + lab.fileList.get(key));
                String fXmlFile = new Scanner(new File("files//hindi//hindi//" + lab.fileList.get(docID))).useDelimiter("\\Z").next(); // reading a whole file into a single string
                fXmlFile = fXmlFile.replaceAll("&", ""); //replacing '&' , since in xml it act as end of file if present in between 
               
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(fXmlFile.getBytes("utf-8"))));

                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("story");  // getting all the nodes of main tag i.e "story"

                for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode; // creating a instance of Element class 
                        String title = eElement.getElementsByTagName("title").item(0).getTextContent(); //getting the context of the tag
                        String content = eElement.getElementsByTagName("content").item(0).getTextContent();
                        String line = title.concat(content);
                        words = line.split(" ");  //defined globallyy ,array .. splitting the line of string into individual words array
                    }
                }
            } catch (SAXParseException e) {
                try{
                String fXmlFile = new Scanner(new File("files//hindi//hindi//" + lab.fileList.get(docID))).useDelimiter("\\Z").next(); // reading a whole file into a single string
                fXmlFile = fXmlFile.replaceAll("&", ""); //replacing '&' , since in xml it act as end of file if present in between 
               // String title = StringUtils.substringBetween(fXmlFile, "<title>", "</title>");
                System.out.println(lab.fileList.get(docID));
                //e.printStackTrace();
                
                }
                catch(Exception ex)
                {
                
                }
                
            }
            for (int i = 0; i < words.length; i++) { // loop for each word
                int temp = 0; // for frequency
                String word = words[i]; 
                word = lab.validWords(word);  // method returning the valid words
                word = word.trim(); // to remove white spaces
                if (word.length() > 0) {
                    //System.out.println(word);

                    if (!dictionary.IsWordStopword(word)) {

                        word = hindiStemmer.stemTheString(word);
                        tf.termFreq(word,(int) docID);
                        invIndex.index(word, (int) docID);

                        if (lab.wordCountHashMap.containsKey(word)) // if hashmap already contains the word
                        {
                            temp = (int) lab.wordCountHashMap.get(word);  //saving value of a key, ie frequency
                            temp++; // increasing its frequency
                            lab.wordCountHashMap.put(word, temp); // putting into hashmap
                        } else {
                            lab.wordCountHashMap.put(word, 1);
                        }

                    }
                }
            }
        }
        // System.out.println(hm.size());
        // System.out.println(hm);

       // System.out.println(lab.wordCountHashMap.size());
        //System.out.println(lab.wordCountHashMap);
        tf.printFile();

        lab.outputFile(lab.wordCountHashMap, "wordCount");

        invIndex.printIndex();
       // weight.createWeightMatrix();
        //weight.printFile();

//        System.out.println("Press \n1. Word Count \n2. Documents that contains word");
//        int num;
//        num= scan.nextInt();
//        lab.callSwitchStatement(num);
    }

    public void outputFile(HashMap hm, String filename) throws IOException //method for creating an output file
    {

        FileWriter out = new FileWriter("outputFile//" + filename + ".txt");  //create a file for output
        for (Object key : hm.keySet()) {
            out.write(key + "  " + hm.get(key) + "\n");
        }

        out.close();

        File file = new File("outputFile//" + filename + "binary" + ".txt");  // creating a binary file
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(hm);
        fos.close();
        oos.close();

    }

    public String validWords(String word) {

        word = word.replaceAll("\\p{Punct}+", "");   //remove puctuation marks
        word = word.replaceAll(",", "");
        word = word.replaceAll("।", "");

        return word;

    }

    public void createIndexOfFile(String fileName) {

        fileList.put(indexOfFile, fileName);
        indexOfFile++;

    }

//    public void callSwitchStatement(int num) throws UnsupportedEncodingException
//    {
//           Scanner scan=new Scanner(System.in);
//            String word;
//             Query query=new Query();
//              System.out.println("Enter Word ");
//                word=scan.nextLine();
//                 word="लग";
//        switch (num)
//        {
//            case 1 :
//                
//                
//                System.out.println();
//               
//                int frequency=query.frequencyOfWord(word,wordCountHashMap);
//                System.out.println(word+" : "+ frequency);
//                break;
//                
//            case 2 :
//                InvertedIndex invIndex = new InvertedIndex();
//                System.out.println(invIndex.map);
//                
//               // List indexOfDocuments=query.documentsContainsWord(word);
//                //System.out.println(word+" : "+ indexOfDocuments);
//                
//                break;
//            default :   System.out.println("Wrong Choice");
//                
//                
//                
//                
//        }
//    }
}
