package com.company;


import com.company.entities.Graph;
import com.company.generators.StarGenerator;
import com.company.services.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BruteForceManager
{
    private CacheService cacheService;

    @Autowired
    public BruteForceManager(CacheService cacheService)
    {
        this.cacheService = cacheService;
    }

    public StarPlan bruteForce(Graph graph, int starsCount)
    {
        StarGenerator generator = new StarGenerator(starsCount,graph.getVertices().size());
        List<Integer> starsCombination; //todo: base plan?
        ArrayList<List<Integer>> allGenerations = new ArrayList<>(); //todo: calculate aprox size

        long start = System.currentTimeMillis();
        while((starsCombination = generator.next()) != null){
            allGenerations.add(starsCombination);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time stars generator: " + (end - start));

//        StarPlan starPlan = allGenerations.parallelStream().unordered()
//                .map(x -> new SmartMatrixWrapper(x, graph).calculateMinimizationFunction())
//                .min(Comparator.comparing(StarPlan::getCost))
//                .get();
        StarPlan starPlan = new SmartMatrixWrapper(allGenerations.get(0), graph).calculateMinimizationFunction();
        StarPlan newStarPlan;
        int i = 0;
        cacheService.putStarPlan(i, starPlan);
        i++;
        for (List<Integer> list:  allGenerations){
            newStarPlan =  new SmartMatrixWrapper(list, graph).calculateMinimizationFunction();
            if(newStarPlan.getCost() < starPlan.getCost()){
                cacheService.putStarPlan(i, newStarPlan);
                i++;
                starPlan = newStarPlan;
            }
        }
        System.out.println("0:" + cacheService.getStarPlan(0));
        System.out.println(cacheService.getMap());
        return starPlan;
    }

}
