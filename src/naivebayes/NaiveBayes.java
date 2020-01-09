/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naivebayes;

import naivebayes.Functionalities.StatCalculation;
import naivebayes.Functionalities.TextTokenizer;
import naivebayes.constructors.Document;
import naivebayes.constructors.KnowledgeBase;
import naivebayes.constructors.Stats;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dmilas
 */
public class NaiveBayes {
    public static final String HAM_FOLDER="\\Ham\\";
    public static final String SPAM_FOLDER="\\Spam\\";
    private static KnowledgeBase knowledgeBase;


    public KnowledgeBase getKnowledgeBase() {
        return knowledgeBase;
    }

    public void setKnowledgeBase(KnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }



    public NaiveBayes(KnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }

    public NaiveBayes() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      // preprocessDataset("Training Data");
      // preprocessDataset("Input Data");

        train();
    }


    public static void train(){
        List<Document> dataset =preprocessDataset("Training Data");

        Stats stats=new Stats(dataset);
        //System.out.println(stats.categoryJointCount.get("HAM"));
        //System.out.println(stats.categoryJointCount.get("SPAM"));
        //System.out.println(StatCalculation.calcPriors(stats));


        knowledgeBase=new KnowledgeBase(stats);







    }















    public static List<Document> preprocessDataset(String filepath){
        List<Document> dataset= new ArrayList<>();
        File ham=new File(filepath+HAM_FOLDER);
        File[] listOfHams= ham.listFiles();
        File spam=new File(filepath+SPAM_FOLDER);
        File[] listOfSpams=spam.listFiles();


        for(int  i=0; i<listOfHams.length;i++){
            dataset.add(TextTokenizer.tokenize(listOfHams[i].getAbsolutePath(),"HAM"));
            //ImportDataset.putData(listOfHams[i].getAbsolutePath(), "Ham");


        }
        for (int i = 0; i <listOfSpams.length ; i++) {
            dataset.add(TextTokenizer.tokenize(listOfSpams[i].getAbsolutePath(),"SPAM"));
            //ImportDataset.putData(listOfSpams[i].getAbsolutePath(),"Spam");
        }

        for(Document d: dataset){
            System.out.println(d.category+"\t"+d.tokens);
        }

        return dataset;
    }




    
}
