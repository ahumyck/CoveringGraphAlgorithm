package com.company;

import com.company.entities.Vertex;

import java.util.List;
import java.util.Map;

public class StarPlan {
    private Map<Vertex, List<Vertex>> starSatelliteMap;
    private int cost;

    public StarPlan(Map<Vertex , List<Vertex>> starSatelliteMap, int cost) {
        this.starSatelliteMap = starSatelliteMap;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "StarPlan{\n" +
                "starSatelliteMap=" + starSatelliteMap +
                ", \ncost=" + cost +
                '}';
    }
}
