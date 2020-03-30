package com.company.controllers;


import com.company.dto.dtoEntites.GraphDTO;
import com.company.dto.dtoEntites.GraphDataDTO;
import com.company.dto.request.ExecuteRequestBody;
import com.company.services.GreedyService;
import com.company.utils.ApiPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(ApiPaths.GREEDY_ALGORITHM_PATH)
public class GreedyAlgorithmController {


    private GreedyService greedyService;

    @Autowired
    public GreedyAlgorithmController(GreedyService greedyService) {
        this.greedyService = greedyService;
    }

    @PostMapping
    public Optional<GraphDataDTO> solveGreedy(HttpServletResponse response,
                                @RequestBody ExecuteRequestBody body) throws IOException {
        Optional<GraphDataDTO> graphDTO = greedyService.solveGreedy(body);
        if(graphDTO.isPresent()){
           response.setStatus(200);
        }
        else{
            response.sendError(404,"You dumbass forgot graph");
        }
        return graphDTO;
    }

}
