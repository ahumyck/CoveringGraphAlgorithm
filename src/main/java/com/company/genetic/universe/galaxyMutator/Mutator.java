package com.company.genetic.universe.galaxyMutator;

import com.company.entities.Graph;
import com.company.genetic.universe.Galaxy;

public interface Mutator {
    Galaxy mutate(Galaxy galaxy, Graph graph);
}
