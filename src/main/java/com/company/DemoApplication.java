package com.company;

import com.company.entities.Coefficient;
import com.company.entities.Graph;
import com.company.services.builders.coefficientsBuilder.LinearCoefficientsBuilder;
import com.company.services.builders.galaxyBuilders.GalaxyDTOBuilderByMap;
import com.company.universe.Galaxy;
import com.company.universe.GalaxyPool;
import com.company.universe.galaxyMutator.CombinationMutator;
import com.company.universe.galaxyMutator.Mutator;
import com.company.utils.GraphGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;


@SpringBootApplication
public class DemoApplication
{
    public static void main(String[] args){
//        SpringApplication.run(DemoApplication.class, args);
//        Graph graph = GraphParser
//                .parseFile("C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\16x16graph.txt");
//        int n = graph.size();
        Graph graph = GraphGenerator.generate(32,
                1000,10000,
                10, 100);
        System.out.println(graph);

        int n = graph.size();
        ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graph).orderByWeight().getCoefficients();
        Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithmTest.solve(coefficients, n);
        Galaxy build = new GalaxyDTOBuilderByMap().build(solve, graph);
        build.orderByWeight();
        System.out.println(build);


        List<Galaxy> bestGalaxies = new ArrayList<>();
        GalaxyPool pool = new GalaxyPool(graph,9);
        pool.addGalaxy(build);
        Mutator mutator = new CombinationMutator();
        bestGalaxies.add(pool.getBestGalaxy());
        for(int i = 0 ; i < 100; i++){
            pool.mutate(mutator);
            bestGalaxies.add(pool.getBestGalaxy());
//            System.out.print(i + " ");
//            if(i % 25 == 0) System.out.println();
        }
        System.out.println();
        AtomicReference<Galaxy> best = new AtomicReference<>();
        bestGalaxies.stream()
                .min(Comparator.comparingLong(Galaxy::getWeight))
                .ifPresent(best::set);

        long diff = build.getWeight() - best.get().getWeight();
        System.out.println(best);
        if(diff > 0){
            System.out.println("win " + diff);
        }
        else if(diff < 0){
            System.out.println("lose " + (-diff));
        }
        else{
            System.out.println("same");
        }

    }
}
