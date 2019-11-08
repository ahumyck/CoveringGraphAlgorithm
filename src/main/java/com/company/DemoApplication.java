package com.company;

import com.company.entities.Coefficient;
import com.company.entities.Graph;
import com.company.generators.SatellitePermutationGeneratorUsingStars;
import com.company.parsers.GraphParser;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) throws FileNotFoundException {
//        SpringApplication.run(DemoApplication.class, args);
        Graph graph = GraphParser
                .parseFile("C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\graph_test.txt");
        AdditiveAlgorithmManager manager = new AdditiveAlgorithmManager();
        manager.solve(graph,23123);
    }
}
