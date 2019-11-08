package com.company.services;

import com.company.entities.EdgeMatrix;
import com.company.entities.Vertex;
import com.company.parsers.GraphParser;
import com.company.StarPlan;
import com.company.entities.Graph;
import com.company.services.builders.GraphBuilderByInitialGraph;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.ArrayList;

@Component
public class GraphService
{
    private Graph initialGraph;
    private StarPlan endSolutionStarPlan;

    public void putInitialGraph(Graph initialGraph){
        this.initialGraph = initialGraph;
    }

    public void calculateInitialGraph(int[] nodeWeights, int[][] matrix) {
        this.initialGraph = new GraphBuilderByInitialGraph().build(nodeWeights,matrix);
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
