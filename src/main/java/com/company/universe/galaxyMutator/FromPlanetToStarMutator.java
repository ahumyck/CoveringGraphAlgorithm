package com.company.universe.galaxyMutator;

import com.company.entities.Graph;
import com.company.entities.Vertex;
import com.company.universe.Galaxy;
import com.company.universe.StarSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//todo: make a good combination of Mutators
public class FromPlanetToStarMutator implements Mutator {
    @Override
    public void mutate(Galaxy galaxy, Graph graph) {
        List<Vertex> vertices = graph.getVertices();

        int size = galaxy.getSystems().size();
        StarSystem system = galaxy.orderByWeight().getSystems().get(size - 1);
        if(system.getPlanets().size() >= size / 2){
            int index = findMinimumStar(system.getPlanets(),vertices);
            if(index != - 1) {
                StarSystem newSystem = new StarSystem(index,new ArrayList<>(), 0);
                galaxy.getSystems().add(newSystem);
                system.remove(index,
                        vertices.get(system.getStar()).getWeight()
                                * graph.getEdgeMatrix().getCell(system.getStar(),index));
                galaxy.orderByWeight().calculateWeight(graph);
                for(int i = 0; i < 10; i++) {
                    new OptimalPlanetDistributorMutator().mutate(galaxy, graph);
                }
            }
        }
    }

    private int findMinimumStar(List<Integer> planets, List<Vertex> vertices){
        return vertices.stream().filter(x -> planets.contains(x.getId())).min(Comparator.comparingInt(Vertex::getId)).map(Vertex::getId).orElse(-1);
    }
}
