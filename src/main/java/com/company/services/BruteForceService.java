package com.company.services;

import com.company.BruteForceManager;
import com.company.dto.GraphDTO;
import com.company.dto.request.ExecuteRequestBody;
import com.company.services.graphBuilders.GraphDTOByGraphBuilder;
import com.company.services.graphBuilders.GraphDTOByStarPlanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BruteForceService
{
    private BruteForceManager bruteForceManager;
    private GraphDTOByStarPlanBuilder graphDTOByStarPlanBuilder;
    private GraphDTOByGraphBuilder graphDTOByGraphBuilder;
    private CacheService cacheService;
    private GraphService graphService;
    private static volatile boolean executing = false;

    @Autowired
    public BruteForceService(BruteForceManager bruteForceManager, GraphDTOByStarPlanBuilder graphDTOByStarPlanBuilder, GraphDTOByGraphBuilder graphDTOByGraphBuilder, CacheService cacheService, GraphService graphService)
    {
        this.bruteForceManager = bruteForceManager;
        this.graphDTOByStarPlanBuilder = graphDTOByStarPlanBuilder;
        this.graphDTOByGraphBuilder = graphDTOByGraphBuilder;
        this.cacheService = cacheService;
        this.graphService = graphService;
    }

    public GraphDTO getCurrentSolution(Integer solutionNumber){
        return graphDTOByStarPlanBuilder.build(cacheService.getStarPlan(solutionNumber));
    }

    public void executeBruteForce(ExecuteRequestBody executeRequestBody) throws Exception
    {
        if(executing) throw new Exception("already executing");
        graphService.setEndSolutionStarPlan(null);
        executing = true;
        graphService.setEndSolutionStarPlan(bruteForceManager.bruteForce(graphService.takeInitialGraph(), executeRequestBody.getStarsCount()));
        executing = false;
    }

    public GraphDTO getEndSolution() throws Exception
    {

//        manager.bruteForce(Parser.parseGraph(), 3);
//        return graphDTOBuilder.build();
        if(graphService.getEndSolutionStarPlan() == null) throw new Exception("it is not time yet");
        return  graphDTOByStarPlanBuilder.build(graphService.getEndSolutionStarPlan());
    }

    //todo: научить отсылать граф
    public GraphDTO getGraph(){
        return null;
    }
}
