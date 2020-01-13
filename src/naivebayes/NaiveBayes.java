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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        train();
        predict("Input Data\\Ham\\2");
        predict("Input Data/Spam/1091477350.8044_550.txt");
    }


    public static void train() {
        List<Document> dataset = preprocessDataset("Training Data");

        Stats stats = new Stats(dataset);
        //System.out.println(stats.categoryJointCount);


        knowledgeBase = new KnowledgeBase(stats);
        //System.out.println(knowledgeBase.logLikelihood);

    }

    public static void predict(String filepath){
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

        // Make a prediction based on probabilities
        // Todo use ln to prevent dataloss
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
    if(PHam>PSpam) System.out.println("HAM Detected!!! \noink oink");
    else if (PHam==PSpam) System.out.println("Equal");
    else {
        System.out.println("SPAM Detected!!! \n no oink oink");
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

    private void calcProb(double d){
        Double probability =d;
        Math.log10(probability);
    }




}
