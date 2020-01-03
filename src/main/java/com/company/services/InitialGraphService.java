package com.company.services;


import com.company.dto.GraphDTO;
import com.company.dto.request.InitialGraphRequestBody;
import com.company.services.builders.graphBuilders.GraphDTOByGraphBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitialGraphService {
    private GraphDTOByGraphBuilder graphDTOByGraphBuilder;
    private GraphService graphService;

    @Autowired
    public InitialGraphService(GraphDTOByGraphBuilder graphDTOByGraphBuilder, GraphService graphService) {
        this.graphDTOByGraphBuilder = graphDTOByGraphBuilder;
        this.graphService = graphService;
    }

    public GraphDTO getInitialGraph(InitialGraphRequestBody initialGraphRequestBody){
        graphService.calculateInitialGraph(initialGraphRequestBody.getNodeWeights(),initialGraphRequestBody.getMatrix());
        return graphDTOByGraphBuilder.build(graphService.takeInitialGraph());
    }
}
