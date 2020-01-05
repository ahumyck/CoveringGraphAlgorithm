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
    public int criterion(@NotNull Galaxy galaxy, @NotNull Graph graph) {
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
                .max(Comparator.comparingInt(Coefficient::getWeight))
                .ifPresent(it -> heaviest = it);
        return heaviest.getSatellite();
    }

    @Override
    public Galaxy rebase(@NotNull Galaxy galaxy, @NotNull Graph graph, int reserve) {
        EdgeMatrix edgeMatrix = graph.getEdgeMatrix();
        List<Vertex> vertices = graph.getVertices();

        int possibleStar = heaviest.getSatellite();
        int possibleStarWeight = vertices.get(possibleStar).getWeight();

        List<Coefficient> possibleStarList = new ArrayList<>();
        List<Coefficient> possiblePlanetList = new ArrayList<>();
        for (StarSystem system:
                galaxy.getSystems()) {
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
            makeChange(galaxy,star,possibleStar,star.getSatellite(),reserve);
        }
        else{
            makeChange(galaxy,planet,planet.getSatellite(),possibleStar,reserve);
        }

        return galaxy.calculateWeight(graph);
    }
    
    @NotNull
    private Optional<Coefficient> min(@NotNull List<Coefficient> coefficients){
        return coefficients.stream().min(Comparator.comparingInt(Coefficient::getWeight));
    }

    private void makeChange(Galaxy galaxy,Coefficient information, int possibleStar,int possiblePlanet, int reserve){
        StarSystem oldSystem = galaxy.getSystems()
                .stream()
                .filter(system -> system.getStar() == information.getStar())
                .findFirst()
                .get();
//        System.out.println("oldSystem: " + oldSystem);
        if(oldSystem.getPlanets().size() > 1){
//            System.out.println("newSystem weight: " + information.getWeight() + " vs " + reserve);
            if(information.getWeight() < reserve) {
                StarSystem starSystem = new StarSystem(possibleStar, Lists.newArrayList(possiblePlanet), information.getWeight());
                galaxy.getSystems().add(starSystem);
                galaxy.addWeight(starSystem.getWeight());

                oldSystem.remove(information.getSatellite(),information.getWeight());
            }
        }
    }

}
