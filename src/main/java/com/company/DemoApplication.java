package com.company;

import com.company.entities.Graph;
import com.company.generators.BinaryGenerator;
import com.company.parsers.GraphParser;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileNotFoundException;
import java.math.BigInteger;


@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) throws FileNotFoundException {
//        SpringApplication.run(DemoApplication.class, args);
//        Graph graph = GraphParser
//                .parseFile("C:\\Users\\Илья\\Desktop\\labs\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\graph_test.txt");
//        System.out.println(graph);
        BinaryGenerator generator = new BinaryGenerator(5,2);
        while(generator.hasNext()){
            System.out.println(generator.next());
        }

    }
}
