package com.company;

import java.util.List;

public class Graph
{
    private List<Vertex> vertices;

    private EdgeMatrix edgeMatrix;

    public Graph(List<Vertex> vertices, EdgeMatrix edgeMatrix)
    {
        this.vertices = vertices;
        this.edgeMatrix = edgeMatrix;
    }
}
