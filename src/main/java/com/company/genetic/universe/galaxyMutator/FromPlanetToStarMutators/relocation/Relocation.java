package com.company.genetic.universe.galaxyMutator.FromPlanetToStarMutators.relocation;

import com.company.entities.Graph;
import com.company.genetic.universe.Galaxy;

public interface Relocation {
    int criterion(Galaxy galaxy, Graph graph);
    Galaxy rebase(Galaxy galaxy, Graph graph);
}
