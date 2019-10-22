package com.company.demo;

public class Edge {
    private int weight;
    private int predecessorId;
    private int successorId;

    public Edge(int weight, int predecessorId, int successorId) {
        this.weight = weight;
        this.predecessorId = predecessorId;
        this.successorId = successorId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPredecessorId() {
        return predecessorId;
    }

    public void setPredecessorId(int predecessorId) {
        this.predecessorId = predecessorId;
    }

    public int getSuccessorId() {
        return successorId;
    }

    public void setSuccessorId(int successorId) {
        this.successorId = successorId;
    }

    //todo: sdelat norm toString
    @Override
    public String toString() {
        return "From " + predecessorId + " To " + successorId + " = " + weight;
    }
}
