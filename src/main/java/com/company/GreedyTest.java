package com.company;

import com.company.entities.Coefficient;
import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;

import java.util.*;

public class GreedyTest
{

    public static Map<Coefficient, ArrayList<Coefficient>> solve(List<Coefficient> coefficients, int starCount, int vertexCount)
    {
        List<Coefficient> listStars = new ArrayList<>(starCount);
        List<Integer> listStarsIndex = new ArrayList<>(starCount);
        List<Integer> listSatellites = new ArrayList<>();
        Map<Coefficient, ArrayList<Coefficient>> mapStars = new HashMap<>(starCount);

        mapStars.put(coefficients.get(0), new ArrayList<>());
        mapStars.get(coefficients.get(0)).add(coefficients.get(0));
        listStars.add(coefficients.get(0));
        listStarsIndex.add(coefficients.get(0).getStar());
        listSatellites.add(coefficients.get(0).getSatellite());
        for (int i = 0; i < coefficients.size(); i++)
        {
            if (vertexCount <= listSatellites.size() + listStarsIndex.size()) break;
            boolean flag = true;
            for (int j = 0; j < listStars.size(); j++)
            {
                if (listStars.get(j).getStar() == coefficients.get(i).getStar() && !listSatellites.contains(coefficients.get(i).getSatellite()) && !listStarsIndex.contains(coefficients.get(i).getSatellite()))
                {
                    mapStars.get(listStars.get(j)).add(coefficients.get(i));
                    listSatellites.add(coefficients.get(i).getSatellite());
                    flag = false;
                    break;
                }
//                else
//                {
//                    mapStars.put(coefficients.get(i), new ArrayList<>());
//                }
            }
            if (flag && starCount > listStars.size() && !listSatellites.contains(coefficients.get(i).getStar()) && !listStarsIndex.contains(coefficients.get(i).getStar()))
            {
                mapStars.put(coefficients.get(i), new ArrayList<>());
                listStars.add(coefficients.get(i));
                listStarsIndex.add(coefficients.get(i).getStar());
                if (!listSatellites.contains(coefficients.get(i).getSatellite()) && !listStarsIndex.contains(coefficients.get(i).getSatellite()))
                {
                    mapStars.get(coefficients.get(i)).add(coefficients.get(i));
                    listSatellites.add(coefficients.get(i).getSatellite());
                }
            }
        }
        return mapStars;
    }

    public static long calculate(Map<Coefficient, ArrayList<Coefficient>> map)
    {
        final long[] sum = new long[1];
        map.forEach((key, value) -> value.forEach(x -> sum[0] += x.getWeight()));
        return sum[0];
    }
}
