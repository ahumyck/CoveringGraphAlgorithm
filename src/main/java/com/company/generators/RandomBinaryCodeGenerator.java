package com.company.generators;

import java.util.Random;

public class RandomBinaryCodeGenerator {
    public static int[] getCode(int n){
        int[] code = new int[n];
        code[0] = 1;
        code[1] = 0;
        code[n - 1] = 0;

        Random random = new Random();

        for(int i = 2 ; i < n - 1 ; i++){
            code[i] = random.nextInt(2);
            if(code[i] == 1){
                code[i+1] = 0;
                i++;
            }
        }
        return code;
    }
}
