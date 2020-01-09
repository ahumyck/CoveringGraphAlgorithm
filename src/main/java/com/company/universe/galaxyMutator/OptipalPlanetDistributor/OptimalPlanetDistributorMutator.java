package com.company.universe.galaxyMutator.OptipalPlanetDistributor;

import com.company.entities.Coefficient;
import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;
import com.company.universe.Galaxy;
import com.company.universe.StarSystem;
import com.company.universe.galaxyMutator.Mutator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Deprecated
public class OptimalPlanetDistributorMutator implements Mutator {
    @Override
    public Galaxy mutate(Galaxy galaxy, Graph graph) {
        List<StarSystem> badSystems = galaxy.getSystems()
                .stream()
                .filter(x -> x.getWeight() > 0) //  galaxy.getSystems().get(0).getWeight()
                .collect(Collectors.toList());

        return galaxy;
    }
}
