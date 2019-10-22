package com.company;

import com.company.generators.SatellitePermutationGeneratorUsingStars;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//todo: refactor this shit
public class SmartMatrixWrapper {
    private List<Integer> starList;
    private List<Integer> satelliteList;
    private List<Vertex> graph;
    private SatellitePermutationGeneratorUsingStars generator;


    public SmartMatrixWrapper(List<Integer> starList, List<Vertex> graph) {
        this.starList = starList;
        this.satelliteList = new ArrayList<>();

        satelliteList = graph.stream()
                .filter(vertex -> !starList.contains(vertex.getId()))
                .map(Vertex::getId).collect(Collectors.toList());

        generator = new SatellitePermutationGeneratorUsingStars(satelliteList.size(),starList.size());
        this.graph = graph;
    }

    public StarPlan calculateMinimizationFunction() {
        ArrayList<Integer> validSolution = generator.next();
        StarPlan basePlan = calculateCurrentPermutation(validSolution);
        while((validSolution = generator.next()) != null){
            StarPlan currentSolution = calculateCurrentPermutation(validSolution);
            if(currentSolution.getCost() < basePlan.getCost())
                basePlan = currentSolution;
        }
        return basePlan;
    }

    //todo: refactor naming
    private StarPlan calculateCurrentPermutation(ArrayList<Integer> solution){
        int count = 0;
        StringBuilder builder = new StringBuilder("Star Plan{\n");
        for (int i = 0; i < satelliteList.size() ; i++)
        {
            Vertex satellite = graph.get(satelliteList.get(i));
            int starIndex = starList.get(solution.get(i));


            for (Edge edge: satellite.getEdges())
            {
                if(edge.getSuccessorId() == starIndex) {
                    int starWeight = graph.get(starIndex).getWeight();
                    int edgeWeight = edge.getWeight();
                    builder.append("Star ").append(starIndex)
                            .append(" is connected to satellite ").append(satellite.getId())
                            .append(" with weight ").append(edgeWeight).append('\n');
                    count += starWeight * edgeWeight;
                }
            }
        }
        builder.append('}').insert(9,"has cost " + count);
        return new StarPlan(builder.toString(),count);
    }
}
