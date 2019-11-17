package com.company.generators.utils;

public class Translator {
    public static int[] translateToArrayOfBits(int number,int totalSize){
        int[] bits = new int[totalSize];
        int lowerBit = 1;
        for (int i = totalSize - 1; i >= 0; i--) {
            bits[i] = number & lowerBit;
            number = number >> 1;
        }
        return bits;
    }
}
