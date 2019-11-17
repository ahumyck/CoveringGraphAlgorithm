package com.company.generators.utils;

public class Translator {
    public static long[] translateToArrayOfBits(long number,int totalSize){
        long[] bits = new long[totalSize];
        long lowerBit = 1;
        for (int i = totalSize - 1; i >= 0; i--) {
            bits[i] = number & lowerBit;
            number = number >> 1;
        }
        return bits;
    }
}
