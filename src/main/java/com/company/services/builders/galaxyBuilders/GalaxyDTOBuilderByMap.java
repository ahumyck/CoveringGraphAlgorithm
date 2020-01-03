package com.company.services.builders.galaxyBuilders;

import com.company.entities.Graph;
import com.company.universe.Galaxy;
import com.company.universe.StarSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GalaxyDTOBuilderByMap implements GalaxyDTOBuilder<Map<Integer, ArrayList<Integer>>,Graph> {
    @Override
    public Galaxy build(Map<Integer, ArrayList<Integer>> input, Graph graph) {
        List<StarSystem> systems = new ArrayList<>();
        for (Integer key:
             input.keySet()) {
            systems.add(new StarSystem(key,input.get(key),0));
        }
        Galaxy galaxy = new Galaxy(systems);
        galaxy.calculateWeight(graph);
        return galaxy;
    }
}
