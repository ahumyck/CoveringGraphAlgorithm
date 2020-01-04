package com.company.universe.galaxyMutator;

import com.company.entities.Graph;
import com.company.universe.Galaxy;

public interface Mutator {
    Galaxy mutate(Galaxy galaxy, Graph graph);
}
