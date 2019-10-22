package com.company;

import java.util.List;


public class Vertex {
    private int id;
    private int weight;

    private List<Edge> edges;

    public Vertex(int id, int weight, List<Edge> edges) {
        this.id = id;
        this.weight = weight;
        this.edges = edges;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public String toString() {
        return "Vertex id: " + this.id + " , weight: " + this.weight;
    }
}
