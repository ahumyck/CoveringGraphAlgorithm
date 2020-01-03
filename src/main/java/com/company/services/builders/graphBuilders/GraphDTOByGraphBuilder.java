package com.company.services.builders.graphBuilders;

import com.company.dto.*;
import com.company.entities.Graph;
import com.company.entities.Vertex;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class GraphDTOByGraphBuilder implements GraphDTOBuilder<Graph>
{

    @Override
    public GraphDTO build(Graph graph)
    {
        return new GraphDTO(buildNodeDTOList(graph),
                buildEdgeDTOList(graph));
    }

    private List<EdgeDataDTO> buildEdgeDTOList(Graph graph)
    {
        final String edgeColor = "#000000";
        List<EdgeDataDTO> edgeDataDTOList = new LinkedList<>();
        for (int j = 0; j < graph.getEdgeMatrix().getSize(); j++)
        {
            for (int i = j + 1; i < graph.getEdgeMatrix().getSize(); i++)
            {
                edgeDataDTOList.add(new EdgeDataDTO(new EdgeDTO(String.valueOf(j + 1), String.valueOf(i + 1), edgeColor)));
            }
        }
        return edgeDataDTOList;
    }

    private List<NodeDataDTO> buildNodeDTOList(Graph graph)
    {
        final String backgroundColor = "#00BFFF";
        List<NodeDataDTO> nodeDTOList = new LinkedList<>();
        for (Vertex vertex: graph.getVertices())
        {
            nodeDTOList.add(new NodeDataDTO(new NodeDTO(String.valueOf(vertex.getId() + 1), String.valueOf(vertex.getId() + 1), backgroundColor)));
        }
        return nodeDTOList;
    }
}
