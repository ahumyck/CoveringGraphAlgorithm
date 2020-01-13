package com.company.controllers;

import com.company.dto.GraphDTO;
import com.company.dto.request.PutGraphRequestBody;
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
 * пример файла, который приходит
 * example /resources/matrixData/graph.json
 * класс, который хранит содержимое файла
 * @see PutGraphRequestBody
 *

 */
@RestController
@RequestMapping(ApiPaths.INITIAL_GRAPH_PATH)
public class PutGraphController {

    private PutGraphService service;

    @Autowired
    public PutGraphController(PutGraphService service)
    {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST, value = ApiPaths.POST_FILE_PATH)
    public Optional<GraphDTO> getGraphFromFile(@RequestBody PutGraphRequestBody body){
        return service.getInitialGraph(body);
    }
}
