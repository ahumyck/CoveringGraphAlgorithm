package com.company.services;


import com.company.dto.GraphDTO;
import com.company.dto.request.PutGraphRequestBody;
import com.company.services.builders.graphBuilders.GraphDTOByGraphBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PutGraphService {
    private GraphDTOByGraphBuilder graphDTOByGraphBuilder;
    private GraphService graphService;

    @Autowired
    public PutGraphService(GraphDTOByGraphBuilder graphDTOByGraphBuilder, GraphService graphService) {
        this.graphDTOByGraphBuilder = graphDTOByGraphBuilder;
        this.graphService = graphService;
    }

    public Optional<GraphDTO> getInitialGraph(PutGraphRequestBody body){
        return Optional.of(graphDTOByGraphBuilder.build(graphService.calculateInitialGraph(body.getNodeWeights(),body.getMatrix())));
    }
}
