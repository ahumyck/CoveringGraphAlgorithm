package com.company;

import com.company.entities.Graph;
import com.company.entities.Vertex;
import com.company.generators.SatellitePermutationGeneratorUsingStars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//todo: refactor this shit
public class SmartMatrixWrapper {
    private List<Integer> starList;
    private List<Integer> satelliteList;
    private Graph graph;
    private SatellitePermutationGeneratorUsingStars generator;


    public SmartMatrixWrapper(List<Integer> starList, Graph graph) {
        this.starList = starList;
        this.satelliteList = new ArrayList<>();

        satelliteList = graph.getVertices().stream()
                .sequential()
                .filter(vertex -> !starList.contains(vertex.getId()))
                .map(Vertex::getId).collect(Collectors.toList());

        generator = new SatellitePermutationGeneratorUsingStars(satelliteList.size(), starList.size());
        this.graph = graph;
    }

    public StarPlan calculateMinimizationFunction() {
        ArrayList<Integer> validSolution = generator.next();
        StarPlan basePlan = calculateCurrentPermutation(validSolution);
        while ((validSolution = generator.next()) != null) {
            StarPlan currentSolution = calculateCurrentPermutation(validSolution);
            if (currentSolution.getCost() < basePlan.getCost()) {
                basePlan = currentSolution;
            }
        }
        return basePlan;
    }

    //todo: refactor naming
    private StarPlan calculateCurrentPermutation(ArrayList<Integer> solution) {
        int count = 0;
        Map<Vertex, List<Vertex>> map = new HashMap<>();
        for (int i = 0; i < satelliteList.size(); i++) {
            Vertex satellite = graph.getVertices().get(satelliteList.get(i));
            Vertex star = graph.getVertices().get(starList.get(solution.get(i)));
            count += graph.getVertices().get(star.getId()).getWeight() * graph.getEdgeMatrix().getCell(satellite.getId(), star.getId());
            if (map.get(star) == null) {
                List<Vertex> list = new ArrayList<>();
                list.add(satellite);
                map.put(star, list);
            } else {
                map.get(star).add(satellite);
            }
        }
        return new StarPlan(map, count);

    }
}
