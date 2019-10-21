package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        List<Vertex> graph = getGraph();
        weightSetter(graph,getWeights());

//        for (Vertex v:
//             graph) {
//            System.out.println(v);
//        }

        int[] arr = new int[3];
        for (int i = 0; i < 3; i++) {
            arr[i] = i;
        }

        List<Integer> stars = new ArrayList<>();
        for (int i : arr)
        {
            stars.add(i);
        }
        SmartMatrixWrapper smartMatrixWrapper = new SmartMatrixWrapper(stars,graph);
        smartMatrixWrapper.calculateMinimizationFunction();
    }



    // generator polnogo grafa na 8 vershinah
    // y kazhodoy vertex dolzhno bit N - 1 edge tak mi ne ychitivaem edge sam v sebya
    private static List<Vertex> getGraph(){

        Edge[] edges1 = new Edge[7];
        edges1[0] = new Edge(1,0,1);
        edges1[1] = new Edge(1,0,2);
        edges1[2] = new Edge(1,0,3);
        edges1[3] = new Edge(1,0,4);
        edges1[4] = new Edge(1,0,5);
        edges1[5] = new Edge(1,0,6);
        edges1[6] = new Edge(1,0,7);
        Vertex a1 = new Vertex(0,10,Arrays.asList(edges1));


        Edge[] edges2 = new Edge[7];
        edges2[0] = new Edge(1,1,0);
        edges2[1] = new Edge(1,1,2);
        edges2[2] = new Edge(1,1,3);
        edges2[3] = new Edge(1,1,4);
        edges2[4] = new Edge(1,1,5);
        edges2[5] = new Edge(1,1,6);
        edges2[6] = new Edge(1,1,7);
        Vertex a2 = new Vertex(1,15,Arrays.asList(edges2));



        Edge[] edges3 = new Edge[7];
        edges3[0] = new Edge(1,2,0);
        edges3[1] = new Edge(1,2,1);
        edges3[2] = new Edge(1,2,3);
        edges3[3] = new Edge(1,2,4);
        edges3[4] = new Edge(1,2,5);
        edges3[5] = new Edge(1,2,6);
        edges3[6] = new Edge(1,2,7);
        Vertex a3 = new Vertex(2,5,Arrays.asList(edges3));



        Edge[] edges4 = new Edge[7];
        edges4[0] = new Edge(1,3,0);
        edges4[1] = new Edge(1,3,1);
        edges4[2] = new Edge(1,3,2);
        edges4[3] = new Edge(1,3,4);
        edges4[4] = new Edge(1,3,5);
        edges4[5] = new Edge(1,3,6);
        edges4[6] = new Edge(1,3,7);
        Vertex a4 = new Vertex(3,1,Arrays.asList(edges4));

        Edge[] edges5 = new Edge[7];
        edges5[0] = new Edge(1,4,0);
        edges5[1] = new Edge(1,4,1);
        edges5[2] = new Edge(1,4,2);
        edges5[3] = new Edge(1,4,3);
        edges5[4] = new Edge(1,4,5);
        edges5[5] = new Edge(1,4,6);
        edges5[6] = new Edge(1,4,7);
        Vertex a5 = new Vertex(4,1,Arrays.asList(edges5));


        Edge[] edges6 = new Edge[7];
        edges6[0] = new Edge(1,5,0);
        edges6[1] = new Edge(1,5,1);
        edges6[2] = new Edge(1,5,2);
        edges6[3] = new Edge(1,5,3);
        edges6[4] = new Edge(1,5,4);
        edges6[5] = new Edge(1,5,6);
        edges6[6] = new Edge(1,5,7);
        Vertex a6 = new Vertex(5,1,Arrays.asList(edges6));

        Edge[] edges7 = new Edge[7];
        edges7[0] = new Edge(1,6,0);
        edges7[1] = new Edge(1,6,1);
        edges7[2] = new Edge(1,6,2);
        edges7[3] = new Edge(1,6,3);
        edges7[4] = new Edge(1,6,4);
        edges7[5] = new Edge(1,6,5);
        edges7[6] = new Edge(1,6,7);
        Vertex a7 = new Vertex(6,1,Arrays.asList(edges7));

        Edge[] edges8 = new Edge[7];
        edges8[0] = new Edge(1,7,0);
        edges8[1] = new Edge(1,7,1);
        edges8[2] = new Edge(1,7,2);
        edges8[3] = new Edge(1,7,3);
        edges8[4] = new Edge(1,7,4);
        edges8[5] = new Edge(1,7,5);
        edges8[6] = new Edge(1,7,6);
        Vertex a8 = new Vertex(7,1,Arrays.asList(edges8));


        return Arrays.asList(a1,a2,a3,a4,a5,a6,a7,a8);
    }

    private static void weightSetter(List<Vertex> initializedGraph, int[][] weights){
        for (int i = 0; i < initializedGraph.size(); i++) {
            List<Edge> edges = initializedGraph.get(i).getEdges();
            for (int j = 0; j < edges.size(); j++) {
                edges.get(j).setWeight(weights[i][j]);
            }
        }
    }

    private static int[][] getWeights(){
        int[][] weights = new int[8][8];

        weights[0][1] = 1;
        weights[0][2] = 1;
        weights[0][3] = 1;
        weights[0][4] = 1;
        weights[0][5] = 5;
        weights[0][6] = 5;
        weights[0][7] = 5;

        weights[1][2] = 2;
        weights[1][3] = 2;
        weights[1][4] = 4;
        weights[1][5] = 2;
        weights[1][6] = 2;
        weights[1][7] = 2;


        weights[2][3] = 3;
        weights[2][4] = 4;
        weights[2][5] = 3;
        weights[2][6] = 3;
        weights[2][7] = 3;

        weights[3][4] = 4;
        weights[3][5] = 4;
        weights[3][6] = 4;
        weights[3][7] = 4;

        weights[4][5] = 5;
        weights[4][6] = 5;
        weights[4][7] = 5;

        weights[5][6] = 6;
        weights[5][7] = 6;

        weights[6][7] = 7;

        for (int i = 0; i < 8 ; i++) {
            weights[i][i] = 0;
            for (int j = i + 1; j < 8; j++) {
                weights[j][i] = weights[i][j];
            }
        }


        return weights;
    }
}
