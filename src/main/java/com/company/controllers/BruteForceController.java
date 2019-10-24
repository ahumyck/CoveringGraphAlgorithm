package com.company.controllers;

import com.company.dto.GraphDTO;
import com.company.dto.request.ExecuteRequestBody;
import com.company.services.BruteForceService;
import com.company.utils.ApiPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.BRUTE_FORSE_PATH)
public class BruteForceController
{
    private BruteForceService bruteForceService;

    @Autowired
    public BruteForceController(BruteForceService bruteForceService)
    {
        this.bruteForceService = bruteForceService;
    }

    @RequestMapping(method = RequestMethod.GET, value = ApiPaths.SOLUTION_PATH + ApiPaths.NUMBER_PATH)
    public GraphDTO getCertainSolution(@PathVariable Integer number){
        return bruteForceService.getCurrentSolution(number);
    }

    @RequestMapping(method = RequestMethod.POST, value = ApiPaths.EXECUTE_PATH)
    public void executeBruteForce(@RequestBody ExecuteRequestBody executeRequestBody) throws Exception
    {
        bruteForceService.executeBruteForce(executeRequestBody);
    }

    @RequestMapping(method = RequestMethod.GET, value = ApiPaths.SOLUTION_PATH + ApiPaths.END_PATH)
    public GraphDTO getEndSolution() throws Exception
    {
        return bruteForceService.getEndSolution();
    }

    @RequestMapping(method = RequestMethod.GET, value = ApiPaths.INITIAL_GRAPH_PATH)
    public GraphDTO getInitialGraph() {
        return bruteForceService.getInitialGraph();
    }
}
