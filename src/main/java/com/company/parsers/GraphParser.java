package com.company.parsers;

import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;

import org.jetbrains.annotations.NotNull;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GraphParser {

    public static Graph parseFile(String filename) throws FileNotFoundException,
            NoSuchElementException {
        Scanner fileScanner = new Scanner(new FileReader(filename));
        return parse(fileScanner);
    }

    public static Graph parseString(String inputString) throws NoSuchElementException {
        Scanner stringScanner = new Scanner(inputString);
        return parse(stringScanner);
    }

    private static Graph parse(Scanner scanner) throws  NoSuchElementException {
        int size = Integer.parseInt(scanner.next());

        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            vertices.add(new Vertex(i,Integer.parseInt(scanner.next())));
        }

        EdgeMatrix matrix = new EdgeMatrix(size);

        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size; j++) {
                matrix.setCell(i,j,Integer.parseInt(scanner.next()));
            }
        }
        return new Graph(vertices,matrix);
    }
}
