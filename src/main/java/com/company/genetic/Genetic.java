package com.company.genetic;

import com.company.GreedyAlgorithmTest;
import com.company.entities.Coefficient;
import com.company.entities.Graph;
import com.company.services.coefficientsBuilder.LinearCoefficientsBuilder;

import java.util.*;

public class Genetic {

    private int INVALID_VALUE = -2;
    private int STAR_VALUE = -1;
    private Random random = new Random();
    private Graph Graph;
    private long Average = Integer.MAX_VALUE;
    private List<Array> minSolves = new ArrayList<>();
    private int counter = 0;
    private Array GREEDY_SOLVE;

    public Map<Integer, ArrayList<Integer>> solve(Graph graph) {
        Graph = graph;
        long average = Integer.MAX_VALUE;
        ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graph).orderByWeight().getCoefficients();
//        Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithmTest.solve(coefficients, graph.size());
//        GREEDY_SOLVE = convertToSolve(solve, graph.size());
        List<Array> generation = init(graph);
//        generation.add(GREEDY_SOLVE);
//        generation = getChildGeneration(generation);
        generation = selection(generation, false, 20);
        while (minSolves.size() < 1) {
//            long startTime = System.currentTimeMillis();

            stuffing(generation, 14);
//            System.out.println("Stuffing time: " + (System.currentTimeMillis() - startTime));

//            startTime = System.currentTimeMillis();
            generation = getChildGeneration(generation);
//            System.out.println("ChildGeneration time: " + (System.currentTimeMillis() - startTime));

//            startTime = System.currentTimeMillis();
            generation = selection(generation, true, 20);
//            System.out.println("Selection time: " + (System.currentTimeMillis() - startTime));
        }
        for (Array solve :
             minSolves) {
            System.out.println("Min solve: " + GreedyAlgorithmTest.calculate(convertToAnswer(solve), Graph));
        }
        return convertToAnswer(getMin(minSolves));

    }


    private Map<Integer, ArrayList<Integer>> convertToAnswer(Array solve) {
        Map<Integer, ArrayList<Integer>> result = new HashMap<>();
        for (int i = 0; i < solve.length; i++) {
            if (solve.getArray()[i] != STAR_VALUE) {
                result.computeIfAbsent(solve.getArray()[i], k -> new ArrayList<>());
                result.get(solve.getArray()[i]).add(i);
            }
        }
        return result;
    }

    private Array convertToSolve(Map<Integer, ArrayList<Integer>> source, int size) {
        Array result = new Array(size);
        source.forEach((key, value) -> {
            result.getArray()[key] = STAR_VALUE;
            value.forEach(x -> result.getArray()[x] = key);
        });
        return result;
    }

    private Array getMin(List<Array> generation) {
//        try {
//            checkValidGeneration(generation);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        int result = calculate(generation.get(0), Graph);
        Array resultSolve = generation.get(0);
        for (Array i :
                generation) {
            int tmp = calculate(i, Graph);
            if (tmp < result) {
                result = tmp;
                resultSolve = i;
            }
        }
        return resultSolve;
    }

    private List<Array> stuffing(List<Array> generation, int size) {
        int starCount = 2;
        while (starCount <= Graph.size() / 2) {
            starCount *= 2;
        }
        starCount /= 2;
        while (starCount >= 2 && size >= 0) {
            generation.addAll(selection(stuffingHelper(starCount), false, 2));
            starCount /= 2;
            size -= 2;
        }
        return generation;
    }

    private List<Array> stuffingHelper(int starCount) {
        int num = 100;
        List<Array> mutation = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            mutation.add(generateSolve(Graph.size(), starCount));
        }
        return mutation;
    }

    private List<Array> stuffing(List<Array> generation, int param, int size) {
        List<Array> mutation = new ArrayList<>();
        int num = 100;
        for (int i = 0; i < num; i++) {
            mutation.add(generateSolve(Graph.size(), param));
        }
        generation.addAll(selection(mutation, false, size));
        return generation;
    }

    private List<Array> getChildGeneration(List<Array> generation) {
        List<Array> newGeneration = new ArrayList<>(generation);
        for (int i = 0; i < generation.size(); i++) {
            for (int j = 0; j < generation.size(); j++) {
                if (i != j) {
                    List<Array> tmp = cross(generation.get(i), generation.get(j));
                    newGeneration.addAll(tmp);
                }
            }
        }
        return newGeneration;
    }

    private List<Array> selection(List<Array> generation, boolean flag, int selectionSize) {
        List<Array> result = new ArrayList<>();
        if (generation.size() == 0) {
            System.out.println("Empty size of generation: " + generation.size());
            result = init(Graph);
            return selection(result, false, 20);
        }
        long average = 0;
        HashSet<Array> generationSet = new HashSet<>(generation);
        generation = new ArrayList<>(generationSet);
        int[] calculatedResults = new int[generation.size()];
        for (int i = 0; i < generation.size(); i++) {
            calculatedResults[i] = calculate(generation.get(i), Graph);
        }
        int[] temp = Arrays.copyOf(calculatedResults, calculatedResults.length);
        Arrays.sort(temp);
        try {
            average = temp[selectionSize];
        } catch (Exception e) {
            System.out.println("Error size of temp: " + temp.length);
            System.out.println("Error size of generation: " + generation.size());
            throw e;
        }
        for (int i = 0; i < calculatedResults.length; i++) {
            if (calculatedResults[i] <= average) {
                result.add(generation.get(i));
            }
        }
        if (!flag) return result;
        System.out.print(counter);
        System.out.print(" Min: " + temp[0]);
        System.out.println(" Average: " + average);
        average = temp[0];

        if (Average > average) {
            counter = 0;
            Average = average;
            return result;
        } else {
            counter++;
            Average = average;
            if (counter > 100) {
                Average = Integer.MAX_VALUE;
                counter = 0;
                minSolves.add(getMin(result));
                result = init(Graph);
                result.addAll(minSolves);
                return selection(result, false, 20);
            } else return result;
        }
    }

    private List<Array> cross(Array child1, Array child2) {
        List<Array> result = new ArrayList<>();
        Array toAdd = null;
//        long startTime = System.currentTimeMillis();
        toAdd = union(child1, child2);
//        System.out.println("Union: " + Arrays.toString(toAdd));
//        System.out.println("Union time: " + (System.currentTimeMillis() - startTime));
        if (toAdd != null) result.add(toAdd);
//        startTime = System.currentTimeMillis();
        toAdd = intersection(child1, child2);
//        System.out.println("Intersection: " + Arrays.toString(toAdd));
//        System.out.println("Intersection time: " + (System.currentTimeMillis() - startTime));
        if (toAdd != null) {
            result.add(toAdd);
//            startTime = System.currentTimeMillis();
            toAdd = outterjoin(child1, child2);
//            System.out.println("Outterjoin time: " + (System.currentTimeMillis() - startTime));
            if (toAdd != null) result.add(toAdd);
        }
//        System.out.println("Outterjoin: " + Arrays.toString(toAdd));
//        System.out.println("Cross time: " + (System.currentTimeMillis() - startTime));
        return result;
    }

    private int calculate(Array solve, Graph graph) {
        int result = 0;
        for (int i = 0; i < solve.length; i++) {
            if (solve.getArray()[i] != STAR_VALUE)
                result += graph.getEdgeMatrix().getCell(solve.getArray()[i], i) * graph.getVertices().get(solve.getArray()[i]).getWeight();
        }
        return result;
    }

    private Array union(Array parent1, Array child2) {
        Array result = new Array(parent1.length);
        int starCount = 0;
        Set<Integer> stars = new HashSet<>();
        for (int i = 0; i < parent1.length; i++) {
            if (parent1.getArray()[i] == STAR_VALUE || child2.getArray()[i] == STAR_VALUE) {
                result.getArray()[i] = STAR_VALUE;
                stars.add(i);
                starCount++;
                if (parent1.getArray()[i] == STAR_VALUE && child2.getArray()[i] == STAR_VALUE) {
                    starCount--;
                }
            } else
                result.getArray()[i] = INVALID_VALUE;
        }
        if (starCount == 0) return null;
        if (stars.size() > parent1.length / 2) return null;
//        System.out.println("Stars count: " + stars.size() + " size: " + parent1.length /2);
        if (stars.size() > parent1.length / 2) {
            System.out.println("If condition(union): parent1=" + parent1 + " child2=" + child2);
        }
//        for (int i = 0; i < result.length; i++) {
//            if (result.getArray()[i] != STAR_VALUE) {
//                if (parent1[i] == child2[i]) {
//                    result[i] = parent1[i];
//                    stars.remove(result[i]);
//                } else {
//                    if (stars.contains(parent1[i])) {
//                        result[i] = parent1[i];
//                        stars.remove(parent1[i]);
//                    } else if (stars.contains(child2[i])) {
//                        result[i] = child2[i];
//                        stars.remove(child2[i]);
//                    } else {
//                        result[i] = random.nextBoolean() ? parent1[i] : child2[i]; //добавить выбор наименьшего пути по ребрам
//                    }
//                }
//                result.getArray()[i] = INVALID_VALUE;
//            }
//        }
        return fillSatellite(result);
    }

    private Array intersection(Array child1, Array child2) {
        Array result = new Array(child1.length);
        Set<Integer> stars = new HashSet<>();
        for (int i = 0; i < child1.length; i++) {
            if (child1.getArray()[i] == STAR_VALUE && child2.getArray()[i] == STAR_VALUE) {
                result.getArray()[i] = STAR_VALUE;
                stars.add(i);
            } else result.getArray()[i] = INVALID_VALUE;

        }
        if (stars.size() == 0) return null;
//        System.out.println("Stars count: " + stars.size() + " size: " + child1.length /2);
        if (stars.size() > child1.length / 2) {
            System.out.println("If condition(intersection): child1=" + child1 + " child2=" + child2);
        }
//        for (int i = 0; i < result.length; i++) {
//            if (result.getArray()[i] != STAR_VALUE) {
//                if (stars.contains(child1.getArray()[i])) {
//                    result.getArray()[i] = child1.getArray()[i];
//                } else if (stars.contains(child2.getArray()[i])) {
//                    result.getArray()[i] = child2.getArray()[i];
//                } else {
//
//                }
//            }
//        }
        return fillSatellite(result);
    }

    private Array outterjoin(Array child1, Array child2) {
        Array result = new Array(child1.length);
        Set<Integer> stars = new HashSet<>();
        for (int i = 0; i < child1.length; i++) {
            if (child1.getArray()[i] == STAR_VALUE && child2.getArray()[i] == STAR_VALUE) {
                result.getArray()[i] = INVALID_VALUE;
            } else if (child1.getArray()[i] == STAR_VALUE) {
                result.getArray()[i] = STAR_VALUE;
                stars.add(i);
            } else if (child2.getArray()[i] == STAR_VALUE) {
                result.getArray()[i] = STAR_VALUE;
                stars.add(i);
            } else {
                result.getArray()[i] = INVALID_VALUE;
            }
        }
        if (stars.size() > child1.length / 2 || stars.size() == 0) {
            return null;
        }
        if (stars.size() > child1.length / 2) {
            System.out.println("If condition(outterjoin): child1=" + Arrays.toString(child1.getArray()) + " child2=" + Arrays.toString(child2.getArray()));
        }
//        for (int i = 0; i < result.length; i++) {
//            if (result[i] != STAR_VALUE) {
//                if (stars.contains(child1[i])) {
//                    result[i] = child1[i];
//                } else if (stars.contains(child2[i])) {
//                    result[i] = child2[i];
//                } else {
//                    result[i] = INVALID_VALUE;
//                }
//            }
//        }
        return fillSatellite(result);
    }

    public List<Array> init(Graph graph) {
        List<Array> initial = new ArrayList<>();
        for (int i = 0; i <= graph.size() * 5; i++) {
            int starCount = random.nextInt(graph.size() / 2 - 1) + 1;
            initial.add(generateSolve(graph.size(), starCount));
        }
        return initial;
    }

    private Array generateSolve(int size, int starCount) {
        Array result = new Array(size);
        Arrays.fill(result.getArray(), INVALID_VALUE);
        fillStars(result, starCount);
        fillSatellite(result);
        return result;
    }

    private Array fillSatellite(Array source) {
        List<Array> result = new ArrayList<>();
        result.add(new Array(source.getArray()));
        result.add(new Array(source.getArray()));
        List<Integer> stars = new ArrayList<>();
        for (int i = 0; i < source.length; i++) {
            if (source.getArray()[i] == STAR_VALUE) stars.add(i);
        }
        int satelliteCount = source.length - stars.size();
        List<Integer> stars1 = new ArrayList<>(stars);

        for (int i = 0; i < result.get(0).length; i++) {
            if (result.get(0).getArray()[i] != STAR_VALUE && result.get(0).getArray()[i] == INVALID_VALUE) {
                Integer star;
                if (satelliteCount <= stars1.size())
                    star = getMinimumStar(i, stars1);
                else star = getMinimumStar(i, stars);
                satelliteCount--;
                result.get(0).getArray()[i] = star;
                stars1.remove(star);
            }
        }
        for (int i = result.get(1).length - 1; i >= 0; i--) {
            if (result.get(1).getArray()[i] != STAR_VALUE && result.get(1).getArray()[i] == INVALID_VALUE) {
                Integer star;
                if (satelliteCount <= stars1.size())
                    star = getMinimumStar(i, stars1);
                else star = getMinimumStar(i, stars);
                satelliteCount--;
                result.get(1).getArray()[i] = star;
                stars1.remove(star);
            }
        }
        return getMin(result);
    }

    private Integer getMinimumStar(int satellite, List<Integer> stars) {
        int min = Graph.getEdgeMatrix().getCell(stars.get(0), satellite);
        int starIndex = stars.get(0);
        for (int star :
                stars) {
            int tmp = Graph.getEdgeMatrix().getCell(star, satellite);
            if (min > tmp) {
                min = tmp;
                starIndex = star;
            }
        }
        return starIndex;
    }

    private void checkValidGeneration(List<Array> generation) throws Exception {
        for (Array solve :
                generation) {
            if (!isValidSolve(solve)) throw new Exception("Invalid generation: " + solve);
        }
    }

    private boolean isValidSolve(Array solve) {
        if (solve.length != Graph.size()) return false;
        int count = 0;
        List<Integer> stars = new ArrayList<>();
        for (int i = 0; i < solve.length; i++) {
            if (solve.getArray()[i] == STAR_VALUE) {
                stars.add(i);
                count++;
            }
        }
        if (stars.size() > Graph.size() / 2) return false;
        for (int i = 0; i < solve.length; i++) {
            if (solve.getArray()[i] > STAR_VALUE) {
                if (stars.contains(solve.getArray()[i])) {
                    count++;
                } else {
                    return false;
                }
            }
        }
        return count == solve.length;
    }

    private Array fillStars(Array source, int starCount) {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < starCount; i++) {
            int number = random.nextInt(source.length);
            if (set.add(number)) {
                source.getArray()[number] = STAR_VALUE;
            } else i--;

        }
        return source;
    }

}
