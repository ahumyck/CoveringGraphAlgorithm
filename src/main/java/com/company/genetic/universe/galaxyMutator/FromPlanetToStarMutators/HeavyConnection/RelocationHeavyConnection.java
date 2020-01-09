package com.company.genetic.universe.galaxyMutator.FromPlanetToStarMutators.HeavyConnection;

import com.company.entities.Coefficient;
import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;
import com.company.genetic.universe.Galaxy;
import com.company.genetic.universe.StarSystem;
import com.company.genetic.universe.galaxyMutator.FromPlanetToStarMutators.relocation.Relocation;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RelocationHeavyConnection implements Relocation {
    private Coefficient heaviest;

    @Override
    public int criterion(Galaxy galaxy, Graph graph) {
        List<Coefficient> heaviestList = new ArrayList<>();
        for (StarSystem system:
             galaxy.getSystems()) {
            int star = system.getStar();
            int starWeight = graph.getVertices().get(star).getWeight();
            for (int planet:
                    system.getPlanets()) {
                Coefficient coefficient = new Coefficient(
                        star,
                        planet,
                        starWeight * graph.getEdgeMatrix().getCell(star,planet));
                heaviestList.add(coefficient);
            }
        }
        heaviestList.stream()
                .max(Comparator.comparingLong(Coefficient::getWeight))
                .ifPresent(it -> heaviest = it);
        return heaviest.getSatellite();
    }

    @Override
    public Galaxy rebase(Galaxy originalGalaxy, Graph graph) {
        Galaxy cloneGalaxy = originalGalaxy.clone();
        EdgeMatrix edgeMatrix = graph.getEdgeMatrix();
        List<Vertex> vertices = graph.getVertices();

        int possibleStar = heaviest.getSatellite();
        int possibleStarWeight = vertices.get(possibleStar).getWeight();

        List<Coefficient> possibleStarList = new ArrayList<>();
        List<Coefficient> possiblePlanetList = new ArrayList<>();
        for (StarSystem system:
                cloneGalaxy.getSystems()) {
            for (int planet:
                    system.getPlanets()) {
                if(planet == possibleStar) continue;
                possibleStarList.add(new Coefficient(
                        system.getStar(),
                        planet,
                        possibleStarWeight * edgeMatrix.getCell(possibleStar,planet)));

                possiblePlanetList.add(new Coefficient(
                        system.getStar(),
                        planet,
                        possibleStarWeight * edgeMatrix.getCell(planet,possibleStar)));
            }
        }
        
        Coefficient star = StreamMin(possibleStarList).get();
        Coefficient planet = StreamMin(possiblePlanetList).get();
        Coefficient min = star.getWeight() < planet.getWeight() ? star : planet;

        return makeChange(originalGalaxy, cloneGalaxy, min, possibleStar, min.getSatellite(), graph);
    }
    
    private Optional<Coefficient> StreamMin(List<Coefficient> coefficients){
        return coefficients.stream().min(Comparator.comparingLong(Coefficient::getWeight));
    }

    private Galaxy makeChange(Galaxy originalGalaxy, Galaxy cloneGalaxy,Coefficient min, int possibleStar,int possiblePlanet,Graph graph){
        StarSystem possibleStarSystem = findSystemByPlanet(cloneGalaxy,possibleStar).get();
        StarSystem possiblePlanetSystem = findSystemByPlanet(cloneGalaxy,possiblePlanet).get();
        if(possibleStarSystem.getPlanets().size() > 1 || possiblePlanetSystem.getPlanets().size() > 1){
            int pSpPWeight = graph.getVertices().get(possibleStar).getWeight() * graph.getEdgeMatrix().getCell(possibleStar,possiblePlanet);
            int pPPsWeight = graph.getVertices().get(possiblePlanet).getWeight() * graph.getEdgeMatrix().getCell(possiblePlanet,possibleStar);

            if(pSpPWeight < pPPsWeight){
                return makeChanges(cloneGalaxy,possibleStarSystem,possiblePlanetSystem,min,possibleStar,possiblePlanet,pSpPWeight,graph)
                        .calculateWeight(graph)
                        .orderByWeight();
            }
            else{
                return makeChanges(cloneGalaxy,possiblePlanetSystem,possibleStarSystem,min,possiblePlanet,possibleStar,pPPsWeight,graph)
                        .calculateWeight(graph)
                        .orderByWeight();
            }
        }
        return originalGalaxy;
    }

    private Optional<StarSystem> findSystemByPlanet(Galaxy galaxy, int planet){
        return galaxy.getSystems()
                .stream()
                .filter(system -> system.getPlanets().contains(planet))
                .findFirst();
    }

    private Galaxy makeChanges(Galaxy galaxy, StarSystem starSystem, StarSystem planetSystem, Coefficient min, int star, int planet, int weight, Graph graph){
        long additive = graph.getVertices().get(min.getStar()).getWeight() *
                graph.getEdgeMatrix().getCell(min.getStar(),min.getSatellite());

        if(weight < heaviest.getWeight() + additive){
            starSystem.remove(star,0);
            planetSystem.remove(planet,0);
            galaxy.getSystems().add(
                    new StarSystem(star,Lists.newArrayList(planet), weight)
            );
            return galaxy;
        }
        return galaxy;
    }

}
