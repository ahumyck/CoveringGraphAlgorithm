package com.company;

import java.util.Arrays;

public class EdgeMatrix
{
    private int[][] incidenceMatrix;

    public EdgeMatrix(int size)
    {
        incidenceMatrix = new int[size][size];
    }

    public void setCell(int i, int j, int value)
    {
        incidenceMatrix[i][j] = value;
    }

    public int getCell(int i, int j)
    {
        return incidenceMatrix[i][j];
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < incidenceMatrix.length; i++)
        {
            for (int j = 0; j < incidenceMatrix.length; j++)
            {
                stringBuilder.append("\t");
                stringBuilder.append(this.getCell(i, j));
            }
            stringBuilder.append("\n");
        }
        return "EdgeMatrix{"
                + stringBuilder.toString() +
                '}';
    }
}
