package com.company;

import com.company.entities.Graph;
import com.company.parsers.GraphParser;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileNotFoundException;


@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) throws FileNotFoundException {
//        SpringApplication.run(DemoApplication.class, args);
        Graph graph = GraphParser
                .parseFile("C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\graph_test.txt");
    }
}
