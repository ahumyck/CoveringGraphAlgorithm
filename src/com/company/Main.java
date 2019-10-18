package com.company;

public class Main {

    public static void main(String[] args) throws Exception
    {
        Matrix matrix = new Matrix(5,3);
        for (int i = 0; i < 150; i++)
        {
            System.out.println(matrix.next());

        }


    }
}
