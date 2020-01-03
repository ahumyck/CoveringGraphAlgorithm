package com.company;

import com.company.entities.Coefficient;
import com.company.entities.Graph;
import com.company.services.builders.coefficientsBuilder.LinearCoefficientsBuilder;
import com.company.services.builders.galaxyBuilders.GalaxyDTOBuilderByMap;
import com.company.universe.Galaxy;
import com.company.universe.galaxyMutator.FromPlanetToStarMutator;
import com.company.universe.galaxyMutator.OptimalPlanetDistributorMutator;
import com.company.utils.GraphGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Map;


@SpringBootApplication
public class DemoApplication
{
    public static void main(String[] args)
    {
//        SpringApplication.run(DemoApplication.class, args);
//        Graph graph = GraphParser
//                .parseFile("C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\16x16graph.txt");
//        int n = graph.size();
        Graph graph = GraphGenerator.generate(16,10,100);
        System.out.println(graph);

        int n = graph.size();
        ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graph).orderByWeight().getCoefficients();
        Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithmTest.solve(coefficients, n);
        System.out.println("greedy: " + solve);
        System.out.println(BruteForceAlgorithmTest.calculate(solve,graph));
        Galaxy galaxy = new GalaxyDTOBuilderByMap().build(solve, graph);
        System.out.println(galaxy);
        new FromPlanetToStarMutator().mutate(galaxy,graph);
        galaxy.orderByWeight();
        System.out.println(galaxy);
        new FromPlanetToStarMutator().mutate(galaxy,graph);
        galaxy.orderByWeight();
        System.out.println(galaxy);



//        List<Galaxy> bestGalaxies = new ArrayList<>();
//        GalaxyPool pool = new GalaxyPool(graph,5000);
//        pool.orderByWeight();
//        AverageMutator averageMutator = new AverageMutator();
//        bestGalaxies.add(pool.getBestGalaxy());
//        for(int i = 0 ; i < 500; i++){
//            pool.mutate(averageMutator);
//            bestGalaxies.add(pool.getBestGalaxy());
//        }
//        System.out.println(bestGalaxies.stream().min(Comparator.comparingInt(Galaxy::getWeight)).get());
//
    }
}
