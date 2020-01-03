package com.company.generators;

import com.company.utils.Translator;

public class CombinationBinaryGeneratorLong implements BinaryGenerator {
    private long previousCombination;
    private long currentCombination;
    private long totalSize;

    public CombinationBinaryGeneratorLong(long totalSize, long nonZeroBits){
        previousCombination = calculateFirstCombination(totalSize,nonZeroBits);
        currentCombination = previousCombination;
        this.totalSize = totalSize;
    }

    public long[] next() {
        previousCombination = currentCombination;
        currentCombination = next(currentCombination);
        return Translator.translateToArrayOfBits(previousCombination,(int)totalSize);
    }

    public boolean hasNext() {
        return !(currentCombination == 0);
    }

    private long next(long combination)
    {
        if ((combination & (combination+1)) == 0)
            return 0;

        if ((combination & 1) != 0) {
            return addNonZeroBitAfterLastNonZeroBit(next(combination >> 1) << 1);
        }
        else
            return shiftLastNonZeroBit(combination);
    }

    private long shiftLastNonZeroBit(long combination){
        return ((combination-1) ^ ((combination^(combination-1)) >> 2));
    }

    private long addNonZeroBitAfterLastNonZeroBit(long combination) {
        return ( combination | ( ((combination^(combination-1)) + 1) >> 2 ) );
    }

    private long calculateFirstCombination(long n, long k) {
        return (((1L << k) - 1) << (n - k));
    }
}
