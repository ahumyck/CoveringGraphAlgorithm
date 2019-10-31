package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;


@SpringBootApplication
public class DemoApplication {

    private static final String graph = "C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\graph.txt";
    private static final String graphAsString =
            "16\n" +
            "-1 -1 1 1 1 1 -1 1 -1 -1 1 1 1 1 -1 1\n" +
            "0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1\n" +
            "0 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1\n" +
            "1 0 1 1 1 1 1 1 1 0 1 1 1 1 1 1\n" +
            "1 1 0 1 1 1 1 1 1 1 0 1 1 1 1 1\n" +
            "1 1 1 0 1 1 1 1 1 1 1 0 1 1 1 1\n" +
            "1 1 1 1 0 1 1 1 1 1 1 1 0 1 1 1\n" +
            "1 1 1 1 1 0 1 1 1 1 1 1 1 0 1 1\n" +
            "1 1 1 1 1 1 0 1 1 1 1 1 1 1 0 1\n" +
            "1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 0";

    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(DemoApplication.class, args);
    }
}
