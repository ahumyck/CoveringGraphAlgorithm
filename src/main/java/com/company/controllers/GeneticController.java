package com.company.controllers;


import com.company.dto.dtoEntites.GraphDTO;
import com.company.dto.dtoEntites.GraphDataDTO;
import com.company.genetic.settings.Settings;
import com.company.services.GeneticService;
import com.company.utils.ApiPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(ApiPaths.GENETIC_PATH)
public class GeneticController {

    private GeneticService geneticService;

    @Autowired
    public GeneticController(GeneticService geneticService) {
        this.geneticService = geneticService;
    }

    //todo: add @RequestBody Settings settings
    @PostMapping
    public Optional<GraphDataDTO> solveGenetic(HttpServletResponse response) throws IOException {
        Optional<GraphDataDTO> graphDTO = geneticService.solveGenetic(Settings.DEFAULT);
        if(graphDTO.isPresent()){
            response.setStatus(200);
        }
        else{
            response.sendError(404,"You dumbass forgot graph");
        }
        return graphDTO;
    }
}
