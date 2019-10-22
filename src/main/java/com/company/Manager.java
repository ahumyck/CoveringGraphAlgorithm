package com.company;


import com.company.generators.StarGenerator;

import java.util.*;

public class Manager
{

    public static StarPlan bruteForce(List<Vertex> vertexList, int starsCount)
    {
        StarGenerator generator = new StarGenerator(starsCount,vertexList.size());
        List<Integer> starsCombination; //todo: base plan?
        ArrayList<List<Integer>> allGenerations = new ArrayList<>(); //todo: calculate aprox size

        while((starsCombination = generator.next()) != null){
            allGenerations.add(starsCombination);
        }

//        allGenerations.forEach(System.out::println);

        return allGenerations.parallelStream()
                .map(x -> new SmartMatrixWrapper(x, vertexList).calculateMinimizationFunction())
                .min(Comparator.comparing(StarPlan::getCost))
                .get();
    }

}
