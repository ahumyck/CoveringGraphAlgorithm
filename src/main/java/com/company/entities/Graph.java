package com.company.entities;

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

    public List<Vertex> getVertices()
    {
        return vertices;
    }

    public EdgeMatrix getEdgeMatrix()
    {
        return edgeMatrix;
    }
}
