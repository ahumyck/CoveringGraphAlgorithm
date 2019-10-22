package com.company;

import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser {

    public static Graph parseGraph(String vertexFilename, String matrixFilename) throws FileNotFoundException,
            NoSuchElementException,IllegalArgumentException {
        List<Vertex> vertices = parseVertices(vertexFilename);
        EdgeMatrix matrix = parseMatrix(matrixFilename, vertices.size());

        return new Graph(vertices,matrix);
    }

    private static List<Vertex> parseVertices(String vertexFilename) throws FileNotFoundException, NoSuchElementException {
        Scanner vertexScanner = new Scanner(new FileReader(vertexFilename));
        List<Vertex> vertices = new ArrayList<>();
        int id = 0;
        while(vertexScanner.hasNext()){
            vertices.add(new Vertex(id,Integer.parseInt(vertexScanner.next())));
            id++;
        }

        return vertices;
    }

    private static EdgeMatrix parseMatrix(String matrixFilename,int verticesSize) throws FileNotFoundException,
            NoSuchElementException, IllegalArgumentException {

        Scanner matrixScanner = new Scanner(new FileReader(matrixFilename));
        int size = Integer.parseInt(matrixScanner.next());
        if(size != verticesSize){
            throw new IllegalArgumentException("size are not match");
        }
        EdgeMatrix matrix = new EdgeMatrix(size);

        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size; j++) {
               matrix.setCell(i,j,Integer.parseInt(matrixScanner.next()));
            }
        }
        return matrix;
    }
}
