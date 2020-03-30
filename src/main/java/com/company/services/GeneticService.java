package com.company.services;

import com.company.GreedyAlgorithm;
import com.company.dto.dtoEntites.GraphDTO;
import com.company.dto.dtoEntites.GraphDataDTO;
import com.company.entities.Graph;
import com.company.genetic.Genetic;
import com.company.genetic.settings.Settings;
import com.company.services.builders.graphBuilders.GraphDTOByMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
public class GeneticService {

    private GraphService graphService;

    @Autowired
    public GeneticService(GraphService graphService) {
        this.graphService = graphService;
    }

    public Optional<GraphDataDTO> solveGenetic(Settings settings){
        Optional<Graph> optionalGraph = graphService.takeInitialGraph();
        if(optionalGraph.isPresent()){
            Graph graph = optionalGraph.get();
            Genetic genetic = new Genetic(graph,settings);
            Map<Integer, ArrayList<Integer>> solve = genetic.solve();
            GraphDTO geneticSolution = new GraphDTOByMapBuilder().build(solve);
            int cost = GreedyAlgorithm.calculate(solve, graph);
            return Optional.of(new GraphDataDTO(geneticSolution,cost));
        }
        return Optional.empty();
    }
}
