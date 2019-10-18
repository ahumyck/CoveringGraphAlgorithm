package com.company;

import java.util.ArrayList;
import java.util.List;

public class SmartMatrixWrapper {
    private int[] starList;
    private List<Integer> satelliteList;
    private List<Vertex> newGraph;
    private int satelliteCount;


    public SmartMatrixWrapper(int[] starList, List<Vertex> graph) {
        this.starList = starList;
        satelliteList = new ArrayList<>(graph.size() - starList.length);
        newGraph = new ArrayList<>(graph);

        for (int i = starList.length - 1; i >= 0 ; i--) {
            satelliteList.add(graph.remove(starList[i]).getId());
        }
        System.out.println(satelliteList);
    }

    public List<Vertex> getL(){
        return null;
    }
}
