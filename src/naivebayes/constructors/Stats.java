/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naivebayes.constructors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dmilas
 */
public class Stats {
    //No. of Observations 
    public int n;

    //No. CategoryCounts
    public Map<String, Integer> categoryCounts;

    //No. JointCounts
    public Map<String, Map<String, Integer>> categoryJointCount;

    public Stats() {
        n = 0;
        categoryCounts = new HashMap<>();
        categoryJointCount = new HashMap<>();

    }

    public Stats(List<Document> dataset) {
        n = dataset.size();
        categoryCounts = calcCategoryCounts(dataset);
        categoryJointCount = calcJointCount(dataset);
    }

    public HashMap<String, Integer> calcCategoryCounts(List<Document> dataset) {
        HashMap<String, Integer> categoryCount = new HashMap<>();

        dataset.forEach((n) -> {
                    if (n.category.toLowerCase().equals("ham")) {
                        categoryCount.putIfAbsent(n.category, 1);
                        if (categoryCount.containsKey(n.category))
                            categoryCount.replace(n.category, categoryCount.get(n.category) + 1);
                    } else if (n.category.toLowerCase().equals("spam")) {
                        categoryCount.putIfAbsent(n.category, 1);
                        if (categoryCount.containsKey(n.category))
                            categoryCount.replace(n.category, categoryCount.get(n.category) + 1);
                    }
                }
        );

        return categoryCount;
    }

    public HashMap<String, Map<String, Integer>> calcJointCount(List<Document> dataset) {
        HashMap<String, Map<String, Integer>> categoryJointCount = new HashMap<>();

        dataset.forEach((n) -> {
                    categoryJointCount.putIfAbsent(n.category, n.tokens);
                    if (categoryJointCount.containsKey(n.category)) {
                        Map<String, Integer> temp = categoryJointCount.get(n.category);
                        for (Map.Entry<String, Integer> entry : n.tokens.entrySet()) {
                            temp.putIfAbsent(entry.getKey(), entry.getValue());
                            if (temp.containsKey(entry.getKey()))
                                temp.replace(entry.getKey(), temp.get(entry.getKey()) + entry.getValue());
                        }

                    }

                }
        );

        return categoryJointCount;
    }


}
