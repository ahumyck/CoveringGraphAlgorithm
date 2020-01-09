package com.company.genetic.universe.galaxyMutator.FromPlanetToStarMutators;

import com.company.entities.Graph;
import com.company.genetic.universe.Galaxy;
import com.company.genetic.universe.StarSystem;
import com.company.genetic.universe.galaxyMutator.FromPlanetToStarMutators.relocation.Relocation;


public class FromPlanetToStarMutatorHelper {
    public static Galaxy rebase(Galaxy galaxy, Graph graph, Relocation relocation){

        int size = galaxy.getSystems().size();
        StarSystem system = galaxy.orderByWeight().getSystems().get(size - 1);
        if(system.getPlanets().size() >= size / 2){
            int index = relocation.criterion(galaxy,graph);
            if(index != - 1) {
                Galaxy g = relocation.rebase(galaxy,graph);
                if(g.orderByWeight().getSystems().get(0).getWeight() != 0) return g;
            }
        }
        return galaxy;
    }
}
