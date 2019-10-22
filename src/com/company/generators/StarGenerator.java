package com.company.generators;

import java.util.ArrayList;
import java.util.List;

public class StarGenerator implements Generator {

    private int starCount;
    private int vertexCount;
    private ArrayList<Integer> arr = null;

    public StarGenerator(int starCount, int vertexCount) {
        this.starCount = starCount;
        this.vertexCount = vertexCount;
    }

    @Override
    public List<Integer> next() {
        if (arr == null)
        {
            arr = new ArrayList<>(starCount);
            for (int i = 0; i < starCount; i++)
                arr.add(i);
            return arr;
        }
        for (int i = starCount - 1; i >= 0; i--)
            if (arr.get(i) < vertexCount - starCount + i)
            {
                arr.set(i,arr.get(i) + 1);
                for (int j = i; j < starCount - 1; j++)
                    arr.set(j + 1, arr.get(j) + 1);
                return (List<Integer>) arr.clone();
            }
        return null;
    }
}
