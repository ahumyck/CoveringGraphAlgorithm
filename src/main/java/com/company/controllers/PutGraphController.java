package com.company.controllers;

import com.company.dto.dtoEntites.GraphDTO;
import com.company.entities.Graph;
import com.company.parsers.GraphParser;
import com.company.services.GraphService;
import com.company.services.PutGraphService;
import com.company.utils.ApiPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


/**
 * Контроллер отвечающий за обработку графа, присланного в виде файла
 * после обработки отсылает данные на фронт, чтобы англуяр отрисовать граф
 * бэк запоминает его для дальнейших необходимых действий(например решить его жадиной или генетическим алгоритмом)
 * пример файла, который приходит
 * /resources/matrixData/test_graph_0.txt
 */
@RestController
@RequestMapping(ApiPaths.INITIAL_GRAPH_PATH)
public class PutGraphController {

    private PutGraphService putGraphService;
    private GraphService graphService;

    @Autowired
    public PutGraphController(PutGraphService putGraphService, GraphService graphService)
    {
        this.putGraphService = putGraphService;
        this.graphService = graphService;
    }

    @RequestMapping(method = RequestMethod.POST, value = ApiPaths.POST_FILE_PATH)
    public Optional<GraphDTO> getGraphFromFile(@RequestBody String body){
        Graph graph = GraphParser.parseString(body);
        graphService.putInitialGraph(graph);
        return putGraphService.getInitialGraph(graph);
    }
}
