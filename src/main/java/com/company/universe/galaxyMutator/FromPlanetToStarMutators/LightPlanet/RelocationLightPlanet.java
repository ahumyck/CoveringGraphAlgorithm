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
    private int index;
    @Override
    public int criterion(Galaxy galaxy, Graph graph) {
        List<Integer> planets = galaxy.orderByWeight().getSystems().get(galaxy.getSystems().size() - 1).getPlanets(); // getting planets of heaviest system
        List<Vertex> vertices = graph.getVertices();
        index = vertices.stream()
                .filter(x -> planets.contains(x.getId()))
                .min(Comparator.comparingInt(Vertex::getWeight))
                .map(Vertex::getId)
                .orElse(-1);
        return index;
    }

    @Override
    public Galaxy rebase(Galaxy galaxy, Graph graph) {
        Galaxy g = galaxy.clone();
        int size = g.getSystems().size();
        StarSystem heavySystem = g.orderByWeight().getSystems().get(size - 1);
        StarSystem newSystem = new StarSystem(index,new ArrayList<>(), 0);
        g.getSystems().add(newSystem);
        heavySystem.remove(index,
                graph.getVertices().get(heavySystem.getStar()).getWeight()
                        * graph.getEdgeMatrix().getCell(heavySystem.getStar(),index));
        g.orderByWeight().calculateWeight(graph);
        long reserve = galaxy.getWeight() - g.getWeight();

        StarSystem emptySystem = g.getSystems().get(0);
        EdgeMatrix edgeMatrix = graph.getEdgeMatrix();


        int weight = graph.getVertices().get(emptySystem.getStar()).getWeight();
        List<Coefficient> bestSolutions = new ArrayList<>();

        for (int planet:
                heavySystem.getPlanets()) {
            int tmpWeight = weight * edgeMatrix.getCell(emptySystem.getStar(),planet);
            if(tmpWeight < reserve) bestSolutions.add(new Coefficient(emptySystem.getStar(),planet,tmpWeight));
        }
        Optional<Coefficient> optionalBestSolution = bestSolutions.stream().min(Comparator.comparingLong(Coefficient::getWeight));
        if(optionalBestSolution.isPresent()){
            Coefficient bestSolution = optionalBestSolution.get();
            emptySystem.add(bestSolution.getSatellite(),bestSolution.getWeight());
            heavySystem.remove(bestSolution.getSatellite(),
                    graph.getVertices().get(heavySystem.getStar()).getWeight() *
                            graph.getEdgeMatrix().getCell(heavySystem.getStar(),bestSolution.getSatellite()));
            return g.orderByWeight();
        }
        return galaxy;
    }
}
