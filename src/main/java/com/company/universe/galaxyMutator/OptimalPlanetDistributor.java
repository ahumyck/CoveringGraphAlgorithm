package com.company.universe.galaxyMutator;

import com.company.entities.Coefficient;
import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;
import com.company.universe.Galaxy;
import com.company.universe.StarSystem;

import java.util.Comparator;
import java.util.List;

public class OptimalPlanetDistributor implements Mutator {
    @Override
    public Galaxy mutate(Galaxy galaxy, Graph graph) {
        for(int i = 0 ; i < galaxy.getSystems().size(); i++){
            StarSystem badSystem = galaxy.getSystems().remove(i);

            boolean putBackBadSystem = true;
            for(int j = 0; j < badSystem.getPlanets().size(); j++){
                Coefficient worstConnectionInSystem = findWorstConnectionInSystem(badSystem,graph);
                Coefficient bestPlace = findBestPlaceForWorstConnection(galaxy, worstConnectionInSystem.getSatellite(), graph);
                if(bestPlace.getWeight() < worstConnectionInSystem.getWeight()){
                    if (badSystem.getPlanets().size() > 1){
                        badSystem.remove(worstConnectionInSystem.getSatellite(),worstConnectionInSystem.getWeight());
                        StarSystem systemByStar = findSystemByStar(galaxy, bestPlace.getStar());
                        systemByStar.add(bestPlace.getSatellite(),bestPlace.getWeight());
                    }
                    else{
                        Coefficient bestPlaceForWorstConnectionStar = findBestPlaceForWorstConnection(galaxy, badSystem.getStar(), graph);
                        if(bestPlace.getWeight() + bestPlaceForWorstConnectionStar.getWeight() < worstConnectionInSystem.getWeight()){
                            putBackBadSystem = false;
                            StarSystem systemByStar = findSystemByStar(galaxy, bestPlace.getStar());
                            systemByStar.add(bestPlace.getSatellite(),bestPlace.getWeight());

                            StarSystem systemForStar = findSystemByStar(galaxy, bestPlaceForWorstConnectionStar.getStar());
                            systemForStar.add(bestPlaceForWorstConnectionStar.getSatellite(),bestPlaceForWorstConnectionStar.getWeight());
                        }
                    }
                }
            }
            if(putBackBadSystem){
                galaxy.getSystems().add(badSystem);
                galaxy.addWeight(badSystem.getWeight());
            }
        }

        return galaxy.calculateWeight(graph).orderByWeight();
    }

    private Coefficient findBestPlaceForWorstConnection(Galaxy galaxy, int satellite, Graph graph) {
        EdgeMatrix edgeMatrix = graph.getEdgeMatrix();
        List<Vertex> vertices = graph.getVertices();
        return galaxy.getSystems().stream().map(starSystem ->
                new Coefficient(
                        starSystem.getStar(),
                        satellite,
                        vertices.get(starSystem.getStar()).getWeight() *
                                edgeMatrix.getCell(starSystem.getStar(),satellite)

                ))
                .min(Comparator.comparingLong(Coefficient::getWeight))
                .get();
    }

    private StarSystem findSystemByPlanet(Galaxy galaxy, int planet){
        return galaxy.getSystems()
                .stream()
                .filter(system -> system.getPlanets().contains(planet))
                .findFirst()
                .get();
    }

    private StarSystem findSystemByStar(Galaxy galaxy, int star){
        return galaxy.getSystems()
                .stream()
                .filter(system -> system.getStar() == star)
                .findFirst()
                .get();
    }

    private Coefficient findWorstConnectionInSystem(StarSystem badSystem, Graph graph) {
        int star = badSystem.getStar();
        EdgeMatrix edgeMatrix = graph.getEdgeMatrix();
        List<Vertex> vertices = graph.getVertices();
        return badSystem.getPlanets().stream()
                .map(planet -> new Coefficient(
                        star,
                        planet,
                        vertices.get(star).getWeight() *
                                edgeMatrix.getCell(star,planet)
                ))
                .max(Comparator.comparingLong(Coefficient::getWeight))
                .get();
    }
}
