package com.company.services;

import com.company.parsers.GraphParser;
import com.company.StarPlan;
import com.company.entities.Graph;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;

@Component
public class GraphService
{
    private Graph initialGraph;
    private StarPlan endSolutionStarPlan;
    private static final String graphFile = "C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\graph.txt";

    public void putInitialGraph(Graph initialGraph){
        this.initialGraph = initialGraph;
    }

    @PostConstruct
    public void init() throws FileNotFoundException
    {
        initialGraph = GraphParser.parseFile(graphFile);
    }
    public Graph getInitialGraph(){
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
