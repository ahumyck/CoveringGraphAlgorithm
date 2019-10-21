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

    //todo: dostat star i poschitat vrychnyu
    //todo: renamen nahui
    public void calculateMinimizationFunction() {
        ArrayList<Integer> validSolution = generator.next(); // [2,1,0,0,0];
        int count = 0;

//        System.out.println("Star list: " + starList);
//        System.out.println("Satellite list: " + satelliteList);
        for (int i = 0; i < satelliteList.size() ; i++)
        {
            Vertex satellite = graph.get(satelliteList.get(i));
            int starIndex = starList.get(validSolution.get(i));
            for (Edge edge: satellite.getEdges())
            {
                if(edge.getSuccessorId() == starIndex){
                    int starWeight = graph.get(starIndex).getWeight();
                    int edgeWeight = edge.getWeight();
//                    todo: ne ydalay etot debug block information, potom nyzhen bydet
//                    System.out.println("Star weight: " + starWeight + ", StarIndex" + starIndex);
//                    System.out.println("Edge weight: " + edgeWeight + ", Edge: " + edge.getPredecessorId() + ',' + edge.getSuccessorId());
//                    System.out.println("Total weight: " + starWeight * edgeWeight);
//                    System.out.println();
                    count += starWeight * edgeWeight; // count == 225
//                    todo: elsi bydyt voprosi pochemy 225 to y menya est kartinka so vsey herney, na sleduyshem mitinge obsudim
                }
            }
        }
        System.out.println("count: " + count);
    }
}
