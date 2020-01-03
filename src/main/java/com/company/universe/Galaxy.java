package com.company.universe;

import com.company.entities.Graph;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Galaxy {
    private List<StarSystem> systems;
    private int weight;

    public Galaxy(List<StarSystem> systems) {
        this.systems = systems.stream().sorted(Comparator.comparingInt(StarSystem::getWeight)).collect(Collectors.toList());
        for (StarSystem system :
                systems) {
            weight += system.getWeight();
        }
    }

    public int getWeight() {
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

    public void addWeight(int weight){
        this.weight += weight;
    }

    public void removeWeight(int weight){
        this.weight -= weight;
    }

    public void calculateWeight(Graph graph){
        this.weight = 0;
        for (StarSystem system:
             systems) {
            system.calculateWeight(graph);
            this.weight += system.getWeight();
        }
    }

    public void orderByWeight(){
        this.systems.sort(Comparator.comparingInt(StarSystem::getWeight));
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
