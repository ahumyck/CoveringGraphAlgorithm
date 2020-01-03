package com.company.universe;

import com.company.entities.Graph;
import com.company.universe.galaxyMutator.Mutator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class GalaxyPool {
    private List<Galaxy> galaxies;
    private Graph graph;

    public GalaxyPool(Graph graph, int howMany){
        this.graph = graph;
        this.galaxies = GalaxyPoolGenerator.getGalaxies(graph,howMany);
    }

    public void addGalaxies(int howMany){
        this.galaxies.addAll(GalaxyPoolGenerator.getGalaxies(graph, howMany));
    }

    public GalaxyPool orderByWeight(){
        galaxies.sort(Comparator.comparingInt(Galaxy::getWeight));
        return this;
    }

    public List<Galaxy> getFirstGalaxies(int howMany){
        return galaxies.stream().limit(howMany).collect(Collectors.toList());
    }

    public Galaxy getBestGalaxy(){
        return orderByWeight().galaxies.get(0);
    }

    public void mutate(Mutator mutator){

        for (Galaxy galaxy:
             galaxies) {
            galaxy.orderByWeight();
            mutator.mutate(galaxy,graph);
        }
        orderByWeight();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Galaxy Pool\n");
        for (Galaxy galaxy:
                galaxies) {
            builder.append(galaxy.toString()).append('\n');
        }
        return builder.toString();
    }
}
