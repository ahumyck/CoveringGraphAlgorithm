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

public class OptimalPlanetDistributorMutator implements Mutator {
    @Override
    public Galaxy mutate(Galaxy galaxy, Graph graph) {
        List<StarSystem> badSystems = galaxy.getSystems()
                .stream()
                .filter(x -> x.getWeight() > galaxy.getSystems().get(0).getWeight())
                .collect(Collectors.toList());

        for(int i = 0 ; i < badSystems.size(); i++) {
            StarSystem badSystem = badSystems.get(i);
            galaxy.getSystems().remove(badSystem);
            galaxy.removeWeight(badSystem.getWeight());

            for(int j = 0; j < badSystem.getPlanets().size(); j++) {
                findBetterHomeForPlanet(badSystem,
                        badSystem.getPlanets().get(j),
                        galaxy,
                        graph);
            }
            if(badSystem.getPlanets().isEmpty())
                findBetterHomeForStar(badSystem.getStar(),galaxy,graph);
            else{
                galaxy.getSystems().add(badSystem);
                galaxy.addWeight(badSystem.getWeight());
            }
        }
        return galaxy;
    }


    private void findBetterHomeForPlanet(StarSystem system, int planet, Galaxy galaxy, Graph graph){
        int oldCost = calculateWeight(graph, system.getStar(), planet);
        List<Coefficient> bestSolutions = getBestSolutions(planet, galaxy, graph);

        Coefficient home = bestSolutions.stream().min(Comparator.comparingLong(Coefficient::getWeight)).get(); // Best Weight
        if(oldCost > home.getWeight()) {
            system.remove(planet, oldCost);
            galaxy.getSystems().get(bestSolutions.indexOf(home)).add(planet, home.getWeight()); // Add weight and home to system
            galaxy.addWeight(home.getWeight()); // Add weight to galaxy
        }
    }

    private void findBetterHomeForStar(int name, Galaxy galaxy, Graph graph){
        List<Coefficient> bestSolutions = getBestSolutions(name, galaxy, graph);
        Coefficient home = bestSolutions.stream().min(Comparator.comparingLong(Coefficient::getWeight)).get(); // Best Weight
        galaxy.getSystems().get(bestSolutions.indexOf(home)).add(name, home.getWeight()); // Add weight and home to system
        galaxy.addWeight(home.getWeight()); // Add weight to galaxy
    }


    private List<Coefficient> getBestSolutions(int name, Galaxy galaxy, Graph graph){
        List<Coefficient> solutions = new ArrayList<>();
        for (StarSystem goodSystem:
                galaxy.getSystems()) {
            solutions.add(new Coefficient(goodSystem.getStar(),
                    name,
                    calculateWeight(graph,goodSystem.getStar(),name)));
        }
        return solutions;
    }

    private int calculateWeight(Graph graph,int i, int j){
        List<Vertex> vertices = graph.getVertices();
        EdgeMatrix edgeMatrix = graph.getEdgeMatrix();
        return vertices.get(i).getWeight() * edgeMatrix.getCell(i,j);
    }
}
