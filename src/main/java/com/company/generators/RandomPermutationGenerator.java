package com.company.generators;

import org.apache.el.stream.Stream;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomPermutationGenerator {
    public static int[] getPermutation(int n){
        int[] permutation = IntStream.range(0,n).toArray();
        Random random = new Random();
        for(int i = n - 1 ; i >= 0 ; i--){
            int random_number = random.nextInt(n);
            int tmp = permutation[i];
            permutation[i] = permutation[random_number];
            permutation[random_number] = tmp;
        }
        return permutation;
    }
}
