package com.company;

public class EdgeMatrix
{
    private int[][] incidenceMatrix;

    public EdgeMatrix(int size)
    {
        incidenceMatrix = new int[size][size];
    }

    public void setCell(int i, int j, int value){
        incidenceMatrix[i][j] = value;
    }
    public int getCell(int i, int j){
        return incidenceMatrix[i][j];
    }

}
