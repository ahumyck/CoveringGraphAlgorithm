package com.company.services;


import com.company.dto.dtoEntites.GraphDTO;
import com.company.entities.Graph;
import com.company.services.builders.graphBuilders.GraphDTOByGraphBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PutGraphService {
    private GraphDTOByGraphBuilder graphDTOByGraphBuilder;

    @Autowired
    public PutGraphService(GraphDTOByGraphBuilder graphDTOByGraphBuilder) {
        this.graphDTOByGraphBuilder = graphDTOByGraphBuilder;
    }

    public Optional<GraphDTO> getInitialGraph(Graph graph){
        return Optional.of(graphDTOByGraphBuilder.build(graph));
    }
}
