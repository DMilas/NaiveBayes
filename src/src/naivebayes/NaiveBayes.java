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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dmilas
 */
public class NaiveBayes {
    public static final String HAM_FOLDER = "\\Ham\\";
    public static final String SPAM_FOLDER = "\\Spam\\";
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


    


    public static void train(String filepath) {
        List<Document> dataset = preprocessDataset(filepath);

        Stats stats = new Stats(dataset);



        knowledgeBase = new KnowledgeBase(stats);


    }

    public static boolean predict(String filepath,String category){
        Document inputDoc=TextTokenizer.tokenize(filepath,"");
        Map<String,Map<String,Double>> foundlikelihoods=new HashMap<>();

        // Get the appropriate probabilities
        for(HashMap.Entry<String,HashMap<String,Double>> likelihood: knowledgeBase.logLikelihood.entrySet()) {
            HashMap<String,Double> temp=new HashMap();
            for(Map.Entry<String,Integer> word: inputDoc.tokens.entrySet()){
                if(likelihood.getValue().containsKey(word.getKey())){

                    temp.put(word.getKey(),likelihood.getValue().get(word.getKey()));

                }

            }
            foundlikelihoods.putIfAbsent(likelihood.getKey(),temp);
        }

        double PSpam=1,PHam=1;


        for (Map.Entry<String,Map<String,Double>> n:foundlikelihoods.entrySet()){
            if(n.getKey().toLowerCase().equals("ham")){
                for(Map.Entry<String,Double> possibility: n.getValue().entrySet()){
                    PHam+=possibility.getValue();
                }
            }
            else if(n.getKey().toLowerCase().equals("spam")){
                for(Map.Entry<String,Double> possibility: n.getValue().entrySet()){
                    PSpam+=possibility.getValue();
                }
            }
        }
        if(PHam>PSpam) {
            //System.out.println("HAM Detected!!! ");
            return category.toLowerCase().equals("ham");
        }
    
        else {
            //System.out.println("SPAM Detected!!! ");
            return category.toLowerCase().equals("spam");
        }
    }




    public static List<Document> preprocessDataset(String filepath) {
        List<Document> dataset = new ArrayList<>();
        File ham = new File(filepath + HAM_FOLDER);
        File[] listOfHams = ham.listFiles();
        File spam = new File(filepath + SPAM_FOLDER);
        File[] listOfSpams = spam.listFiles();


        for (int i = 0; i < listOfHams.length; i++) {
            dataset.add(TextTokenizer.tokenize(listOfHams[i].getAbsolutePath(), "HAM"));



        }
        for (int i = 0; i < listOfSpams.length; i++) {
            dataset.add(TextTokenizer.tokenize(listOfSpams[i].getAbsolutePath(), "SPAM"));

        }



        return dataset;
    }
    
    public static double predictDataset(String filepath){
        List<Document> dataset= new ArrayList<>();
        File ham = new File(filepath + HAM_FOLDER);
        File[] listOfHams = ham.listFiles();
        File spam = new File(filepath + SPAM_FOLDER);
        File[] listOfSpams = spam.listFiles();
        int accFrequency=0;
        for(int i=0;i<listOfHams.length;i++)if(predict(listOfHams[i].getAbsolutePath(),"Ham"))accFrequency++;
        for(int i=0;i<listOfSpams.length;i++)if(predict(listOfSpams[i].getAbsolutePath(),"Spam"))accFrequency++;
        
        return (accFrequency/(double)(listOfHams.length+listOfSpams.length))*100;
    }






}
