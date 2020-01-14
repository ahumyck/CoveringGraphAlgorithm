package com.company;

import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import com.company.entities.Coefficient;
import com.company.entities.Graph;
import com.company.genetic.Genetic;
import com.company.genetic.universe.Galaxy;
import com.company.parsers.GraphParser;
import com.company.services.builders.coefficientsBuilder.LinearCoefficientsBuilder;
import com.company.services.builders.galaxyBuilders.GalaxyDTOBuilderByMap;
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
import java.util.concurrent.atomic.AtomicLong;
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
        int vert_count = 100;
        int tests = 16;
        for (int i = 0; i < tests; i++) {
            graphs.add(GraphParser
                .parseFile(GraphGenerator.TEMPLATE_FILEPATH + "test_graph_" + i + ".txt"));
//            graphs.add(GraphGenerator.generate(vert_count, 100, 1000, 2, 200));
//            graphs.add(GraphGenerator.generate(100, 2, 100));

//            graphs.add(GraphGenerator.gnerate1(100));
        }
        System.out.println("Generating finish.");


        AtomicLong atomicLongGreedy = new AtomicLong();
        AtomicLong atomicLongGenetic = new AtomicLong();
        AtomicInteger genetic = new AtomicInteger();
        AtomicDouble geneticSum = new AtomicDouble();
        AtomicInteger greedy = new AtomicInteger();
        AtomicDouble greedySum = new AtomicDouble();
        AtomicInteger equal = new AtomicInteger();
        Consumer<Graph> consumer = graph -> {
            Genetic gen = new Genetic(graph);
//                    .isMutation(false)
//                    .selectionSize(6)
//                    .solvesStuffingSize(2)
//                    .totalStuffingSize(6)
//                    .roundCount(200);
            long start = System.currentTimeMillis();
            Map<Integer, ArrayList<Integer>> min = gen.solve();
            long end = System.currentTimeMillis();
            atomicLongGenetic.addAndGet(end - start);
            double geneticMin = GreedyAlgorithm.calculate(min, graph);

            start = System.currentTimeMillis();
            ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graph).orderByWeight().getCoefficients();
            Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithm.solve(coefficients, graph.size());
            end = System.currentTimeMillis();
            atomicLongGreedy.addAndGet(end - start);
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
        graphs.forEach(consumer);
        logger.info("Graph size: " + vert_count);
        logger.info("greedy average: " + (double)atomicLongGreedy.get()/tests);
        logger.info("genetic average: " + (double)atomicLongGenetic.get()/tests);
        logger.info("Genetic: " + genetic + " Greedy: " + greedy + " Equal: " + equal);
        logger.info("Genetic wins: " + geneticSum.get() / genetic.get());
        logger.info("Greedy wins: " + greedySum.get() / greedy.get() + '\n');
        logger.info("\n");
    }

    public static void main3(String[] args){
        Graph graph = GraphGenerator.generate(18, 10, 20, 10, 100);
        ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graph).orderByWeight().getCoefficients();
        GreedyAlgorithm.setMaximumStars(8);
        Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithm.solve(coefficients, graph.size());
        Galaxy solution = new GalaxyDTOBuilderByMap().build(solve, graph);
        System.out.println(solution);
    }
}
