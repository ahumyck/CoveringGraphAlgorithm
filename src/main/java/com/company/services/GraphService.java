package com.company.services;

import com.company.StarPlan;
import com.company.entities.Graph;
import com.company.services.builders.GraphBuilderByInitialGraphRequest;
import org.springframework.stereotype.Component;

@Component
public class GraphService
{
    private Graph initialGraph;
    private StarPlan endSolutionStarPlan;

    public void putInitialGraph(Graph initialGraph){
        this.initialGraph = initialGraph;
    }

    public void calculateInitialGraph(int[] nodeWeights, int[][] matrix) {
        this.initialGraph = new GraphBuilderByInitialGraphRequest().build(nodeWeights,matrix);
    }

    public Graph takeInitialGraph(){
        return this.initialGraph;
    }

    public StarPlan getEndSolutionStarPlan()
    {
        return endSolutionStarPlan;
    }

    public void setEndSolutionStarPlan(StarPlan endSolutionStarPlan)
    {
        this.endSolutionStarPlan = endSolutionStarPlan;
    }
}
