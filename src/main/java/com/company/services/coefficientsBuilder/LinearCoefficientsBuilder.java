package com.company.services.coefficientsBuilder;

import com.company.entities.Coefficient;
import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;
import java.util.ArrayList;
import java.util.List;

public class LinearCoefficientsBuilder {
    ArrayList<Coefficient> coefficients;

    public LinearCoefficientsBuilder(Graph graph){
        List<Vertex> vertices = graph.getVertices();
        EdgeMatrix edgeMatrix = graph.getEdgeMatrix();

        int size = (vertices.size() * (vertices.size() - 1));
        coefficients = new ArrayList<>(size);
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if(i == j) continue;
                Coefficient coefficient = new Coefficient(
                        i,j, vertices.get(i).getWeight() * edgeMatrix.getCell(i,j) +
                                     vertices.get(j).getWeight() + edgeMatrix.getCell(j,i)
                );
                coefficients.add(coefficient);
            }
        }
    }

    public ArrayList<Coefficient> getCoefficients() {
        return coefficients;
    }
}
