package com.company.universe.galaxyMutator;

import com.company.entities.Graph;
import com.company.universe.Galaxy;
import com.company.universe.galaxyMutator.FromPlanetToStarMutators.HeaveConnection.FromPlanetToStarMutatorHeavyConnection;
import com.company.universe.galaxyMutator.FromPlanetToStarMutators.LightPlanet.FromPlanetToStarMutatorLightPlanet;
import com.company.universe.galaxyMutator.OptipalPlanetDistributor.OptimalPlanetDistributorMutator;

public class CombinationMutator implements Mutator {
    @Override
    public Galaxy mutate(Galaxy galaxy, Graph graph) {
        int size = galaxy.getSystems().size();
        Mutator mutator = new OptimalPlanetDistributorMutator();
        for(int i = 0 ; i < size; i++){
            galaxy = mutator.mutate(galaxy, graph);
        }
        galaxy = new FromPlanetToStarMutatorLightPlanet().mutate(galaxy,graph);
        return new FromPlanetToStarMutatorHeavyConnection().mutate(galaxy,graph);
    }
}
