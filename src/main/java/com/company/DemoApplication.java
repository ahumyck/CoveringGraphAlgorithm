package com.company;

import com.company.entities.Coefficient;
import com.company.entities.Graph;
import com.company.parsers.GraphParser;
import com.company.services.coefficientsBuilder.LinearCoefficientsBuilder;
import com.company.utils.GraphGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;


@SpringBootApplication
public class DemoApplication
{
    public static void main(String[] args) throws FileNotFoundException
    {

//        SpringApplication.run(DemoApplication.class, args);
//        Graph graphSized8 = GraphParser
//                .parseFile("C:\\Users\\ILYA\\nauchka\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\test_graph.txt");
//        System.out.println("from file: " + graphSized8);
//        Graph graphSized64 = GraphGenerator.generate(100, 10000,50);
//        GraphGenerator.writeToFile(graphSized64);
//        int n = graphSized64.size(); // 8*7
//        ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graphSized64).orderByWeight().getCoefficients();
////        System.out.println("Graph: " + graphSized64);
////        long l = System.currentTimeMillis();
////        System.out.println(System.currentTimeMillis() - l);
////        System.out.println("Coefficients:" + coefficients);
//        long startTime1 = System.currentTimeMillis();
//        Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithmTest.solve(coefficients, n);
//        long endTime1 = System.currentTimeMillis();
//        long startTime2 = System.currentTimeMillis();
//        Map<Coefficient, ArrayList<Coefficient>> mySolve = GreedyTest.solve(coefficients, 5000, n);
//        long endTime2 = System.currentTimeMillis();
//        System.out.println("my");
//        mySolve.forEach((key, value) ->
//        {
//            System.out.print(key.getStar() + "=[");
//            value.forEach(x -> System.out.print(x.getSatellite() + ","));
//            System.out.print("]");
//            System.out.println();
//        });
//        System.out.println("result: " + GreedyTest.calculate(mySolve));
//        System.out.println("my");
//
//        System.out.println("lis greedy: " + (endTime2 - startTime2));
//        System.out.println("perm greedy: " + (endTime1 - startTime1));
//
//        System.out.println(solve);
//        System.out.println(GreedyAlgorithmTest.calculate(solve, graphSized64));
    }
}
