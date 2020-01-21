/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naivebayes;

import naivebayes.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author dmilas
 */
public class main {
    
    
    public static void main(String[] args) throws FileNotFoundException {

        PrintWriter out=new PrintWriter("Results.txt");

        NaiveBayes kb10=new NaiveBayes();
        kb10.train("Training Data\\10");
        System.out.println("Percentage of accuracy with 10 mails as training data:\n"+kb10.predictDataset("Input Data v2"));
        out.println("10\t"+kb10.predictDataset("Input Data v2"));

        NaiveBayes kb20=new NaiveBayes();
        kb20.train("Training Data\\20");
        System.out.println("Percentage of accuracy with 20 mails as training data:\n"+kb20.predictDataset("Input Data v2"));
        out.println("20\t"+kb20.predictDataset("Input Data v2"));

        NaiveBayes kb30=new NaiveBayes();
        kb30.train("Training Data\\30");
        System.out.println("Percentage of accuracy with 30 mails as training data:\n"+kb30.predictDataset("Input Data v2"));
        out.println("30\t"+kb30.predictDataset("Input Data v2"));

        NaiveBayes kb50=new NaiveBayes();
        kb50.train("Training Data\\50");
        System.out.println("Percentage of accuracy with 50 mails as training data:\n"+kb50.predictDataset("Input Data v2"));
        out.println("50\t"+kb50.predictDataset("Input Data v2"));

        NaiveBayes kb80=new NaiveBayes();
        kb80.train("Training Data\\80");
        System.out.println("Percentage of accuracy with 80 mails as training data:\n"+kb80.predictDataset("Input Data v2"));
        out.println("80\t"+kb80.predictDataset("Input Data v2"));

        NaiveBayes kb100=new NaiveBayes();
        kb100.train("Training Data\\100");
        System.out.println("Percentage of accuracy with 100 mails as training data:\n"+kb100.predictDataset("Input Data v2"));
        out.println("100\t"+kb100.predictDataset("Input Data v2"));

        NaiveBayes kb150=new NaiveBayes();
        kb150.train("Training Data\\150");
        System.out.println("Percentage of accuracy with 150 mails as training data:\n"+kb150.predictDataset("Input Data v2"));
        out.println("150\t"+kb150.predictDataset("Input Data v2"));

        NaiveBayes kb200=new NaiveBayes();
        kb200.train("Training Data\\200");
        System.out.println("Percentage of accuracy with 200 mails as training data:\n"+kb200.predictDataset("Input Data v2"));
        out.println("200\t"+kb200.predictDataset("Input Data v2"));

        NaiveBayes kb250=new NaiveBayes();
        kb250.train("Training Data\\250");
        System.out.println("Percentage of accuracy with 250 mails as training data:\n"+kb250.predictDataset("Input Data v2"));
        out.println("250\t"+kb250.predictDataset("Input Data v2"));

        out.flush();
        out.close();

    }
    
    
}
