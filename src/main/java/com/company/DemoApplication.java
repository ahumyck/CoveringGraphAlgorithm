package com.company;

import com.company.entities.Coefficient;
import com.company.entities.Graph;
import com.company.generators.BinaryGenerator;
import com.company.generators.CombinationBinaryGeneratorLong;
import com.company.parsers.GraphParser;
import com.company.services.coefficientsBuilder.LinearCoefficientsBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) throws FileNotFoundException {
//        SpringApplication.run(DemoApplication.class, args);
        Graph graph = GraphParser
                .parseFile("C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\6x6graph.txt");
        int n = graph.size(); // 8*7
        ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graph).orderByWeight().getCoefficients();
        Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithmTest.solve(coefficients, n);
        System.out.println(solve);
        System.out.println(BruteForceAlgorithmTest.calculate(solve,graph));
    }
}
