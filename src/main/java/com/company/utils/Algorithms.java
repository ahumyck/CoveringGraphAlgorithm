package com.company.utils;
import java.util.*;

public class Algorithms {

    public static int contains(List<Integer> sortedArray, int key){
        int low = 0;
        int high = sortedArray.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedArray.get(mid) < key) {
                low = mid + 1;
            } else if (sortedArray.get(mid) > key) {
                high = mid - 1;
            } else if (sortedArray.get(mid) == key) {
                return mid;
            }
        }
        return -1;
    }

    public static boolean intercept(List<Integer> first_array, List<Integer> second_array){
        int i = 0;
        int j = 0;
        while(i < first_array.size() && j < second_array.size()){
            if (first_array.get(i) > second_array.get(j)){
                j++;

            } else if (first_array.get(i) < second_array.get(j)){
                i++;

            } else {
                return true;
            }
        }
        return false;
    }
}
