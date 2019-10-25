package com.company.services.builders;

import com.company.StarPlan;
import com.company.dto.*;
import com.company.entities.Vertex;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class GraphDTOByStarPlanBuilder implements GraphDTOBuilder<StarPlan>
{
    private String STAR_LABEL ="STAR ";
    private String SATELLITE_LABEL = "SATELLITE ";
    public GraphDTO build(StarPlan starPlan)
    {
        return new GraphDTO(buildNodeDTOList(starPlan),
                buildEdgeDTOList(starPlan));
    }

    private List<EdgeDTO> buildEdgeDTOList(StarPlan starPlan)
    {
        List<EdgeDTO> edgeDTOList = new LinkedList<>();
        for (Vertex star : starPlan.getStarSatelliteMap().keySet())
        {
            for (Vertex satellite : starPlan.getStarSatelliteMap().get(star))
            {
                if(star.getId() != satellite.getId())
                    edgeDTOList.add(new EdgeDTO("a", String.valueOf(star.getId() + 1), String.valueOf(satellite.getId() + 1)));
            }
        }
        return edgeDTOList;
    }

    private List<NodeDTO> buildNodeDTOList(StarPlan starPlan)
    {
        //todo: refactor this however you want later
        final String satelliteColor = "#ff0000";
        final String starColor = "#00ff00";

        List<NodeDTO> nodeDTOList = new LinkedList<>();
        System.out.println("builder:" + starPlan);
        for (Vertex star : starPlan.getStarSatelliteMap().keySet())
        {
            nodeDTOList.add(new NodeDTO(String.valueOf(star.getId() + 1), STAR_LABEL +(star.getId() + 1),new DataDTO(starColor)));
//                   , new NodePositionDTO(0,0),
            for (Vertex satellite : starPlan.getStarSatelliteMap().get(star))
            {
                nodeDTOList.add(new NodeDTO(String.valueOf(satellite.getId() + 1), SATELLITE_LABEL + (satellite.getId() + 1),new DataDTO(satelliteColor)));
//                        , new NodePositionDTO(star.getId()*10,star.getId() * 10),
            }
        }
        return nodeDTOList;
    }
}
