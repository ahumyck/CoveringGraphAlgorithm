package com.company.generators;

import java.util.ArrayList;

//todo: rename naxui
//todo: fix case when starCount == 1
public class SatellitePermutationGeneratorUsingStars
{
    private ArrayList<Integer> mass;
    private int starCount;//mod

    public SatellitePermutationGeneratorUsingStars(int satelliteCount, int starCount)
    {
        this.mass = new ArrayList<>(satelliteCount);
        for (int i = 0; i < satelliteCount; i++)
        {
            mass.add(0);
        }
        this.starCount = starCount;
    }

    public ArrayList<Integer> next()
    {
        do
        {
            shiftRow(0);
            if(isLastSolution()) return null;

        }
        while (!isValidSolution());

        return mass;
    }

    private void shiftRow(int currentRow)
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
        for (Integer number:
             mass) {
            count  += number;
        }
        return count == 0;
    }

    @Override
    public String toString() {
        return mass.toString();
    }
}
