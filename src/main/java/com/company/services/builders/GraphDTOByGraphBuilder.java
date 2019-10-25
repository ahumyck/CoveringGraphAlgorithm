package com.company.services.builders;

import com.company.dto.DataDTO;
import com.company.dto.EdgeDTO;
import com.company.dto.GraphDTO;
import com.company.dto.NodeDTO;
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
            for (int i = 0; i < graph.getEdgeMatrix().getSize() - j; i++)
            {
                edgeDTOList.add(new EdgeDTO("a", i, j));
            }
        }
        return edgeDTOList;
    }

    private List<NodeDTO> buildNodeDTOList(Graph graph)
    {
        final String blackColor = "#000000";
        List<NodeDTO> nodeDTOList = new LinkedList<>();
        for (Vertex vertex: graph.getVertices()
             )
        {
            nodeDTOList.add(new NodeDTO(vertex.getId(), String.valueOf(vertex.getId()), new DataDTO(blackColor)));
        }
        return nodeDTOList;
    }
}
