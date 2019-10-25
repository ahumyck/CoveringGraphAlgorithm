package com.company.services;

import com.company.Parser;
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
    private static final String matrixFile = "C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\matrix.txt";
    private static final String vertexFile = "C:\\Users\\Илья\\Desktop\\nauchka_sb\\CoveringGraphAlgorithm\\src\\main\\resources\\matrixData\\vertexWeight.txt";

    public void putInitialGraph(Graph initialGraph){
        this.initialGraph = initialGraph;
    }

    @PostConstruct
    public void init() throws FileNotFoundException
    {
        initialGraph = Parser.parseGraph(vertexFile,matrixFile);
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
