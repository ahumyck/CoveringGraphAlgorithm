package com.company.universe.galaxyMutator.FromPlanetToStarMutators.HeavyConnection;

import com.company.entities.Coefficient;
import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;
import com.company.universe.Galaxy;
import com.company.universe.StarSystem;
import com.company.universe.galaxyMutator.FromPlanetToStarMutators.relocation.Relocation;
import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;

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
        
        Coefficient star = min(possibleStarList).get();
        Coefficient planet = min(possiblePlanetList).get();

        if(star.getWeight() < planet.getWeight()){
            return makeChange(originalGalaxy, cloneGalaxy, star, possibleStar, star.getSatellite(), graph);
        }
        else{
            return makeChange(originalGalaxy, cloneGalaxy, planet, planet.getSatellite(), possibleStar,graph);
        }
    }
    
    @NotNull
    private Optional<Coefficient> min(List<Coefficient> coefficients){
        return coefficients.stream().min(Comparator.comparingLong(Coefficient::getWeight));
    }

    private Galaxy makeChange(Galaxy originalGalaxy, Galaxy cloneGalaxy,Coefficient information, int possibleStar,int possiblePlanet,Graph graph){
        StarSystem possibleStarSystem = findSystemByPlanet(cloneGalaxy,possibleStar).get();
        StarSystem possiblePlanetSystem = findSystemByPlanet(cloneGalaxy,possiblePlanet).get();
        if(possibleStarSystem.getPlanets().size() > 1 || possiblePlanetSystem.getPlanets().size() > 1){
//            System.out.println(information.getWeight() + " vs " + heaviest.getWeight());
            if(information.getWeight() < heaviest.getWeight()) {
//                System.out.println("possible star: " + possibleStar);
//                System.out.println("possible planet: " + possiblePlanet);
//                System.out.println("weight: " + information.getWeight());
//
//                System.out.println("possibleStarSystem: " + possibleStarSystem);
//                System.out.println("possiblePlanetSystem: " + possiblePlanetSystem);
//                System.out.println("bad connection: " + heaviest.getStar() + " " + heaviest.getSatellite() + " has weight " + heaviest.getWeight());
//                System.out.println("that's a win");
//                System.out.println();

                possiblePlanetSystem.remove(possiblePlanet,0);
                possibleStarSystem.remove(possibleStar,0);
                cloneGalaxy.getSystems().add(new StarSystem(possibleStar,Lists.newArrayList(possiblePlanet),information.getWeight()));
                return cloneGalaxy.orderByWeight().calculateWeight(graph);
            }
        }
        return originalGalaxy;
    }

    private Optional<StarSystem> findSystemByStar(Galaxy galaxy, int star){
        return galaxy.getSystems()
                .stream()
                .filter(system -> system.getStar() == star)
                .findFirst();
    }

    private Optional<StarSystem> findSystemByPlanet(Galaxy galaxy, int planet){
        return galaxy.getSystems()
                .stream()
                .filter(system -> system.getPlanets().contains(planet))
                .findFirst();
    }

}
