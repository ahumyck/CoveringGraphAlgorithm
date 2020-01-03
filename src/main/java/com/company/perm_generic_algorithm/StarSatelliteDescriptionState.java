package com.company.perm_generic_algorithm;

import java.util.Arrays;

public class StarSatelliteDescriptionState {
        private int[] nodes;
        private int[] binaryCode;
        private int weight;


    public StarSatelliteDescriptionState(int n) {
        nodes = RandomPermutationGenerator.getPermutation(n);
        binaryCode = RandomBinaryCodeGenerator.getCode(n);
    }

    public int[] getNodes() {
        return nodes;
    }

    public void setNodes(int[] nodes) {
        this.nodes = nodes;
    }

    public int[] getBinaryCode() {
        return binaryCode;
    }

    public void setBinaryCode(int[] binaryCode) {
        this.binaryCode = binaryCode;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "StarSatelliteDescriptionState\n" +
                "nodes = " + Arrays.toString(nodes) + '\n' +
                "binaryCode = " + Arrays.toString(binaryCode) + '\n' +
                "weight = " + weight;
    }
}
