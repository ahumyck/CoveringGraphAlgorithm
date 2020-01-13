package com.company.services;

import com.company.entities.Graph;
import com.company.services.builders.graphBuilders.GraphBuilderByInitialGraphRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GraphService
{
    private Graph initialGraph;

    public void putInitialGraph(Graph initialGraph){
        this.initialGraph = initialGraph;
    }

    public Graph calculateInitialGraph(int[] nodeWeights, int[][] matrix) {
        this.initialGraph = new GraphBuilderByInitialGraphRequest().build(nodeWeights,matrix);
        return this.initialGraph;
    }

    public Optional<Graph> takeInitialGraph(){
        return Optional.of(initialGraph);
    }
}