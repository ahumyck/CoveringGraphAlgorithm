package com.company;

import com.company.entities.Coefficient;
import com.company.entities.Graph;
import com.company.services.builders.coefficientsBuilder.LinearCoefficientsBuilder;
import com.company.services.builders.galaxyBuilders.GalaxyDTOBuilderByMap;
import com.company.universe.Galaxy;
import com.company.universe.GalaxyPool;
import com.company.universe.galaxyMutator.CombinationMutator;
import com.company.universe.galaxyMutator.FromPlanetToStarMutators.HeavyConnection.FromPlanetToStarMutatorHeavyConnection;
import com.company.universe.galaxyMutator.FromPlanetToStarMutators.HeavyConnection.RelocationHeavyConnection;
import com.company.universe.galaxyMutator.Mutator;
import com.company.utils.GraphGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@SpringBootApplication
public class DemoApplication
{
    public static void main(String[] args){
//        SpringApplication.run(DemoApplication.class, args);
//        Graph graph = GraphParser
//                .parseFile("C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\16x16graph.txt");
//        int n = graph.size();
        Graph graph = GraphGenerator.generate(50,10000,50);
        System.out.println(graph);

        int n = graph.size();
        ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graph).orderByWeight().getCoefficients();
        Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithmTest.solve(coefficients, n);
        Galaxy build = new GalaxyDTOBuilderByMap().build(solve, graph);
        build.orderByWeight();
        System.out.println(build);

        List<Galaxy> bestGalaxies = new ArrayList<>();
        GalaxyPool pool = new GalaxyPool(graph,999);
        pool.addGalaxy(build);
        Mutator mutator = new FromPlanetToStarMutatorHeavyConnection();
        bestGalaxies.add(pool.getBestGalaxy());
        for(int i = 0 ; i < 10; i++){
//            System.out.print(i + " ");
            pool.mutate(mutator);
            bestGalaxies.add(pool.getBestGalaxy());
        }
        System.out.println();

        List<Galaxy> collect = bestGalaxies.stream()
                .sorted(Comparator.comparingInt(Galaxy::getWeight))
                .limit(10)
                .collect(Collectors.toList());

        Galaxy galaxy = collect.get(0);
        System.out.println();

        int diff = build.getWeight() - galaxy.getWeight();
        System.out.println(galaxy);
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
