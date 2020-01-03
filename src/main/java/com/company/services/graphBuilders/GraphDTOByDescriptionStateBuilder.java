package com.company.services.graphBuilders;

import com.company.perm_generic_algorithm.StarSatelliteDescriptionState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphDTOByDescriptionStateBuilder implements  StarPlanBuilder<StarSatelliteDescriptionState> {
    @Override
    public Map<Integer, ArrayList<Integer>> build(StarSatelliteDescriptionState input) {
        Map<Integer,ArrayList<Integer>> map = new HashMap<>();
        int[] binaryCode = input.getBinaryCode();
        int[] nodes = input.getNodes();

        int currentStar = nodes[0];
        for(int i = 0 ; i < nodes.length; i++){
            if(binaryCode[i] == 1){
                currentStar = nodes[i];
                map.put(currentStar, new ArrayList<Integer>());
            }
            else {
                map.get(currentStar).add(nodes[i]);
            }
        }
        return map;
    }
}
