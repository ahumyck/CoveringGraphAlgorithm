package com.company;

import com.company.generators.BinaryGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileNotFoundException;


@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) throws FileNotFoundException {
//        SpringApplication.run(DemoApplication.class, args);
//        Graph graph = GraphParser
//                .parseFile("C:\\Users\\Илья\\Desktop\\labs\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\graph_test.txt");
//        System.out.println(graph);
        BinaryGenerator generator = new BinaryGenerator(30,9);
        long start = System.currentTimeMillis();
        long next = 0;
        while (generator.hasNext()){
            next = generator.next();
            //System.out.println(generator.next());
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(next);


    }
}
