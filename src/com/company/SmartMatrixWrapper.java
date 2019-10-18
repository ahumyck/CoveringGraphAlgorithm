package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SmartMatrixWrapper {
    private List<Integer> starList;
    private List<Integer> satelliteList;
    private List<Vertex> graph;
    private Matrix matrix;


    public SmartMatrixWrapper(List<Integer> starList, List<Vertex> graph) {
        this.starList = starList;
        this.satelliteList = new ArrayList<Integer>();

        satelliteList = graph.stream()
                .filter(vertex -> !starList.contains(vertex.getId()))
                .map(Vertex::getId).collect(Collectors.toList());

        matrix = new Matrix(satelliteList.size(),starList.size());
        this.graph = graph;
    }

    //todo: dostat star i poschitat vrychnyu
    public void getL() throws Exception{
        ArrayList<Integer> validSolution = matrix.next(); // [2,1,0,0,0];

        int count = 0;

        for (int i = 0; i < satelliteList.size() ; i++) {
            Vertex satellite = graph.get(satelliteList.get(i));
            int starIndex = starList.get(validSolution.get(i));
            System.out.println("Satellite: " + satellite.getId());
            System.out.println("Star index: " + starIndex);
            for (Edge edge:
                 satellite.getEdges()) {
                System.out.println("Edge{" + edge.getSuccessorId() + "}{" + edge.getPredecessorId() + "} = "  + edge.getWeight());
                if(edge.getSuccessorId() == starIndex){
                    System.out.println("complete");
                    count += edge.getWeight(); //todo: multiply by star weight
                }
            }
            System.out.println();
        }


        System.out.println("counter: " + count);
    }
}
