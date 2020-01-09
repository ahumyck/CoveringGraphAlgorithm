package com.company.universe;

import com.company.entities.Graph;
import com.company.universe.galaxyMutator.Mutator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class GalaxyPool {
    public static int EMPTY = 0;

    private List<MutableGalaxy> galaxies;
    private Graph graph;

    public GalaxyPool(Graph graph, int howMany){
        this.graph = graph;
        this.galaxies = GalaxyPoolGenerator.getGalaxies(graph,howMany);
    }

    public void addGalaxies(int howMany){
        this.galaxies.addAll(GalaxyPoolGenerator.getGalaxies(graph, howMany));
    }

    public void addGalaxy(Galaxy galaxy) { this.galaxies.add(new MutableGalaxy(galaxy,true)); orderByWeight(); }

    public GalaxyPool orderByWeight(){
        galaxies.sort(Comparator.comparingLong(MutableGalaxy::getWeight));
        return this;
    }

    public Galaxy getBestGalaxy(){
        return orderByWeight().galaxies.get(0).getGalaxy();
    }

    public void mutate(Mutator mutator){

        orderByWeight();
        for(int i = 0 ; i < galaxies.size(); i++){
            MutableGalaxy beforeMutation = galaxies.get(i);
            if(beforeMutation.isSpaceToGrow()) {
                Galaxy afterMutation = mutator.mutate(beforeMutation.getGalaxy(), graph);
                if (beforeMutation.getWeight() - afterMutation.getWeight() == 0) {
                    beforeMutation.setSpaceToGrow(false);
                    System.out.println("Galaxy " + i + " is no good");
                }
                else{
                    beforeMutation.setGalaxy(afterMutation);
                }
                orderByWeight();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Galaxy Pool\n");
        for (MutableGalaxy galaxy:
                galaxies) {
            builder.append(galaxy.toString()).append('\n');
        }
        return builder.toString();
    }
}
