package com.company.perm_generic_algorithm;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StarSatellitePoolGenerator {
    public static List<StarSatelliteDescriptionState> getStates(int n,int graphSize){
        return IntStream.range(0, n).mapToObj(i -> new StarSatelliteDescriptionState(graphSize)).collect(Collectors.toList());
    }
}
