package com.company.universe.galaxyMutator.FromPlanetToStarMutators;

import com.company.entities.Graph;
import com.company.entities.Vertex;
import com.company.universe.Galaxy;
import com.company.universe.StarSystem;
import com.company.universe.galaxyMutator.FromPlanetToStarMutators.relocation.Relocation;

import java.util.ArrayList;
import java.util.List;

public class FromPlanetToStarMutatorHelper {
    public static Galaxy rebase(Galaxy galaxy, Graph graph, Relocation relocation){
        List<Vertex> vertices = graph.getVertices();

        int size = galaxy.getSystems().size();
        StarSystem system = galaxy.orderByWeight().getSystems().get(size - 1);
        if(system.getPlanets().size() >= size / 2){
            int index = relocation.criterion(galaxy,graph);
            if(index != - 1) {
                Galaxy g = galaxy.clone();
                StarSystem newSystem = new StarSystem(index,new ArrayList<>(), 0);
                g.getSystems().add(newSystem);
                g.getSystems().get(size - 1).remove(index,
                        vertices.get(system.getStar()).getWeight()
                                * graph.getEdgeMatrix().getCell(system.getStar(),index));
                g.orderByWeight().calculateWeight(graph);
                g = relocation.rebase(g,graph,galaxy.getWeight() - g.getWeight());
                if(g.orderByWeight().getSystems().get(0).getWeight() != 0) return g;
            }
        }
        return galaxy;
    }
}
