package com.company;

import java.util.List;
import java.util.Objects;


public class Vertex {
    private int id;
    private int weight;

    public Vertex(int id, int weight) {
        this.id = id;
        this.weight = weight;
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

//    @Override
//    public String toString() {
//        return "Vertex id: " + this.id + " , weight: " + this.weight;
//    }

    @Override
    public String toString()
    {
        return "Vertex{" +
                "id=" + id +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex vertex = (Vertex) o;
        return getId() == vertex.getId() &&
                getWeight() == vertex.getWeight();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getWeight());
    }
}
