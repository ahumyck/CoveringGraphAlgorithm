package com.company.universe.galaxyMutator;

import com.company.entities.Graph;
import com.company.universe.Galaxy;
import com.company.universe.galaxyMutator.FromPlanetToStarMutators.HeavyConnection.FromPlanetToStarMutatorHeavyConnection;
import com.company.universe.galaxyMutator.FromPlanetToStarMutators.LightPlanet.FromPlanetToStarMutatorLightPlanet;

public class CombinationMutator implements Mutator {
    @Override
    public Galaxy mutate(Galaxy galaxy, Graph graph) {
        return
                new OptimalPlanetDistributor().mutate(
                    new FromPlanetToStarMutatorHeavyConnection().mutate(
                        new FromPlanetToStarMutatorLightPlanet().mutate(galaxy,graph),
                graph), graph);
    }
}
