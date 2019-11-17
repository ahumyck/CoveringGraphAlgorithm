package com.company;


import com.company.entities.Coefficient;
import com.company.entities.EdgeMatrix;
import com.company.entities.Graph;
import com.company.generators.BinaryGenerator;
import com.company.generators.CombinationBinaryGeneratorLong;

import java.util.*;

public class BruteForceAlgorithmTest extends GreedyAlgorithmTest {
    public static void solve(final Graph graph, List<Coefficient> coefficients, int starCounter, int n) {
        if (starCounter > 1) {
            ArrayList<Map<Integer,ArrayList<Integer>>> solutions = new ArrayList<>();
            BinaryGenerator generator = new CombinationBinaryGeneratorLong(coefficients.size(), n - starCounter);
            while (generator.hasNext()) {
                Optional<Map<Integer, ArrayList<Integer>>> solution = solve(coefficients, generator.next(),starCounter,n);
                solution.ifPresent(solutions::add);
            }

            Optional<Map<Integer, ArrayList<Integer>>> min = solutions.stream().min(Comparator.comparing(o -> calculate(o, graph)));
            if(min.isPresent()){
                System.out.println(min.get());
                System.out.println(calculate(min.get(),graph));
            }
        }
    }

    public static Integer calculate(Map<Integer,ArrayList<Integer>> map, Graph graph){
        EdgeMatrix edgeMatrix = graph.getEdgeMatrix();
        int counter = 0;
        for (Integer key :
                map.keySet()) {
            for (Integer value:
                 map.get(key)) {
                counter += edgeMatrix.getCell(key,value);
            }
        }
        return counter;
    }


    private static Optional<Map<Integer,ArrayList<Integer>>> solve(List<Coefficient> coefficients,  long[] solution, int starCounter,int n){
        List<Coefficient> nonZeroCoefficients = new ArrayList<>();
        for (int i = 0; i < solution.length; i++) {
            if(solution[i] == 1){
                nonZeroCoefficients.add(coefficients.get(i));
            }
        }

        Map<Integer, ArrayList<Integer>> hashMap = initializeHashMap(nonZeroCoefficients);
        for (int i = 1; i < nonZeroCoefficients.size(); i++) {
            addPotentialSatelliteToExistingStarOrCreateNewStarWithNewSatelliteOrDoNothingDependsOnPotentialStarAndPotentialSatellite(
                    hashMap,nonZeroCoefficients.get(i).getStar(),nonZeroCoefficients.get(i).getSatellite()
            );
        }
        if(stopRule(hashMap,starCounter,n)) {
//            nonZeroCoefficients.forEach(x-> System.out.println(x.fullInfo()));
            return Optional.of(hashMap);
        }
        else return Optional.empty();
    }

    private static boolean stopRule(Map<Integer,ArrayList<Integer>> map, int starCounter,int n){
        int counter = 0;
        for (Integer star:
                map.keySet()) {
            counter += map.get(star).size();
        }
        return counter == (n - starCounter) && map.keySet().size() == starCounter;
    }

}

