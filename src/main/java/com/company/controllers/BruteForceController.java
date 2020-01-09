package com.company.controllers;

import com.company.dto.GraphDTO;
import com.company.dto.request.ExecuteRequestBody;
import com.company.services.BruteForceService;
import com.company.utils.ApiPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.BRUTE_FORCE_PATH)
public class BruteForceController
{
    private BruteForceService bruteForceService;

    @Autowired
    public BruteForceController(BruteForceService bruteForceService)
    {
        this.bruteForceService = bruteForceService;
    }

    @RequestMapping(method = RequestMethod.GET, value = ApiPaths.SOLUTION_PATH + ApiPaths.NUMBER_PATH)
    public @ResponseStatus(HttpStatus.OK) GraphDTO getCertainSolution(@PathVariable Integer number){
        return bruteForceService.getCurrentSolution(number);
    }

    @RequestMapping(method = RequestMethod.POST, value = ApiPaths.EXECUTE_PATH)
    public @ResponseStatus(HttpStatus.OK) void executeBruteForce(@RequestBody ExecuteRequestBody executeRequestBody) throws Exception
    {
//        bruteForceService.(executeRequestBody);
    }

    @RequestMapping(method = RequestMethod.GET, value = ApiPaths.SOLUTION_PATH + ApiPaths.END_PATH)
    public @ResponseStatus(HttpStatus.OK) GraphDTO getEndSolution() throws Exception
    {
        return bruteForceService.getEndSolution();
    }
}
