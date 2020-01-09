package com.company.genetic.universe.galaxyMutator.FromPlanetToStarMutators.LightPlanet;

import com.company.entities.Graph;
import com.company.genetic.universe.Galaxy;
import com.company.genetic.universe.galaxyMutator.FromPlanetToStarMutators.FromPlanetToStarMutatorHelper;
import com.company.genetic.universe.galaxyMutator.Mutator;

public class FromPlanetToStarMutatorLightPlanet implements Mutator {
    @Override
    public Galaxy mutate(Galaxy galaxy, Graph graph) {
        return FromPlanetToStarMutatorHelper.rebase(galaxy,graph,new RelocationLightPlanet());
    }

}
