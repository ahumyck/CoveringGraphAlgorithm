package com.company.services.builders.galaxyBuilders;

import com.company.entities.Graph;
import com.company.genetic.universe.Galaxy;
import com.company.genetic.universe.StarSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GalaxyBuilderByMap implements GalaxyBuilder<Map<Integer, ArrayList<Integer>>,Graph> {
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
