package com.company.genetic;

import com.company.GreedyAlgorithm;
import com.company.entities.Coefficient;
import com.company.entities.Graph;
import com.company.services.builders.arrayBuilder.ArrayDTOBuilderByGalaxy;
import com.company.services.builders.coefficientsBuilder.LinearCoefficientsBuilder;
import com.company.services.builders.galaxyBuilders.GalaxyDTOBuilderByCustomArray;
import com.company.genetic.universe.Galaxy;
import com.company.genetic.universe.GalaxyPool;
import com.company.genetic.universe.MutableGalaxy;
import com.company.genetic.universe.galaxyMutator.CombinationMutator;
import com.company.genetic.universe.galaxyMutator.Mutator;

import java.util.*;
import java.util.stream.Collectors;

public class Genetic {

    private static final int INVALID_VALUE = -2;
    private  static final int STAR_VALUE = -1;
    private Random random = new Random();
    private Graph graph;
    private long Average = Integer.MAX_VALUE;
    private List<Array> minSolves = new ArrayList<>();
    private int counter = 0;
    private List<Integer> listCounter = new ArrayList<>();
    private Array GREEDY_SOLVE;


    private boolean isIncludeGreedy;
    private int totalStuffingSize;
    private int solvesStuffingSize;
    private int selectionSize;
    private boolean isMutation;
    private int reinitCount;
    private boolean isCheckValidSolves;
    private int roundCount;

    public Genetic(Graph graph) {
        this.totalStuffingSize = 20;
        this.solvesStuffingSize = 4;
        this.selectionSize = 10;
        this.isMutation = true;
        this.reinitCount = 1;
        this.isIncludeGreedy = false;
        this.graph = graph;
        this.isCheckValidSolves = false;
        this.roundCount = 100;
    }

    public Genetic roundCount(int roundCount){
        this.roundCount = roundCount;
        return this;
    }

    public Genetic isCheckValidSolves(boolean isCheckValidSolves){
        this.isCheckValidSolves = isCheckValidSolves;
        return this;
    }

    public static Genetic initialize(Graph graph){
        return new Genetic(graph);
    }

    public Genetic isIncludeGreedy(boolean isIncludeGreedy){
        this.isIncludeGreedy = isIncludeGreedy;
        return this;
    }

    public Genetic totalStuffingSize(int totalStuffingSize) {
        this.totalStuffingSize = totalStuffingSize;
        return this;
    }

    public Genetic solvesStuffingSize(int solvesStuffingSize) {
        this.solvesStuffingSize = solvesStuffingSize;
        return this;
    }

    public Genetic selectionSize(int selectionSize) {
        this.selectionSize = selectionSize;
        return this;
    }

    public Genetic reinitCount(int reinitCount) {
        this.reinitCount = reinitCount;
        return this;
    }

    public Genetic isMutation(boolean isMutation) {
        this.isMutation = isMutation;
        return this;
    }

    @Override
    public String toString() {
        return "Genetic{" +
                "isIncludeGreedy=" + isIncludeGreedy +
                ", totalStuffingSize=" + totalStuffingSize +
                ", solvesStuffingSize=" + solvesStuffingSize +
                ", selectionSize=" + selectionSize +
                ", isMutation=" + isMutation +
                ", reinitCount=" + reinitCount +
                ", isCheckValidSolves=" + isCheckValidSolves +
                '}';
    }

    public Map<Integer, ArrayList<Integer>> solve() {
        System.out.println(this);
        List<Array> generation = init();
        if(isIncludeGreedy) {
            ArrayList<Coefficient> coefficients = new LinearCoefficientsBuilder().build(graph).orderByWeight().getCoefficients();
            Map<Integer, ArrayList<Integer>> solve = GreedyAlgorithm.solve(coefficients, graph.size());
            GREEDY_SOLVE = convertToSolve(solve, graph.size());
            generation.add(GREEDY_SOLVE);
        }
//        generation = getChildGeneration(generation);
        generation = selection(generation, false, selectionSize);
        while (minSolves.size() < reinitCount) {
//            long startTime = System.currentTimeMillis();

            stuffing(generation, totalStuffingSize);
//            System.out.println("Stuffing time: " + (System.currentTimeMillis() - startTime));

//            startTime = System.currentTimeMillis();
            generation = getChildGeneration(generation);
//            System.out.println("ChildGeneration time: " + (System.currentTimeMillis() - startTime));

//            startTime = System.currentTimeMillis();
            generation = selection(generation, true, selectionSize);
//            System.out.println("Selection time: " + (System.currentTimeMillis() - startTime));

//            long startTime = System.currentTimeMillis();
            if (isMutation)
                mutate(generation);
//            System.out.println("Selection time: " + (System.currentTimeMillis() - startTime));

        }

//        for (Array solve :
//             minSolves) {
//            System.out.println("Min solve: " + GreedyAlgorithmTest.calculate(convertToAnswer(solve), graph));
//        }
        return convertToAnswer(getMin(minSolves));

    }


