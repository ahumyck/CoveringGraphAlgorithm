package com.company;

import com.company.entities.Coefficient;
import com.company.entities.Graph;
import com.company.genetic.Genetic;
import com.company.genetic.universe.Galaxy;
import com.company.services.builders.coefficientsBuilder.LinearCoefficientsBuilder;
import com.company.services.builders.galaxyBuilders.GalaxyBuilderByMap;
import com.company.utils.GraphGenerator;
import com.google.common.util.concurrent.AtomicDouble;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;


@SpringBootApplication
public class DemoApplication {

    private static final  Logger logger = LoggerFactory.getLogger(DemoApplication.class);


    public static void main1(String[] args) {
        GraphGenerator.createTestResources(16);
    }

    public static void main(String[] args) throws FileNotFoundException {
//        Graph graph = GraphParser
//                .parseFile("C:\\Users\\10ila\\nauchka\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\16x16graph.txt");
//        System.out.println("from file: " + graphSized8);
        List<Graph> graphs = new ArrayList<>();
        System.out.println("Start generate ...");
        for (int i = 0; i < 4; i++) {
//            graphs.add(GraphParser
//                .parseFile(GraphGenerator.TEMPLATE_FILEPATH + "test_graph_" + i + ".txt"));
            graphs.add(GraphGenerator.generate(100, 9550, 10000, 2, 200));
//            graphs.add(GraphGenerator.generate(100, 2, 100));

//            graphs.add(GraphGenerator.gnerate1(100));
        }
        System.out.println("Generating finish.");


        AtomicInteger genetic = new AtomicInteger();
        AtomicDouble geneticSum = new AtomicDouble();
        AtomicInteger greedy = new AtomicInteger();
        AtomicDouble greedySum = new AtomicDouble();
        AtomicInteger equal = new AtomicInteger();
        Consumer<Graph> consumer = graph -> {
            Map<Integer, ArrayList<Integer>> min = new Genetic(graph)
//                    .isMutation(false)
//                    .selectionSize(6)
//                    .solvesStuffingSize(2)
//                    .totalStuffingSize(6)
//                    .roundCount(200)
                    .solve();
            double geneticMin = GreedyAlgorithm.calculate(min, graph);

            ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graph).orderByWeight().getCoefficients();
            Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithm.solve(coefficients, graph.size());
            double greedyMin = GreedyAlgorithm.calculate(solve, graph);
            System.out.println("Genetic solve: " + min);
            System.out.println("Greedy solve: " + solve);

            System.out.println("Greedy result " + ": " + (int) greedyMin);
            if (geneticMin == greedyMin) {
                System.out.println("Size: " + graph.size() + " Genetic equal Greedy " + greedyMin);
                equal.incrementAndGet();
            } else if (geneticMin < greedyMin) {
                double k = greedyMin / geneticMin;
                System.out.println("Size: " + graph.size() + " Genetic win " + k);
                genetic.incrementAndGet();
                geneticSum.addAndGet(k);
            } else {
                double k = geneticMin / greedyMin;
                System.out.println("Size: " + graph.size() + " Greedy win " + k);
                greedy.incrementAndGet();
                greedySum.addAndGet(k);
            }

        };
        System.out.println("parallel");
        graphs.parallelStream().forEach(consumer);
        logger.info("Genetic: " + genetic + " Greedy: " + greedy + " Equal: " + equal);
        logger.info("Genetic wins: " + geneticSum.get() / genetic.get());
        logger.info("Greedy wins: " + greedySum.get() / greedy.get() + '\n');
    }

    public static void main3(String[] args){
        Graph graph = GraphGenerator.generate(18, 10, 20, 10, 100);
        ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graph).orderByWeight().getCoefficients();
        GreedyAlgorithm.setMaximumStars(8);
        Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithm.solve(coefficients, graph.size());
        Galaxy solution = new GalaxyBuilderByMap().build(solve, graph);
        System.out.println(solution);
    }
}
