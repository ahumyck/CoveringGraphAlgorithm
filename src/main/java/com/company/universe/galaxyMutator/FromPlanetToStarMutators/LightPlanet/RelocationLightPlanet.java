package com.company.universe.galaxyMutator.FromPlanetToStarMutators.LightPlanet;

import com.company.entities.Coefficient;
import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;
import com.company.universe.Galaxy;
import com.company.universe.StarSystem;
import com.company.universe.galaxyMutator.FromPlanetToStarMutators.relocation.Relocation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RelocationLightPlanet implements Relocation {
    @Override
    public int criterion(StarSystem system, Graph graph) {
        List<Integer> planets = system.getPlanets();
        List<Vertex> vertices = graph.getVertices();
        return vertices.stream()
                .filter(x -> planets.contains(x.getId()))
                .min(Comparator.comparingInt(Vertex::getWeight))
                .map(Vertex::getId)
                .orElse(-1);
    }

    @Override
    public Galaxy rebase(Galaxy galaxy, Graph graph, int win) {
        StarSystem emptySystem = galaxy.getSystems().get(0);
        StarSystem heavySystem = galaxy.getSystems().get(galaxy.getSystems().size() - 1);
        EdgeMatrix edgeMatrix = graph.getEdgeMatrix();

        int weight = graph.getVertices().get(emptySystem.getStar()).getWeight();
        List<Coefficient> bestSolutions = new ArrayList<>();

        for (int planet:
                heavySystem.getPlanets()) {
            int tmpWeight = weight * edgeMatrix.getCell(emptySystem.getStar(),planet);
            if(tmpWeight < win) bestSolutions.add(new Coefficient(emptySystem.getStar(),planet,tmpWeight));
        }
        Optional<Coefficient> optionalBestSolution = bestSolutions.stream().min(Comparator.comparingInt(Coefficient::getWeight));
        if(optionalBestSolution.isPresent()){
            Coefficient bestSolution = optionalBestSolution.get();
            emptySystem.add(bestSolution.getSatellite(),bestSolution.getWeight());
            heavySystem.remove(bestSolution.getSatellite(),
                    graph.getVertices().get(heavySystem.getStar()).getWeight() *
                            graph.getEdgeMatrix().getCell(heavySystem.getStar(),bestSolution.getSatellite()));
            galaxy.calculateWeight(graph);
        }
        return galaxy;
    }
}
