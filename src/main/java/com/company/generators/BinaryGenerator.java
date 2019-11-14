package com.company.generators;


public class BinaryGenerator {
    private int previousCombination;
    private int currentCombination;

    public BinaryGenerator(int n, int k){
        previousCombination = calculateFirstCombination(n,k);
        currentCombination = previousCombination;
    }

    public int next() {
        previousCombination = currentCombination;
        currentCombination = next(currentCombination);
        return previousCombination;
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