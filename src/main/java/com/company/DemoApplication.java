package com.company;

import com.company.entities.Graph;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class DemoApplication {

    private static final String matrixFile = "C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\matrix.txt";
    private static final String vertexFile = "C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\vertexWeight.txt";


    public static void main(String[] args) throws Exception {

        Graph graph = Parser.parseGraph(vertexFile,matrixFile);
        System.out.println(graph);

        long start = System.currentTimeMillis();
        StarPlan starPlan = Manager.bruteForce(graph, 3);
        long end = System.currentTimeMillis();
        System.out.println(starPlan);
        System.out.println("Time: " + (end - start));

        //SpringApplication.run(DemoApplication.class, args);
    }
}
