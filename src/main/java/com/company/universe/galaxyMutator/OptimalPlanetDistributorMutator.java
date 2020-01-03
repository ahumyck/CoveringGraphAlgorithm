package com.company.universe.galaxyMutator;

import com.company.entities.Coefficient;
import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;
import com.company.universe.Galaxy;
import com.company.universe.StarSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OptimalPlanetDistributorMutator implements Mutator {
    @Override
    public void mutate(Galaxy galaxy, Graph graph) {
        List<StarSystem> badSystems = galaxy.getSystems().stream().filter(x -> x.getWeight() > galaxy.getSystems().get(0).getWeight()).collect(Collectors.toList());
//todo:   Need to find a reason why are they "bad"?
//        Maybe weight of vertex is really big
//        or planet is far away from star
        //do the smart things later, right now im gonna mini brute force

        for(int i = 0 ; i < badSystems.size(); i++) {
            StarSystem badSystem = badSystems.get(i);
            galaxy.getSystems().remove(badSystem);
            galaxy.removeWeight(badSystem.getWeight());

            for(int j = 0; j < badSystem.getPlanets().size(); j++) {
                findBetterHomeForSatellite(badSystem,
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

    }

    private void findBetterHomeForSatellite(StarSystem system, int planet, Galaxy galaxy,Graph graph){
        int oldCost = calculateWeight(graph, system.getStar(), planet);
        List<Coefficient> bestSolutions = getBestSolutions(planet, galaxy, graph);

        Coefficient satellite = bestSolutions.stream().min(Comparator.comparingInt(Coefficient::getWeight)).get(); // Best Weight
        if(oldCost > satellite.getWeight()) {
            system.remove(planet, oldCost);
            galaxy.getSystems().get(bestSolutions.indexOf(satellite)).add(planet, satellite.getWeight()); // Add weight and satellite to system
            galaxy.addWeight(satellite.getWeight()); // Add weight to galaxy
        }
    }

    private void findBetterHomeForStar(int name, Galaxy galaxy, Graph graph){
        List<Coefficient> bestSolutions = getBestSolutions(name, galaxy, graph);
        Coefficient satellite = bestSolutions.stream().min(Comparator.comparingInt(Coefficient::getWeight)).get(); // Best Weight
        galaxy.getSystems().get(bestSolutions.indexOf(satellite)).add(name, satellite.getWeight()); // Add weight and satellite to system
        galaxy.addWeight(satellite.getWeight()); // Add weight to galaxy
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
