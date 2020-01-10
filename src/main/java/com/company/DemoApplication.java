package com.company;

import com.company.entities.Coefficient;
import com.company.entities.Graph;
import com.company.genetic.Genetic;
import com.company.services.builders.coefficientsBuilder.LinearCoefficientsBuilder;
import com.company.utils.GraphGenerator;
import com.google.common.util.concurrent.AtomicDouble;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;


@SpringBootApplication
public class DemoApplication {
    public static void main1(String[] args) {
        GraphGenerator.createTestResources();
    }
    public static void main(String[] args) throws FileNotFoundException {
//        Graph graph = GraphParser
//                .parseFile("C:\\Users\\10ila\\nauchka\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\16x16graph.txt");
//        System.out.println("from file: " + graphSized8);
        List<Graph> graphs = new ArrayList<>();
        System.out.println("Start generate ...");
//        for (int i = 0; i < 4; i++) {
//            graphs.add(GraphGenerator.generate(50, 5000, 10000, 2, 100));
//        }
//        for (int i = 0; i < 4; i++) {
//            graphs.add(GraphGenerator.generate(50, 2, 100, 2, 100));
//        }
//        for (int i = 0; i < 4; i++) {
//            graphs.add(GraphGenerator.generate(100, 5000, 10000, 2, 100));
//        }
//        for (int i = 0; i < 8; i++) {
//            graphs.add(GraphGenerator.generate(200, 5000, 10000, 2, 100));
//        }
        for (int i = 0; i < 24; i++) {
//            graphs.add(GraphParser
//                .parseFile(GraphGenerator.TEMPLATE_FILEPATH + "test_graph_" + i + ".txt"));
            graphs.add(GraphGenerator.generate(100, 9550, 10000, 2, 100));
//            graphs.add(GraphGenerator.generate(100, 2, 100));

//            graphs.add(GraphGenerator.gnerate1(100));
        }
//        for (int i = 0; i < 4; i++) {
//            graphs.add(GraphGenerator.generate(200, 2, 100, 2, 100));
//        }
//        for (int i = 0; i < 4; i++) {
//            graphs.add(GraphGenerator.generate(500, 5000, 10000, 2, 100));
//        }
//        for (int i = 0; i < 4; i++) {
//            graphs.add(GraphGenerator.generate(500, 2, 100, 2, 100));
//        }
        System.out.println("Generating finish.");

        AtomicInteger genetic = new AtomicInteger();
        AtomicDouble geneticSum = new AtomicDouble();
        AtomicInteger greedy = new AtomicInteger();
        AtomicDouble greedySum = new AtomicDouble();
        AtomicInteger equal = new AtomicInteger();
//        final double[] equalSum = new double[1];
        Consumer<Graph> consumer = graph -> {
//            int i = atomicInteger.incrementAndGet();
//            System.out.println("Start test ...");
            Map<Integer, ArrayList<Integer>> min = new Genetic(graph).solve();
            double geneticMin = GreedyAlgorithmTest.calculate(min, graph);

            ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graph).orderByWeight().getCoefficients();
            Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithmTest.solve(coefficients, graph.size());
            double greedyMin = GreedyAlgorithmTest.calculate(solve, graph);
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
//        graphs.stream().forEach(consumer);
        System.out.println("parallel");
        graphs.parallelStream().forEach(consumer);
        System.out.println("\nGenetic: " + genetic + " Greedy: " + greedy + " Equal: " + equal);
        System.out.println("Genetic wins: " + geneticSum.get() / genetic.get());
        System.out.println("Greedy wins: " + greedySum.get() / greedy.get());
    }
}
