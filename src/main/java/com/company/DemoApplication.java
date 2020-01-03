package com.company;

import com.company.entities.Coefficient;
import com.company.entities.Graph;
import com.company.parsers.GraphParser;
import com.company.perm_generic_algorithm.RandomPermutationGenerator;
import com.company.perm_generic_algorithm.StarSatelliteDescriptionState;
import com.company.perm_generic_algorithm.StarSatellitePool;
import com.company.perm_generic_algorithm.StarSatellitePoolGenerator;
import com.company.services.coefficientsBuilder.LinearCoefficientsBuilder;
import com.company.services.graphBuilders.GraphDTOByDescriptionStateBuilder;
import com.company.utils.GraphGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;


@SpringBootApplication
public class DemoApplication
{
    public static void main(String[] args) throws Exception
    {
//        SpringApplication.run(DemoApplication.class, args);
//        Graph graph = GraphParser
//                .parseFile("C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\16x16graph.txt");
//        int n = graph.size();
        Graph graph = GraphGenerator.generate(128,100,100);
        int n = graph.size();
        ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graph).orderByWeight().getCoefficients();


        List<StarSatelliteDescriptionState> states = new ArrayList<>();
        int poolSize = 1000;
        int howManyStates = 666;
        StarSatellitePool pool = new StarSatellitePool(graph,poolSize);
        pool.calculateWeights().orderByWeight();
        for(int i = 0 ; i < 1000; i++){
            System.out.println(i);
            StarSatellitePool newPool = new StarSatellitePool(graph,pool.getFirstStates(howManyStates),poolSize).calculateWeights().orderByWeight();
            states.add(newPool.getBestState());
            pool = newPool;
        }
        StarSatelliteDescriptionState state = states.stream().min(Comparator.comparingInt(StarSatelliteDescriptionState::getWeight)).get();
        Map<Integer, ArrayList<Integer>> gen = new GraphDTOByDescriptionStateBuilder().build(state);

        Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithmTest.solve(coefficients, n);
        System.out.println("greedy: " + solve);
        System.out.println(BruteForceAlgorithmTest.calculate(solve,graph));
        System.out.println("genetic: " + gen);
        System.out.println(BruteForceAlgorithmTest.calculate(gen,graph));


    }
}
