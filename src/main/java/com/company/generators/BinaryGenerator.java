package com.company.generators;
import java.math.BigInteger;

public class BinaryGenerator {
    private BigInteger previousCombination;
    private BigInteger currentCombination;

    public BinaryGenerator(int totalSize, int numberOfNonZeroBits){
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
        if(combination.and(combination.add(BigInteger.ONE)).compareTo(BigInteger.ZERO) == 0){
            System.out.println("oy may " + currentCombination);
            return BigInteger.ZERO;
        }

        if(combination.and(BigInteger.ONE).compareTo(BigInteger.ZERO) != 0){
            System.out.println("if " + currentCombination);
            return addNonZeroBitAfterLastNonZeroBit(next(combination.shiftLeft(1)).shiftRight(1));
        }
        else {
            System.out.println("else " + currentCombination);
            BigInteger integer = shiftLastNonZeroBit(combination);
            System.out.println("after else " + integer);
            return integer;
        }

//        if ((combination & (combination+1)) == 0)
//            return 0;

//        if ((combination & 1) != 0)
//            return addNonZeroBitAfterLastNonZeroBit( next(combination >> 1) << 1 );
//        else
//            return shiftLastNonZeroBit(combination);
    }

    private BigInteger shiftLastNonZeroBit(BigInteger combination){
        BigInteger firstStep = combination.subtract(BigInteger.ONE);
//        return ((combination-1) ^ ((combination^(combination-1)) >> 2));
    }
//
    private BigInteger addNonZeroBitAfterLastNonZeroBit(BigInteger combination) {
        return combination.or(combination.xor(combination.subtract(BigInteger.ONE)).add(BigInteger.ONE).shiftLeft(2));
//        return ( combination | ( ((combination^(combination-1)) + 1) >> 2 ) );
    }

    private BigInteger calculateFirstCombination(BigInteger n, BigInteger k) {
        return BigInteger.ONE.shiftLeft(k.intValue()).subtract(BigInteger.ONE).shiftLeft(n.subtract(k).intValue());
    }

}
