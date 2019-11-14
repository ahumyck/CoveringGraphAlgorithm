package com.company.generators;
import java.math.BigInteger;

public class BigIntegerBinaryGenerator {
    private BigInteger previousCombination;
    private BigInteger currentCombination;

    public BigIntegerBinaryGenerator(int totalSize, int numberOfNonZeroBits){
        previousCombination = calculateFirstCombination(BigInteger.valueOf(totalSize),BigInteger.valueOf(numberOfNonZeroBits));
        currentCombination = previousCombination;
    }
    
    public BigInteger next() {
        previousCombination = currentCombination;
        currentCombination = next(currentCombination);
        return previousCombination;
    }

    public boolean hasNext() {
        return currentCombination.compareTo(BigInteger.ZERO) != 0;
    }

    private BigInteger next(BigInteger combination)
    {
        if(combination.and(combination.add(BigInteger.ONE)).compareTo(BigInteger.ZERO) == 0)
            return BigInteger.ZERO;


        if(combination.and(BigInteger.ONE).compareTo(BigInteger.ZERO) != 0)
            return addNonZeroBitAfterLastNonZeroBit(next(combination.shiftRight(1)).shiftLeft(1));
        else
            return shiftLastNonZeroBit(combination);
    }

    private BigInteger shiftLastNonZeroBit(BigInteger combination){
         return combination.subtract(BigInteger.ONE).xor(combination.xor(combination.subtract(BigInteger.ONE)).shiftRight(2));
    }

    private BigInteger addNonZeroBitAfterLastNonZeroBit(BigInteger combination) {
        return combination.subtract(BigInteger.ONE).xor(combination).add(BigInteger.ONE).shiftRight(2).or(combination);
    }

    private BigInteger calculateFirstCombination(BigInteger n, BigInteger k) {
        return BigInteger.ONE.shiftLeft(k.intValue()).subtract(BigInteger.ONE).shiftLeft(n.subtract(k).intValue());
    }

}
