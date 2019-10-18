package com.company;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

public class Manager
{

    public static void bruteForce(List<Vertex> vertexList, int starsCount)
    {
        int[] arr = null;
        while ((arr = generateCombinations(arr, starsCount, vertexList.size())) != null)
        {

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

    public static int getPlan(List<Vertex> vertexList)
    {
        return L(Collections.singletonList(vertexList.get(0)));
    }

    public static int[] generateCombinations(int[] arr, int starCount, int vertexCount)
    {
        if (arr == null)
        {
            arr = new int[starCount];
            for (int i = 0; i < starCount; i++)
                arr[i] = i;
            return arr;
        }
        for (int i = starCount - 1; i >= 0; i--)
            if (arr[i] < vertexCount - starCount + i)
            {
                arr[i]++;
                for (int j = i; j < starCount - 1; j++)
                    arr[j + 1] = arr[j] + 1;
                return arr;
            }
        return null;
    }
}
