package com.company.universe;

import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;
import com.company.generators.RandomBinaryCodeGenerator;
import com.company.generators.RandomPermutationGenerator;

import java.util.ArrayList;
import java.util.List;

public class GalaxyPoolGenerator {
    public static List<Galaxy> getGalaxies(Graph graph, int howMany){
        List<Galaxy> galaxies = new ArrayList<>();
        for(int uselessIndex = 0; uselessIndex < howMany; uselessIndex++){
            int[] nodes = RandomPermutationGenerator.getPermutation(graph.size());
            int[] codes = RandomBinaryCodeGenerator.getCode(graph.size());
            galaxies.add(getGalaxy(graph,nodes,codes));
        }
        return galaxies;
    }

    public static Galaxy getGalaxy(Graph graph, int[] nodes, int[] codes){
        List<Vertex> vertices = graph.getVertices();
        EdgeMatrix edgeMatrix = graph.getEdgeMatrix();
        int currentStar = nodes[0];
        int weight = 0;
        List<StarSystem> systems = new ArrayList<>();
        List<Integer> planets = new ArrayList<>();
        for(int i = 1; i < nodes.length; i++){
            if(codes[i] == 1){
                systems.add(new StarSystem(currentStar,planets ,weight));
                currentStar = nodes[i];
                planets.clear();
                weight = 0;
            }
            else{
                weight += (vertices.get(currentStar).getWeight() * edgeMatrix.getCell(currentStar,nodes[i]));
                planets.add(nodes[i]);
            }
        }
        systems.add(new StarSystem(currentStar, planets ,weight));
        return new Galaxy(systems);
    }
}
