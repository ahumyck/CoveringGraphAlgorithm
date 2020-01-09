package com.company.genetic.universe.galaxyMutator;

import com.company.entities.Coefficient;
import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;
import com.company.genetic.universe.Galaxy;
import com.company.genetic.universe.StarSystem;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class OptimalPlanetDistributor implements Mutator {
    @Override
    public Galaxy mutate(Galaxy galaxy, Graph graph) {
        for(int i = 0 ; i < galaxy.getSystems().size(); i++){
            StarSystem badSystem = galaxy.getSystems().remove(i);

            boolean putBackBadSystem = true;
            for(int j = 0; j < badSystem.getPlanets().size(); j++){
                Optional<Coefficient> worstConnectionInSystem = findWorstConnectionInSystem(badSystem,graph);
                if(worstConnectionInSystem.isPresent()){
                    Coefficient worst = worstConnectionInSystem.get();
                    Optional<Coefficient> bestPlaceOptional = findBestPlaceForWorstConnection(galaxy, worst.getSatellite(), graph);
                    if(bestPlaceOptional.isPresent()){
                        Coefficient bestPlace = bestPlaceOptional.get();
                        if(bestPlace.getWeight() < worst.getWeight()){
                            if (badSystem.getPlanets().size() > 1){
                                Optional<StarSystem> systemByStarOptional = findSystemByStar(galaxy, bestPlace.getStar());
                                if(systemByStarOptional.isPresent()){
                                    StarSystem starSystem = systemByStarOptional.get();
                                    badSystem.remove(worst.getSatellite(),worst.getWeight());
                                    starSystem.add(bestPlace.getSatellite(),bestPlace.getWeight());
                                }
                            }
                            else{
                                Optional<Coefficient> bestPlaceForWorstConnectionOptional
                                        = findBestPlaceForWorstConnection(galaxy, badSystem.getStar(), graph);
                                if(bestPlaceForWorstConnectionOptional.isPresent()){
                                    Coefficient bestPlaceForWorstConnectionStar = bestPlaceForWorstConnectionOptional.get();

                                    if(bestPlace.getWeight() + bestPlaceForWorstConnectionStar.getWeight() < worst.getWeight()){
                                        Optional<StarSystem> systemByStarOptional = findSystemByStar(galaxy, bestPlace.getStar());
                                        Optional<StarSystem> systemForStarOptional = findSystemByStar(galaxy, bestPlaceForWorstConnectionStar.getStar());

                                        if(systemByStarOptional.isPresent() && systemForStarOptional.isPresent()){
                                            putBackBadSystem = false;
                                            StarSystem systemByStar = systemByStarOptional.get();
                                            StarSystem systemForStar = systemForStarOptional.get();
                                            systemByStar.add(bestPlace.getSatellite(),bestPlace.getWeight());
                                            systemForStar.add(bestPlaceForWorstConnectionStar.getSatellite(),bestPlaceForWorstConnectionStar.getWeight());
                                        }

                                    }
                                }
                            }
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

    private Optional<Coefficient> findBestPlaceForWorstConnection(Galaxy galaxy, int satellite, Graph graph) {
        EdgeMatrix edgeMatrix = graph.getEdgeMatrix();
        List<Vertex> vertices = graph.getVertices();
        return galaxy.getSystems().stream().map(starSystem ->
                new Coefficient(
                        starSystem.getStar(),
                        satellite,
                        vertices.get(starSystem.getStar()).getWeight() *
                                edgeMatrix.getCell(starSystem.getStar(),satellite)

                ))
                .min(Comparator.comparingLong(Coefficient::getWeight));
    }

    private Optional<StarSystem> findSystemByPlanet(Galaxy galaxy, int planet){
        return galaxy.getSystems()
                .stream()
                .filter(system -> system.getPlanets().contains(planet))
                .findFirst();
    }

    private Optional<StarSystem> findSystemByStar(Galaxy galaxy, int star){
        return galaxy.getSystems()
                .stream()
                .filter(system -> system.getStar() == star)
                .findFirst();
    }

    private Optional<Coefficient> findWorstConnectionInSystem(StarSystem badSystem, Graph graph) {
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
                .max(Comparator.comparingLong(Coefficient::getWeight));
    }
}
