package com.company.controllers;

import com.company.dto.GraphDTO;
import com.company.services.MainService;
import com.company.utils.ApiPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.BRUTE_FORSE_PATH)
public class MainController
{
    private MainService mainService;

    @Autowired
    public MainController(MainService mainService)
    {
        this.mainService = mainService;
    }

    @RequestMapping(method = RequestMethod.GET, value = ApiPaths.NEXT_SOLUTION)
    public GraphDTO getCurrentSolution(){
        return mainService.getCurrentSolution();
    }

    public GraphDTO getEndSolution(){
        return mainService.getEndSolution();
    }
}
