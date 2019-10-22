package com.company.services;

import com.company.dto.GraphDTO;
import com.company.services.builders.GraphDTOBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainService
{
    private GraphDTOBuilder graphDTOBuilder;

    @Autowired
    public MainService(GraphDTOBuilder graphDTOBuilder)
    {
        this.graphDTOBuilder = graphDTOBuilder;
    }

    public GraphDTO getCurrentSolution(){

        return graphDTOBuilder.build();
    }

    public GraphDTO getEndSolution(){
        return graphDTOBuilder.build();
    }
}
