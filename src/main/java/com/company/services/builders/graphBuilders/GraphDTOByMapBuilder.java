package com.company.services.builders.graphBuilders;

import com.company.dto.dtoEntites.*;
import com.company.dto.dtoEntites.EdgeDTO;
import com.company.dto.dtoEntites.NodeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphDTOByMapBuilder implements GraphDTOBuilder<Map<Integer, ArrayList<Integer>>> {

    private static final String satelliteColor = "#00FF00";
    private static final String starColor = "#FF0000";
    private static final String edgeColor = "#888888";

    @Override
    public GraphDTO build(Map<Integer, ArrayList<Integer>> input) {
        return new GraphDTO(buildNodeDTOList(input),
                buildEdgeDTOList(input));
    }

    private List<NodeDataDTO> buildNodeDTOList(Map<Integer,ArrayList<Integer>> map){
        List<NodeDataDTO> nodes = new ArrayList<>();
        for (int star : map.keySet()) {
            nodes.add(getNode(star,starColor));
            for (int satellite: map.get(star)) {
                nodes.add(getNode(satellite,satelliteColor));
            }
        }
        return nodes;
    }

    private List<EdgeDataDTO> buildEdgeDTOList(Map<Integer,ArrayList<Integer>> map){
        List<EdgeDataDTO> edges = new ArrayList<>();
        for (int star : map.keySet()) {
            for (int satellite: map.get(star)) {
                edges.add(getEdge(satellite,star));
            }
        }
        return edges;
    }

    private NodeDataDTO getNode(int value, String color){
        return new NodeDataDTO(
                new NodeDTO(
                        String.valueOf(value + 1), String.valueOf(value + 1), color)
        );
    }

    private EdgeDataDTO getEdge(int from, int to){
        return new EdgeDataDTO(
                new EdgeDTO(
                        String.valueOf(from + 1), String.valueOf(to + 1), edgeColor
                )
        );
    }

}
