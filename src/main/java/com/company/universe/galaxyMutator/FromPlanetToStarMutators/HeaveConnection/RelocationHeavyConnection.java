package com.company.universe.galaxyMutator.FromPlanetToStarMutators.HeaveConnection;

import com.company.entities.Coefficient;
import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.universe.Galaxy;
import com.company.universe.StarSystem;
import com.company.universe.galaxyMutator.FromPlanetToStarMutators.LightPlanet.RelocationLightPlanet;
import com.company.universe.galaxyMutator.FromPlanetToStarMutators.relocation.Relocation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RelocationHeavyConnection implements Relocation {
    @Override
    public int criterion(StarSystem system, Graph graph) {
        int star = system.getStar();
        int starWeight = graph.getVertices().get(star).getWeight();
        List<Coefficient> heaviest = new ArrayList<>();
        for (int planet:
                system.getPlanets()) {
            Coefficient coefficient = new Coefficient(
                    star,
                    planet,
                    starWeight * graph.getEdgeMatrix().getCell(star,planet));
            heaviest.add(coefficient);
        }
        return heaviest
                .stream()
                .max(Comparator.comparingInt(Coefficient::getWeight))
                .map(Coefficient::getSatellite)
                .orElse(-1);
    }

    @Override
    public Galaxy rebase(Galaxy galaxy, Graph graph, int win) {
        return new RelocationLightPlanet().rebase(galaxy, graph, win);
    }
}
