package com.company.services.builders;

import com.company.dto.*;
import com.company.entities.Graph;
import com.company.entities.Vertex;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class GraphDTOByGraphBuilder implements GraphDTOBuilder<Graph>
{
//    private String VERTEX_LABEL ="";

    @Override
    public GraphDTO build(Graph graph)
    {
        return new GraphDTO(buildNodeDTOList(graph),
                buildEdgeDTOList(graph));
    }

    private List<EdgeDTO> buildEdgeDTOList(Graph graph)
    {
        List<EdgeDTO> edgeDTOList = new LinkedList<>();
        for (int j = 0; j < graph.getEdgeMatrix().getSize(); j++)
        {
            for (int i = j + 1; i < graph.getEdgeMatrix().getSize(); i++)
            {
                //todo: pridymat chto-to s id
                edgeDTOList.add(new EdgeDTO("a", String.valueOf(j + 1), String.valueOf(i + 1)));
            }
        }
        return edgeDTOList;
    }

    private List<NodeDTO> buildNodeDTOList(Graph graph)
    {
        final String backgroundColor = "#00BFFF";
        List<NodeDTO> nodeDTOList = new LinkedList<>();
        for (Vertex vertex: graph.getVertices()
             )
        {
            nodeDTOList.add(new NodeDTO(String.valueOf(vertex.getId() + 1), String.valueOf(vertex.getId() + 1), new DataDTO(backgroundColor)));
//                    ,new NodePositionDTO(vertex.getId()*10,vertex.getId()*10),
        }
        return nodeDTOList;
    }
}
