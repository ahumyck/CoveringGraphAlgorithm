package com.company;

import com.company.generators.StarGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Manager
{

    public static void bruteForce(List<Vertex> vertexList, int starsCount)
    {
        StarGenerator generator = new StarGenerator(starsCount,vertexList.size());
        List<Integer> starsCombination; //todo: base plan?

        while((starsCombination = generator.next()) != null){
            //todo: calculate something here and compare to base plan
            //todo: either collect results to List<Integer> and use ParallelStream?
            System.out.println(starsCombination);
        }
    }


    //todo rename naxui
    public static int L(List<Vertex> vertexList)
    {
        int count = 0;
        for (Vertex vertex :
                vertexList)
        {
            for (Edge edge :
                    vertex.getEdges())
            {
                count += vertex.getWeight() * edge.getWeight();
            }
        }
        return count;
    }
}
