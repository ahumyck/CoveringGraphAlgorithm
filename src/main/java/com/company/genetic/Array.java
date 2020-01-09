package com.company.genetic;

import java.util.Arrays;

public class Array {

    private int[] array;

    public int length;

    @Override
    public String toString() {
        return  Arrays.toString(array);
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public Array(int size) {
        this.array = new int[size];
        this.length = size;
    }

    public Array(int[] array) {
        this.array = array;
        this.length = array.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Array array1 = (Array) o;
        return Arrays.equals(array, array1.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
