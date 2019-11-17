package com.company.generators;


public class BinaryGenerator {
    private int previousCombination;
    private int currentCombination;
    private int totalSize, nonZeroBits;

    public BinaryGenerator(int totalSize, int nonZeroBits){
        previousCombination = calculateFirstCombination(totalSize,nonZeroBits);
        currentCombination = previousCombination;
        this.totalSize = totalSize;
        this.nonZeroBits = nonZeroBits;
    }

    public int[] next() {
        previousCombination = currentCombination;
        currentCombination = next(currentCombination);
        return translateToArrayOfBits(previousCombination);
    }

    private int[] translateToArrayOfBits(int number){
        int[] bits = new int[totalSize];
        int lowerBit = 1;
        for (int i = totalSize - 1; i >= 0; i--) {
            bits[i] = number & lowerBit;
            number = number >> 1;
        }
        return bits;
    }

    public boolean hasNext() {
        return !(currentCombination == 0);
    }

    private int next(int combination)
    {
        if ((combination & (combination+1)) == 0)
            return 0;

        if ((combination & 1) != 0) {
            return addNonZeroBitAfterLastNonZeroBit(next(combination >> 1) << 1);
        }
        else
            return shiftLastNonZeroBit(combination);
    }

    private int shiftLastNonZeroBit(int combination){
        return ((combination-1) ^ ((combination^(combination-1)) >> 2));
    }

    private int addNonZeroBitAfterLastNonZeroBit(int combination) {
        return ( combination | ( ((combination^(combination-1)) + 1) >> 2 ) );
    }

    private int calculateFirstCombination(int n, int k) {
        return (((1 << k) - 1) << (n - k));
    }

}