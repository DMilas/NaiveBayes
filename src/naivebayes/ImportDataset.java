/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naivebayes;

import naivebayes.Functionalities.TextTokenizer;
import naivebayes.constructors.Document;

import java.util.ArrayList;

/**
 *
 * @author dmilas
 */
public class ImportDataset {
   
    
    public static ArrayList<Document> dataset=new ArrayList<>();
    
    
    public static void putData(String filename,String Category){
        dataset.add(TextTokenizer.tokenize(filename, Category));
    }
    
     
    
  
}
