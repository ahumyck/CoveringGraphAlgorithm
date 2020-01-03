package com.company.generators;


import com.company.utils.Translator;

public class CombinationBinaryGeneratorInt implements BinaryGenerator {
    private int previousCombination;
    private int currentCombination;
    private int totalSize;

    public CombinationBinaryGeneratorInt(int totalSize, int nonZeroBits){
        previousCombination = calculateFirstCombination(totalSize,nonZeroBits);
        currentCombination = previousCombination;
        this.totalSize = totalSize;
    }

    public long[] next() {
        previousCombination = currentCombination;
        currentCombination = next(currentCombination);
        return Translator.translateToArrayOfBits(previousCombination,totalSize);
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