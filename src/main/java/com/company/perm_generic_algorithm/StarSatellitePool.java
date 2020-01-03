package com.company.perm_generic_algorithm;

import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StarSatellitePool {
    private List<StarSatelliteDescriptionState> states;
    private Graph graph;

    public StarSatellitePool(Graph graph, List<StarSatelliteDescriptionState> states, int totalPoolSize){
        this.graph = graph;
        this.states = states;
        this.states.addAll(StarSatellitePoolGenerator.getStates(totalPoolSize - states.size(),graph.size()));
    }

    public StarSatellitePool(Graph graph, int poolSize) {
        this.states = StarSatellitePoolGenerator.getStates(poolSize,graph.size());
        this.graph = graph;
    }

    public StarSatellitePool calculateWeights() {
        EdgeMatrix edgeMatrix = graph.getEdgeMatrix();
        List<Vertex> vertices = graph.getVertices();
        for (StarSatelliteDescriptionState state :
                states) {
            int[] nodes = state.getNodes();
            int[] binaryCode = state.getBinaryCode();
            int weight = 0;
            int currentStar = nodes[0];
            for (int i = 0; i < nodes.length; i++) {
                if (binaryCode[i] == 1) {
                    currentStar = nodes[i];
                } else {
                    weight += (vertices.get(currentStar).getWeight() * edgeMatrix.getCell(currentStar, nodes[i]));
                }
            }
            state.setWeight(weight);
        }
        return this;
    }

    public StarSatellitePool orderByWeight(){
        states.sort(Comparator.comparingInt(StarSatelliteDescriptionState::getWeight));
        return this;
    }

    public List<StarSatelliteDescriptionState> getFirstStates(int howManyStates){
        return states.stream().limit(howManyStates).collect(Collectors.toList());
    }

    public StarSatelliteDescriptionState getBestState(){
        return states.get(0);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("StarSatellitePool\n");
        for (StarSatelliteDescriptionState state:
             states) {
            builder.append(state).append("\n\n");
        }
        return builder.toString();
    }
}
