package com.company.services.graphBuilders;

import com.company.StarPlan;
import com.company.dto.*;
import com.company.entities.Vertex;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class GraphDTOByStarPlanBuilder implements GraphDTOBuilder<StarPlan>
{
//    private String STAR_LABEL ="STAR ";
//    private String SATELLITE_LABEL = "SATELLITE ";
    public GraphDTO build(StarPlan starPlan)
    {
        return new GraphDTO(buildNodeDTOList(starPlan),
                buildEdgeDTOList(starPlan));
    }

    private List<EdgeDataDTO> buildEdgeDTOList(StarPlan starPlan)
    {
        String edgeColor = "#000000";
        List<EdgeDataDTO> edgeDTOList = new LinkedList<>();
        for (Vertex star : starPlan.getStarSatelliteMap().keySet())
        {
            for (Vertex satellite : starPlan.getStarSatelliteMap().get(star))
            {
                if(star.getId() != satellite.getId())
                    edgeDTOList.add(new EdgeDataDTO(new EdgeDTO(String.valueOf(star.getId() + 1), String.valueOf(satellite.getId() + 1),edgeColor)));
            }
        }
        return edgeDTOList;
    }

    private List<NodeDataDTO> buildNodeDTOList(StarPlan starPlan)
    {
        //todo: refactor this however you want later
        final String satelliteColor = "#ff0000";
        final String starColor = "#00ff00";

        List<NodeDataDTO> nodeDTOList = new LinkedList<>();
//        System.out.println("builder:" + starPlan);
        for (Vertex star : starPlan.getStarSatelliteMap().keySet())
        {
            nodeDTOList.add(new NodeDataDTO(new NodeDTO(String.valueOf(star.getId() + 1),String.valueOf(star.getId() + 1),starColor)));
            for (Vertex satellite : starPlan.getStarSatelliteMap().get(star))
            {
                nodeDTOList.add(new NodeDataDTO(new NodeDTO(String.valueOf(satellite.getId() + 1),String.valueOf(satellite.getId() + 1),satelliteColor)));
            }
        }
        return nodeDTOList;
    }
}
