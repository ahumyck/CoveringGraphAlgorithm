package com.company.universe.galaxyMutator;

import com.company.entities.Graph;
import com.company.universe.Galaxy;
import com.company.universe.galaxyMutator.FromPlanetToStarMutators.HeavyConnection.FromPlanetToStarMutatorHeavyConnection;
import com.company.universe.galaxyMutator.FromPlanetToStarMutators.LightPlanet.FromPlanetToStarMutatorLightPlanet;
import com.company.universe.galaxyMutator.OptipalPlanetDistributor.OptimalPlanetDistributorMutator;

public class CombinationMutator implements Mutator {
    @Override
    public Galaxy mutate(Galaxy galaxy, Graph graph) {
//        Mutator mutator = new OptimalPlanetDistributorMutator();
//        Galaxy newGalaxy = mutator.mutate(galaxy,graph);
//        System.out.println("trying to mutate " + (newGalaxy.getWeight() - galaxy.getWeight()));
//        System.out.println("old galaxy: " + galaxy);
//        System.out.println("new galaxy: " + newGalaxy);
//        while (newGalaxy.getWeight() - galaxy.getWeight() != 0){
//            System.out.println("yeah im still mutating");
//            galaxy = newGalaxy;
//            newGalaxy = mutator.mutate(galaxy,graph);
//        }
        return new FromPlanetToStarMutatorHeavyConnection().mutate(
                new FromPlanetToStarMutatorLightPlanet().mutate(galaxy,graph),graph);
    }
}
