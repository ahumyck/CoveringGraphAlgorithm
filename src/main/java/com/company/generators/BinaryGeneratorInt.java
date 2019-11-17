//package com.company.generators;
//
//public class BinaryGeneratorInt implements BinaryGenerator {
//    CombinationBinaryGenerator generator;
//    int totalSize;
//    int nonZeroBits = 0;
//
//    public BinaryGeneratorInt(int n) {
//        this.totalSize = n;
//        generator = new CombinationBinaryGenerator(totalSize,nonZeroBits);
//    }
//
//    @Override
//    public boolean hasNext() {
//        return generator.hasNext();
//    }
//
//    @Override
//    public int[] next() {
//    }
//
//    private boolean hasNext(boolean value){
//        return generator.hasNext() && nonZeroBits < totalSize / 2;
//    }
//}
