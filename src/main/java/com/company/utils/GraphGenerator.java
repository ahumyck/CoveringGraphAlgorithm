package com.company.utils;

import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator
{
    private static final Random VERTEX_WEIGHT_RANDOM = new Random();
    private static final Random EDGE_WEIGHT_RANDOM = new Random();


    public static Graph generate(int vertexCount,
                                 int minVertexWeight, int maxVertexWeight,
                                 int minEdgeWeight, int maxEdgeWeight)
    {
        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++)
        {
            vertices.add(new Vertex(i, VERTEX_WEIGHT_RANDOM.nextInt(maxVertexWeight - minVertexWeight) + minVertexWeight));
        }
        EdgeMatrix matrix = new EdgeMatrix(vertexCount);
        for (int i = 0; i < vertexCount ; i++) {
            for (int j = 0; j < vertexCount; j++) {
                    matrix.setCell(i,j, EDGE_WEIGHT_RANDOM.nextInt(maxEdgeWeight - minEdgeWeight) + minEdgeWeight);
                    if(i == j){
                        matrix.setCell(i,j,0);
                    }
            }
        }
        return new Graph(vertices, matrix);
    }

}