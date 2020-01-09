package com.company.utils;

import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {
    private static final Random WEIGHT_RANDOM = new Random();
    private static String TEMPLATE_FILEPATH = "C:\\Users\\10ila\\nauchka\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\";
    private static String TEMPLATE_FILENAME = "test_graph_{0}.txt";

    public static void createTestResourses() {
        for (int i = 0; i < 1; i++) {
            String resultFilename = MessageFormat.format(TEMPLATE_FILENAME, i);
            String resultFilepath = TEMPLATE_FILEPATH + resultFilename;
            writeToFile(
                    resultFilepath,
                    generate(200, 1000, 2000, 10, 50)
            );
            System.out.println("Generate Graph and write to file with name=[" + resultFilename + "].");

        }
    }

    public static Graph generate(int vertexCount, int minVertexWEight, int maxVertexWeight, int minEdgeWEight, int maxEdgeWeight) {
        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            vertices.add(new Vertex(i, nextInt(minVertexWEight, maxVertexWeight)));
        }
        EdgeMatrix matrix = new EdgeMatrix(vertexCount);
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                matrix.setCell(i, j, nextInt(minEdgeWEight, maxEdgeWeight));
            }
        }
        return new Graph(vertices, matrix);
    }

    public static Graph generate(int vertexCount, int minEdgeWEight, int maxEdgeWeight) {
        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            vertices.add(new Vertex(i, 1));
        }
        EdgeMatrix matrix = new EdgeMatrix(vertexCount);
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                matrix.setCell(i, j, nextInt(minEdgeWEight, maxEdgeWeight));
            }
        }
        return new Graph(vertices, matrix);
    }

    public static Graph gnerate1(int vertexCount) {
        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            if (i % 5 == 0)
                vertices.add(new Vertex(i, nextInt(1000, 2000)));
            else vertices.add(new Vertex(i, nextInt(4000, 6000)));
        }
        EdgeMatrix matrix = new EdgeMatrix(vertexCount);
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (i % 7 == 0)
                    matrix.setCell(i, j, nextInt(10, 20));
                else matrix.setCell(i, j, nextInt(80, 100));
            }
        }
        return new Graph(vertices, matrix);
    }

    public static void writeToFile(String filepath, Graph graph) {
        File file = new File(filepath);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(String.valueOf(graph.size()));
            fileWriter.write(' ');
            for (Vertex vertex : graph.getVertices()) {
                fileWriter.write(String.valueOf(vertex.getWeight()));
                fileWriter.write(' ');
            }
            EdgeMatrix edgeMatrix = graph.getEdgeMatrix();
            for (int i = 0; i < edgeMatrix.getSize(); i++) {
                for (int j = 0; j < edgeMatrix.getSize(); j++) {
                    fileWriter.write(String.valueOf(edgeMatrix.getCell(i, j)));
                    fileWriter.write(' ');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int nextInt(int minValue, int maxValue) {
        return WEIGHT_RANDOM.nextInt(maxValue - minValue) + minValue;
    }
}
