package com.company.services;

import com.company.GreedyAlgorithm;
import com.company.dto.GraphDTO;
import com.company.dto.request.ExecuteRequestBody;
import com.company.entities.Graph;
import com.company.services.builders.graphBuilders.GraphDTOByMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
public class GreedyService {

    private GraphService graphService;

    @Autowired
    public GreedyService(GraphService graphService) {
        this.graphService = graphService;
    }

    public Optional<GraphDTO> solveGreedy(ExecuteRequestBody body) {
        Optional<Graph> optionalGraph = graphService.takeInitialGraph();
        if(optionalGraph.isPresent()){
            Graph graph = optionalGraph.get();
            int stars = body.getStarsCount();
            Map<Integer, ArrayList<Integer>> answer;
            if(stars > 0){
                GreedyAlgorithm.setMaximumStars(stars);
                answer = GreedyAlgorithm.solve(graph);
                GreedyAlgorithm.setDefault();
            }
            else{
                answer = GreedyAlgorithm.solve(graph);
            }
            return Optional.of(new GraphDTOByMapBuilder().build(answer));

        }
        return Optional.empty();
    }


}
