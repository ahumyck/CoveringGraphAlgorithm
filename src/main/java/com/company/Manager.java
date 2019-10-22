package com.company;


import com.company.entities.Graph;
import com.company.generators.StarGenerator;

import java.util.*;

public class Manager
{

    public static StarPlan bruteForce(Graph graph, int starsCount)
    {
        StarGenerator generator = new StarGenerator(starsCount,graph.getVertices().size());
        List<Integer> starsCombination; //todo: base plan?
        ArrayList<List<Integer>> allGenerations = new ArrayList<>(); //todo: calculate aprox size

        while((starsCombination = generator.next()) != null){
            allGenerations.add(starsCombination);
        }

        return allGenerations.parallelStream()
                .map(x -> new SmartMatrixWrapper(x, graph).calculateMinimizationFunction())
                .min(Comparator.comparing(StarPlan::getCost))
                .get();
    }

}
