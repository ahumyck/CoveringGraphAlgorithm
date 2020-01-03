package com.company.services.builders.coefficientsBuilder;

import com.company.entities.Coefficient;
import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LinearCoefficientsBuilder {
    private ArrayList<Coefficient> coefficients = null;

    public  LinearCoefficientsBuilder(){
    }

    public LinearCoefficientsBuilder build(Graph graph){
        List<Vertex> vertices = graph.getVertices();
        EdgeMatrix edgeMatrix = graph.getEdgeMatrix();

        int size = (vertices.size() * (vertices.size() - 1));
        coefficients = new ArrayList<>(size);
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if(i != j) {
                    Coefficient coefficient = new Coefficient(
                            i, j, vertices.get(i).getWeight() * edgeMatrix.getCell(i, j)
                    );
                    coefficients.add(coefficient);
                }
            }
        }
        return this;
    }

    public ArrayList<Coefficient> getCoefficients() {
        return coefficients;
    }

    public LinearCoefficientsBuilder orderByWeight() {
        coefficients.sort(Comparator.comparingInt(Coefficient::getWeight));
        return this;
    }
}
