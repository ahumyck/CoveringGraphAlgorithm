package com.company.universe;

import com.company.entities.Graph;
import com.google.gson.Gson;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Galaxy implements Cloneable{
    private List<StarSystem> systems;
    private long weight;

    public Galaxy(List<StarSystem> systems) {
        this.systems = systems.stream().sorted(Comparator.comparingLong(StarSystem::getWeight)).collect(Collectors.toList());
        for (StarSystem system :
                systems) {
            weight += system.getWeight();
        }
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<StarSystem> getSystems() {
        return systems;
    }

    public void setSystems(List<StarSystem> systems) {
        this.systems = systems;
    }

    public Galaxy addWeight(long weight){
        this.weight += weight;
        return this;
    }

    public Galaxy removeWeight(long weight){
        this.weight -= weight;
        return this;
    }

    public Galaxy calculateWeight(Graph graph){
        this.weight = 0;
        for (StarSystem system:
             systems) {
            system.calculateWeight(graph);
            this.weight += system.getWeight();
        }
        return this;
    }

    public Galaxy orderByWeight(){
        this.systems.sort(Comparator.comparingLong(StarSystem::getWeight));
        return this;
    }

    @Override
    public Galaxy clone() {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(this),Galaxy.class);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Galaxy\n");
        for (StarSystem system:
             systems) {
            builder.append(system.toString()).append('\n');
        }
        builder.append("has weight ").append(weight).append('\n');
        return builder.toString();
    }
}
