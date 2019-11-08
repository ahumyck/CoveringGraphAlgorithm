package com.company.services.builders;

import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;

import java.util.ArrayList;

public class GraphBuilderByInitialGraphRequest implements GraphBuilder<int[], int[][]> {
    @Override
    public Graph build(int[] nodeInterpretation, int[][] edgeInterpretation) {
        EdgeMatrix edgeMatrix = new EdgeMatrix(edgeInterpretation);
        ArrayList<Vertex> nodes = new ArrayList<>();
        for (int i = 0; i < nodeInterpretation.length ; i++) {
            nodes.add(new Vertex(i,nodeInterpretation[i]));
        }

        return new Graph(nodes,edgeMatrix);
    }
}
