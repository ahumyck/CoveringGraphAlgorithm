package com.company.services.builders.galaxyBuilders;

import com.company.entities.Graph;
import com.company.genetic.Array;
import com.company.genetic.universe.Galaxy;
import com.company.genetic.universe.StarSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GalaxyBuilderByCustomArray implements GalaxyBuilder<Array, Graph> {
    @Override
    public Galaxy build(Array input, Graph graph){
        List<StarSystem> systems = new ArrayList<>();
        int[] array = input.getArray();
        for(int i = 0 ; i < array.length; i++) {
            if (array[i] == -1) {
                systems.add(new StarSystem(
                        i
                ));
            }
        }

        for(int i = 0 ; i < array.length; i++){
            if(array[i] != -1){
                int star = array[i];
                Optional<StarSystem> first = systems.stream().filter(system -> system.getStar() == star).findFirst();
                if(first.isPresent()){
                    StarSystem starSystem = first.get();
                    starSystem.add(i,0);
                }
            }
        }
        return new Galaxy(systems).calculateWeight(graph).orderByWeight();
    }
}
