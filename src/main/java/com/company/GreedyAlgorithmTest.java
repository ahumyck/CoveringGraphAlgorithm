package com.company;

import com.company.entities.Coefficient;
import java.util.*;

public class GreedyAlgorithmTest {

    public static Map<Integer,ArrayList<Integer>> solve(List<Coefficient> coefficients, int starCounter){
        Map<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        ArrayList<Integer> satellites = new ArrayList<>();
        satellites.add(coefficients.get(0).getJ());
        hashMap.put(coefficients.get(0).getI(),satellites);

        for (int i = 1; i < coefficients.size() - 1; i++) {
            Coefficient coefficient = coefficients.get(i);
            for (Integer star:
                 hashMap.keySet()) {

            }
        }

        return hashMap;
    }
}
