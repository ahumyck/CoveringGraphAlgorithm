package com.company.services.builders;

import com.company.StarPlan;
import com.company.dto.DataDTO;
import com.company.dto.EdgeDTO;
import com.company.dto.GraphDTO;
import com.company.dto.NodeDTO;
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
                edgeDTOList.add(new EdgeDTO("a", star.getId(), satellite.getId()));
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
            nodeDTOList.add(new NodeDTO(star.getId(), STAR_LABEL + star.getId(), new DataDTO(starColor)));
            for (Vertex satellite : starPlan.getStarSatelliteMap().get(star))
            {
                nodeDTOList.add(new NodeDTO(satellite.getId(), SATELLITE_LABEL + satellite.getId(), new DataDTO(satelliteColor)));
            }
        }
        return nodeDTOList;
    }
}
