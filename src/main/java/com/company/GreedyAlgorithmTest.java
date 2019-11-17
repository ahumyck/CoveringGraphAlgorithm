package com.company;

import com.company.entities.Coefficient;
import java.util.*;

public class GreedyAlgorithmTest {

    public static Map<Integer,ArrayList<Integer>> solve(List<Coefficient> coefficients,int n){
        Map<Integer, ArrayList<Integer>> hashMap = initializeHashMap(coefficients);

        for (int i = 1; i < coefficients.size(); i++) {

            Coefficient coefficient = coefficients.get(i);
            int potentialStar = coefficient.getStar();
            int potentialSatellite = coefficient.getSatellite();

            if(stopRule(hashMap,n)) break;
            if(!hashMap.containsKey(potentialStar)){
                if(!isValueContainsInMap(hashMap,potentialStar)){
                    hashMap.put(potentialStar, new ArrayList<>());
                    if(!isValueContainsInMap(hashMap,potentialSatellite)){
                        hashMap.get(potentialStar).add(potentialSatellite);
                    }
                }
            }
            else{
                if(!isValueContainsInMapOrKeySet(hashMap,potentialSatellite)){
                    hashMap.get(potentialStar).add(potentialSatellite);
                }
            }
        }
        return hashMap;
    }

    private static boolean stopRule(Map<Integer,ArrayList<Integer>> map, int n){
        int counter = 0;
        for (Integer star:
                map.keySet()) {
            counter += map.get(star).size();
        }
        counter += map.keySet().size();
        return counter == n;
    }

    private static boolean isValueContainsInMap(Map<Integer,ArrayList<Integer>> hashMap, int valueToCheck){
        for (Integer star :
                hashMap.keySet()) {
            ArrayList<Integer> satellites = hashMap.get(star);
            if (satellites.contains(valueToCheck)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValueContainsInMapOrKeySet(Map<Integer,ArrayList<Integer>> hashMap, int valueToCheck){
        return hashMap.containsKey(valueToCheck) || isValueContainsInMap(hashMap,valueToCheck);
    }

    private static Map<Integer,ArrayList<Integer>> initializeHashMap(List<Coefficient> coefficients){
        Map<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        ArrayList<Integer> firstSatellite = new ArrayList<>();
        firstSatellite.add(coefficients.get(0).getSatellite());
        hashMap.put(coefficients.get(0).getStar(),firstSatellite);

        return hashMap;
    }

}
