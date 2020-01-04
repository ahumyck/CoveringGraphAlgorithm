package com.company;

import com.company.entities.Coefficient;
import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.entities.Vertex;

import java.util.*;

public class GreedyAlgorithmTest
{

    public static int calculate(Map<Integer, ArrayList<Integer>> map, Graph graph)
    {
        EdgeMatrix edgeMatrix = graph.getEdgeMatrix();
        List<Vertex> vertices = graph.getVertices();
        int counter = 0;
        for (Integer key :
                map.keySet())
        {
            for (Integer value :
                    map.get(key))
            {
                counter += vertices.get(key).getWeight() * edgeMatrix.getCell(key, value);
            }
        }
        return counter;
    }


    public static Map<Integer, ArrayList<Integer>> solve(List<Coefficient> coefficients, int n)
    {
        Map<Integer, ArrayList<Integer>> hashMap = initializeHashMap(coefficients);

        for (int i = 1; i < coefficients.size(); i++)
        {

            Coefficient coefficient = coefficients.get(i);
            int potentialStar = coefficient.getStar();
            int potentialSatellite = coefficient.getSatellite();

            if (stopRule(hashMap, n)) break;
            addPotentialSatelliteToExistingStarOrCreateNewStarWithNewSatelliteOrDoNothingDependsOnPotentialStarAndPotentialSatellite(
                    hashMap, potentialStar, potentialSatellite);
        }
        return hashMap;
    }

    private static boolean isValueContainsInMap(Map<Integer, ArrayList<Integer>> hashMap, int valueToCheck)
    {
        for (Integer star :
                hashMap.keySet())
        {
            ArrayList<Integer> satellites = hashMap.get(star);
            if (satellites.contains(valueToCheck))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isValueContainsInMapOrKeySet(Map<Integer, ArrayList<Integer>> hashMap, int valueToCheck)
    {
        return hashMap.containsKey(valueToCheck) || isValueContainsInMap(hashMap, valueToCheck);
    }

    static Map<Integer, ArrayList<Integer>> initializeHashMap(List<Coefficient> coefficients)
    {
        Map<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        ArrayList<Integer> firstSatellite = new ArrayList<>();
        firstSatellite.add(coefficients.get(0).getSatellite());
        hashMap.put(coefficients.get(0).getStar(), firstSatellite);

        return hashMap;
    }

    static void addPotentialSatelliteToExistingStarOrCreateNewStarWithNewSatelliteOrDoNothingDependsOnPotentialStarAndPotentialSatellite(
            Map<Integer, ArrayList<Integer>> hashMap, int potentialStar, int potentialSatellite
    )
    {
        if (!hashMap.containsKey(potentialStar))
        {
            if (!isValueContainsInMap(hashMap, potentialStar))
            {
                if (!isValueContainsInMapOrKeySet(hashMap, potentialSatellite))
                {
                    hashMap.put(potentialStar, new ArrayList<>());
                    hashMap.get(potentialStar).add(potentialSatellite);
                }
            }
        } else
        {
            if (!isValueContainsInMapOrKeySet(hashMap, potentialSatellite))
            {
                hashMap.get(potentialStar).add(potentialSatellite);
            }
        }
    }

    private static boolean stopRule(Map<Integer, ArrayList<Integer>> map, int n)
    {
        int counter = 0;
        for (Integer star :
                map.keySet())
        {
            counter += map.get(star).size();
        }
        counter += map.keySet().size();
        return counter == n;
    }
}
