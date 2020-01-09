package com.company.services.builders.arrayBuilder;

import com.company.entities.Graph;
import com.company.genetic.Array;
import com.company.universe.Galaxy;
import com.company.universe.StarSystem;

public class ArrayDTOBuilderByGalaxy implements ArrayDTOBuilder<Galaxy, Graph> {
    @Override
    public Array build(Galaxy input, Graph graph) {
        int[] array = new int[graph.size()];
        for (StarSystem system:
             input.getSystems()) {
            array[system.getStar()] = -1;
            for (int planet:
                 system.getPlanets()) {
                array[planet] = system.getStar();
            }
        }
        return new Array(array);
    }
}
