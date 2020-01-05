package com.company.universe.galaxyMutator.FromPlanetToStarMutators.relocation;

import com.company.entities.Graph;
import com.company.universe.Galaxy;

public interface Relocation {
    int criterion(Galaxy galaxy, Graph graph);
    Galaxy rebase(Galaxy galaxy, Graph graph);
}
