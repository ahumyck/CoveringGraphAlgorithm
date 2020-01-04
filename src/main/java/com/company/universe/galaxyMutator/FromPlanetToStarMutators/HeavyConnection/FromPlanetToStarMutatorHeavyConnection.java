package com.company.universe.galaxyMutator.FromPlanetToStarMutators.HeavyConnection;

import com.company.entities.Graph;
import com.company.universe.Galaxy;
import com.company.universe.galaxyMutator.FromPlanetToStarMutators.FromPlanetToStarMutatorHelper;
import com.company.universe.galaxyMutator.Mutator;

public class FromPlanetToStarMutatorHeavyConnection implements Mutator {
    @Override
    public Galaxy mutate(Galaxy galaxy, Graph graph) {
        return FromPlanetToStarMutatorHelper.rebase(galaxy,graph, new RelocationHeavyConnection());
    }
}
