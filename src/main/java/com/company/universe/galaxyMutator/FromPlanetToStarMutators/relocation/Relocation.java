package com.company.universe.galaxyMutator.FromPlanetToStarMutators.relocation;

import com.company.entities.Graph;
import com.company.universe.Galaxy;
import com.company.universe.StarSystem;

public interface Relocation {
    int criterion(StarSystem system, Graph graph);
    Galaxy rebase(Galaxy galaxy, Graph graph, int win);
}
