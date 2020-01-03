package com.company.universe;

import com.company.entities.Graph;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


//todo: clone Galaxy
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

    public Galaxy addWeight(int weight){
        this.weight += weight;
        return this;
    }

    public Galaxy removeWeight(int weight){
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
        this.systems.sort(Comparator.comparingInt(StarSystem::getWeight));
        return this;
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
