package com.company.universe.galaxyMutator.FromPlanetToStarMutators;

import com.company.entities.Graph;
import com.company.universe.Galaxy;
import com.company.universe.StarSystem;
import com.company.universe.galaxyMutator.FromPlanetToStarMutators.relocation.Relocation;


public class FromPlanetToStarMutatorHelper {
    public static Galaxy rebase(Galaxy galaxy, Graph graph, Relocation relocation){

        int size = galaxy.getSystems().size();
        StarSystem system = galaxy.orderByWeight().getSystems().get(size - 1);
        if(system.getPlanets().size() >= size / 2){
            int index = relocation.criterion(galaxy,graph);
            if(index != - 1) {
                Galaxy g = galaxy.clone();
                g = relocation.rebase(g,graph,index,galaxy.getWeight() - g.getWeight());
                if(g.orderByWeight().getSystems().get(0).getWeight() != 0) return g;
            }
        }
        return galaxy;
    }
}
