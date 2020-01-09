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
import com.company.universe.galaxyMutator.OptipalPlanetDistributor.OptimalPlanetDistributorMutator;
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
        Graph graph = GraphGenerator.generate(100,
                10,100,
                    10, 50);
        System.out.println(graph);

        int n = graph.size();
        ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graph).orderByWeight().getCoefficients();
        Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithmTest.solve(coefficients, n);
        Galaxy galaxy = new GalaxyDTOBuilderByMap().build(solve, graph);
        galaxy.orderByWeight();
        System.out.println(galaxy);

        Galaxy mutate = new FromPlanetToStarMutatorHeavyConnection().mutate(galaxy,graph);

//        List<Galaxy> bestGalaxies = new ArrayList<>();
//        GalaxyPool pool = new GalaxyPool(graph,1000);
//        pool.addGalaxy(galaxy);
//        Mutator mutator = new CombinationMutator();
//        bestGalaxies.add(pool.getBestGalaxy());
//        for(int i = 0 ; i < 10; i++){
//            pool.mutate(mutator);
//            bestGalaxies.add(pool.getBestGalaxy());
//        }
//        System.out.println();
//        AtomicReference<Galaxy> best = new AtomicReference<>();
//        bestGalaxies.stream()
//                .min(Comparator.comparingLong(Galaxy::getWeight))
//                .ifPresent(best::set);
//
        long diff = galaxy.getWeight() - mutate.getWeight();
        System.out.println(mutate);
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
