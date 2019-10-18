package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception
    {
//        Matrix matrix = new Matrix(5,3);
//        for (int i = 0; i < 150; i++)
//        {
//            System.out.println(matrix.next());
//        }

        int[] arr = null;
        while ((arr = Manager.generateCombinations(arr, 3, 8)) != null)
        {
            System.out.println(Arrays.toString(arr));
        }


    }
}
