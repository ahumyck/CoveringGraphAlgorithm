package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception
    {
        List<Vertex> graph = getGraph();
        int[] arr = new int[3];
        for (int i = 0; i < 3; i++) {
            arr[i] = i;
        }

        List<Integer> stars = new ArrayList<Integer>();
        for (int i : arr)
        {
            stars.add(i);
        }
        SmartMatrixWrapper smartMatrixWrapper = new SmartMatrixWrapper(stars,graph);
        smartMatrixWrapper.getL();
    }



    //todo: nepravilnoe zapolnenie
    //todo: napisat che-nibyd norm
    public static List<Vertex> getGraph(){
        Edge[] edges1 = new Edge[8];
        for (int i = 0; i < 8; i++) {
            edges1[i] = new Edge(1,0,i + 1);
        }
        Vertex a1 = new Vertex(0,1,Arrays.asList(edges1));

        Edge[] edges2 = new Edge[8];
        for (int i = 0; i < 1; i++) {
            edges2[i] = new Edge(1,1, i);
        }
        for (int i = 1; i < 8; i++) {
            edges2[i] = new Edge(1,1,i + 1);
        }
        Vertex a2 = new Vertex(1,1,Arrays.asList(edges2));

        Edge[] edges3 = new Edge[8];
        for (int i = 0; i < 2; i++) {
            edges3[i] = new Edge(1,2, i);
        }
        for (int i = 2; i < 8; i++) {
            edges3[i] = new Edge(1,2,i + 1);
        }
        Vertex a3 = new Vertex(2,1,Arrays.asList(edges3));

        Edge[] edges4 = new Edge[8];
        for (int i = 0; i < 3; i++) {
            edges4[i] = new Edge(1,3, i);
        }
        for (int i = 3; i < 8; i++) {
            edges4[i] = new Edge(1,3,i + 1);
        }
        Vertex a4 = new Vertex(3,1,Arrays.asList(edges4));

        Edge[] edges5 = new Edge[8];
        for (int i = 0; i < 4; i++) {
            edges5[i] = new Edge(1,4, i);
        }
        for (int i = 4; i < 8; i++) {
            edges5[i] = new Edge(1,4,i + 1);
        }
        Vertex a5 = new Vertex(4,1,Arrays.asList(edges5));

        Edge[] edges6 = new Edge[8];
        for (int i = 0; i < 5; i++) {
            edges6[i] = new Edge(1,5, i);
        }
        for (int i = 5; i < 8; i++) {
            edges6[i] = new Edge(1,5,i + 1);
        }
        Vertex a6 = new Vertex(5,1,Arrays.asList(edges6));

        Edge[] edges7 = new Edge[8];
        for (int i = 0; i < 6; i++) {
            edges7[i] = new Edge(1,6, i);
        }
        for (int i = 6; i < 8; i++) {
            edges7[i] = new Edge(1,6,i + 1);
        }
        Vertex a7 = new Vertex(6,1,Arrays.asList(edges7));

        Edge[] edges8 = new Edge[8];
        for (int i = 0; i < 8; i++) {
            edges8[i] = new Edge(1,7,i);
        }
        Vertex a8 = new Vertex(7,1,Arrays.asList(edges8));


        return Arrays.asList(a1,a2,a3,a4,a5,a6,a7,a8);
    }
}
