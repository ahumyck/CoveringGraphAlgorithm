package com.company.services.builders;

import com.company.dto.EdgeDTO;
import com.company.dto.GraphDTO;
import com.company.dto.NodeDTO;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class GraphDTOBuilder
{
    public GraphDTO build()
    {
        return new GraphDTO();
    }

    private List<EdgeDTO> buildEdgeDTOList()
    {
        List<EdgeDTO> edgeDTOList = new LinkedList<>();

        return edgeDTOList;
    }

    private List<NodeDTO> buildNodeDTOList()
    {
        List<NodeDTO> edgeDTOList = new LinkedList<>();

        return edgeDTOList;
    }
}
