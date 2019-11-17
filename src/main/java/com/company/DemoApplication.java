package com.company;

import com.company.entities.Coefficient;
import com.company.entities.Graph;
import com.company.generators.BinaryGenerator;
import com.company.parsers.GraphParser;
import com.company.services.coefficientsBuilder.LinearCoefficientsBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) throws FileNotFoundException {
//        SpringApplication.run(DemoApplication.class, args);
        Graph graph = GraphParser
                .parseFile("C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\graph_test.txt");
//        ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder(graph).orderByWeight().getCoefficients();
//        coefficients.forEach(x-> System.out.println(x.fullInfo()));
       GreedyAlgorithmTest.solve(new LinearCoefficientsBuilder(graph).orderByWeight().getCoefficients(),2);
    }
}
