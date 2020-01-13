package com.company.services;


import com.company.dto.GraphDTO;
import com.company.dto.request.InitialGraphRequestBody;
import com.company.entities.Graph;
import com.company.services.builders.graphBuilders.GraphBuilderByInitialGraphRequest;
import com.company.services.builders.graphBuilders.GraphDTOByGraphBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitialGraphService {
    private GraphDTOByGraphBuilder graphDTOByGraphBuilder;

    @Autowired
    public InitialGraphService(GraphDTOByGraphBuilder graphDTOByGraphBuilder) {
        this.graphDTOByGraphBuilder = graphDTOByGraphBuilder;
    }

    public GraphDTO getInitialGraph(InitialGraphRequestBody body){
        Graph graph = new GraphBuilderByInitialGraphRequest().build(body.getNodeWeights(), body.getMatrix());
        return graphDTOByGraphBuilder.build(graph);
    }
}
