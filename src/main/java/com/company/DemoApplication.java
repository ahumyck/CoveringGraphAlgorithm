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
        Graph graphSized8 = GraphParser
                .parseFile("C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\8x8graph.txt");
        int n = graphSized8.size(); // 8*7
        ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graphSized8).orderByWeight().getCoefficients();
        long l = System.currentTimeMillis();
        BruteForceAlgorithmTest.solve(graphSized8, coefficients,4,n);
        System.out.println(System.currentTimeMillis() - l);
        Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithmTest.solve(coefficients, n);
        System.out.println(solve);
        System.out.println(BruteForceAlgorithmTest.calculate(solve,graphSized8));
    }
}
