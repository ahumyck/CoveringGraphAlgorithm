package com.company;

import com.company.entities.Coefficient;
import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;
import com.company.generators.SatellitePermutationGeneratorUsingStars;

import java.util.*;

public class AdditiveAlgorithmManager {

    public void solve(Graph graph, int starCount){
        List<Coefficient> coefficients = getAdditiveAlgorithmPattern(graph);
//        System.out.println(coefficients);
        SatellitePermutationGeneratorUsingStars generator = new SatellitePermutationGeneratorUsingStars(coefficients.size(),2);

        //todo: generator
        ArrayList<Integer> next = generator.next();
        System.out.println(next);
        while ((next = generator.next()) != null){
            System.out.println(next);
        }


    }



    private List<Coefficient> getAdditiveAlgorithmPattern(Graph graph){
        List<Vertex> vertices = graph.getVertices();
        EdgeMatrix edgeMatrix = graph.getEdgeMatrix();

        List<Coefficient> coefficients = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if(i == j) continue;
                Coefficient coefficient = new Coefficient(
                        i,j, vertices.get(i).getWeight() * edgeMatrix.getCell(i,j)
                );
                coefficients.add(coefficient);
            }
        }

        coefficients.sort(Comparator.comparingInt(Coefficient::getWeight));
        return coefficients;
    }
}