    private List<Array> mutate(List<Array> generation) {
        GalaxyDTOBuilderByCustomArray builderByCustomArray = new GalaxyDTOBuilderByCustomArray();
        List<MutableGalaxy> generationAsGalaxy = new ArrayList<>();
        for (Array solution :
                generation) {
            generationAsGalaxy.add(new MutableGalaxy(builderByCustomArray.build(solution, graph), true));
        }

        GalaxyPool pool = new GalaxyPool(graph, GalaxyPool.EMPTY);
        pool.addGalaxies(generationAsGalaxy);
        Mutator mutator = new CombinationMutator();
        for (int i = 0; i < 10; i++) {
            pool.mutate(mutator);
        }

        List<Galaxy> updatedGeneration = pool.getGalaxies()
                .stream()
                .map(MutableGalaxy::getGalaxy)
                .collect(Collectors.toList());

        ArrayDTOBuilderByGalaxy builderByGalaxy = new ArrayDTOBuilderByGalaxy();
        for (int i = 0; i < updatedGeneration.size(); i++) {
            generation.set(i, builderByGalaxy.build(updatedGeneration.get(i), graph));
        }

        return generation;
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
        int result = calculate(generation.get(0));
        Array resultSolve = generation.get(0);
        for (Array i :
                generation) {
            int tmp = calculate(i);
            if (tmp < result) {
                result = tmp;
                resultSolve = i;
            }
        }
        return resultSolve;
    }

    private List<Array> stuffing(List<Array> generation, int size) {
        int starCount = 2;
        while (starCount <= graph.size() / 2) {
            starCount *= 2;
        }
        starCount /= 2;
        while (starCount >= 2 && size >= 0) {
            generation.addAll(selection(stuffingHelper(starCount), false, solvesStuffingSize));
            starCount /= 2;
            size -= solvesStuffingSize;
        }
        return generation;
    }

    private List<Array> stuffingHelper(int starCount) {
        int num = 100;
        List<Array> mutation = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            mutation.add(generateSolve(graph.size(), starCount));
        }
        return mutation;
    }

    private List<Array> stuffing(List<Array> generation, int param, int size) {
        List<Array> mutation = new ArrayList<>();
        int num = 100;
        for (int i = 0; i < num; i++) {
            mutation.add(generateSolve(graph.size(), param));
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
            result = init();
            return selection(result, false, 20);
        }
        long average;
        HashSet<Array> generationSet = new HashSet<>(generation);
        generation = new ArrayList<>(generationSet);
        int[] calculatedResults = new int[generation.size()];
        for (int i = 0; i < generation.size(); i++) {
            calculatedResults[i] = calculate(generation.get(i));
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
//        System.out.print(counter);
//        System.out.print(" Min: " + temp[0]);
//        System.out.println(" Average: " + average);
        average = temp[0];

        if (Average > average) {
            listCounter.add(counter);
//            System.out.println("Counter: " + counter + " min:" + temp[0]);
            counter = 0;
            Average = average;
            return result;
        } else {
            counter++;
            Average = average;
            if (counter > roundCount) {
                Average = Integer.MAX_VALUE;
                counter = 0;
                minSolves.add(getMin(result));
                result = init();
                result.addAll(minSolves);
                return selection(result, false, 20);
            } else return result;
        }
    }

    private List<Array> cross(Array child1, Array child2) {
        List<Array> result = new ArrayList<>();
        Array toAdd;
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
            toAdd = outerJoin(child1, child2);
//            System.out.println("Outterjoin time: " + (System.currentTimeMillis() - startTime));
            if (toAdd != null) result.add(toAdd);
        }
//        System.out.println("Outterjoin: " + Arrays.toString(toAdd));
//        System.out.println("Cross time: " + (System.currentTimeMillis() - startTime));
        return result;
    }

    private int calculate(Array solve) {
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
        if (stars.size() > parent1.length / 2) {
            System.out.println("If condition(union): parent1=" + parent1 + " child2=" + child2);
        }
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
        if (stars.size() > child1.length / 2) {
            System.out.println("If condition(intersection): child1=" + child1 + " child2=" + child2);
        }
        return fillSatellite(result);
    }

    private Array outerJoin(Array child1, Array child2) {
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
        return fillSatellite(result);
    }

    private List<Array> init() {
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
        int min = graph.getEdgeMatrix().getCell(stars.get(0), satellite);
        int starIndex = stars.get(0);
        for (int star :
                stars) {
            int tmp = graph.getEdgeMatrix().getCell(star, satellite);
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
        if (solve.length != graph.size()) return false;
        int count = 0;
        List<Integer> stars = new ArrayList<>();
        for (int i = 0; i < solve.length; i++) {
            if (solve.getArray()[i] == STAR_VALUE) {
                stars.add(i);
                count++;
            }
        }
        if (stars.size() > graph.size() / 2) return false;
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
