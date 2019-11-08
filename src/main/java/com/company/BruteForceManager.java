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
        List<Integer> starsCombination;

        StarPlan starPlan = new SmartMatrixWrapper(generator.next(), graph).calculateMinimizationFunction();
        StarPlan newStarPlan;
        int i = 0;
        cacheService.putStarPlan(i, starPlan);
        i++;
        while((starsCombination = generator.next()) != null){
            newStarPlan =  new SmartMatrixWrapper(starsCombination, graph).calculateMinimizationFunction();
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
