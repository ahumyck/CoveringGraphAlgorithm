package com.company;

import com.company.entities.Coefficient;
import com.company.entities.Graph;
import com.company.parsers.GraphParser;
import com.company.services.coefficientsBuilder.LinearCoefficientsBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;


@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) throws FileNotFoundException {
//        SpringApplication.run(DemoApplication.class, args);
        Graph graph = GraphParser
                .parseFile("C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\graph_test.txt");
        ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graph).orderByWeight().getCoefficients();
        coefficients.forEach(x-> System.out.println(x.fullInfo()));
        Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithmTest.solve(new LinearCoefficientsBuilder().build(graph).orderByWeight().getCoefficients(), 6);
        System.out.println(solve);
    }
}
