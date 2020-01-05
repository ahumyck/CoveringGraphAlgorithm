package com.company.universe;

import com.company.entities.Graph;
import com.company.utils.Algorithms;
import com.google.gson.Gson;

import java.util.List;
import java.util.stream.Collectors;

public class StarSystem implements Cloneable{
    private int star;
    private List<Integer> planets;
    private long weight;

    public StarSystem(int star, List<Integer> planets, long weight) {
        this.star = star;
        this.planets = planets.stream().sorted().collect(Collectors.toList());
        this.weight = weight;
    }

    public boolean intercept(StarSystem system){
        return (star == system.getStar())
                || (Algorithms.contains(planets, system.getStar()) != -1)
                || (Algorithms.contains(system.getPlanets(), star) != -1)
                || (Algorithms.intercept(planets,system.getPlanets()));
    }

    public void add(int planet, long additionalWeight){
        planets.add(planet);
        planets.sort(Integer::compareTo);
        weight += additionalWeight;
    }

    public void remove(int planet, int weight){
        planets.remove((Integer) planet);
        this.weight -= weight;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public List<Integer> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Integer> planets) {
        this.planets = planets;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public void calculateWeight(Graph graph){
        this.weight = 0;
        int k = graph.getVertices().get(star).getWeight();
        for(int planet : planets){
            this.weight += k * graph.getEdgeMatrix().getCell(star,planet);
        }
    }

    @Override
    public StarSystem clone(){
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(this),StarSystem.class);
    }

    @Override
    public String toString() {
        return "[" + star + "] = " + planets + " has weight " + weight;
    }
}
