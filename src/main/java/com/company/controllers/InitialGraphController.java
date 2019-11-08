package com.company.controllers;

import com.company.dto.GraphDTO;
import com.company.dto.request.InitialGraphRequestBody;
import com.company.services.InitialGraphService;
import com.company.utils.ApiPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.INITIAL_GRAPH_PATH)
public class InitialGraphController {

    private InitialGraphService initialGraphService;

    @Autowired
    public InitialGraphController(InitialGraphService initialGraphService)
    {
        this.initialGraphService = initialGraphService;
    }

    @RequestMapping(method = RequestMethod.POST, value = ApiPaths.POST_FILE)
    public GraphDTO getGraphFromFile(@RequestBody InitialGraphRequestBody initialGraphRequestBody){
        return initialGraphService.getInitialGraph(initialGraphRequestBody);
    }
}
