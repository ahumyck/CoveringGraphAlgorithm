package com.company;

import java.util.ArrayList;

//todo: rename naxui
public class Matrix
{
    private ArrayList<Integer> mass;
    private int satelliteCount;
    private int starCount;//mod

    public Matrix(int satelliteCount, int starCount)
    {
        this.mass = new ArrayList<>(satelliteCount);
        for (int i = 0; i < satelliteCount; i++)
        {
            mass.add(0);
        }
        this.satelliteCount = satelliteCount;
        this.starCount = starCount;
    }

    public ArrayList<Integer> next() throws Exception
    {
        do
        {
            shiftRow(0);
            if(isLastSolution())return null;

        }
        while (!isValidSolution());

        return mass;
    }

    private void shiftRow(int currentRow) throws Exception
    {
        if (currentRow == mass.size())
        {
            return;
        }
        if ((mass.get(currentRow) + 1) % starCount == 0)
        {
            shiftRow(currentRow + 1);
        }
        mass.set(currentRow, (mass.get(currentRow) + 1) % starCount);
    }


    private boolean isValidSolution()
    {
        for (int i = 0; i < starCount; i++)
        {
            if (!mass.contains(i))
                return false;
        }
        return true;
    }

    private boolean isLastSolution(){
        int count = 0;
        for (int i = 0; i < mass.size(); i++)
        {
            count += mass.get(i);
        }
        return count == 0;
    }

    public void print()
    {
        System.out.println(mass);
        System.out.println();
    }
}
