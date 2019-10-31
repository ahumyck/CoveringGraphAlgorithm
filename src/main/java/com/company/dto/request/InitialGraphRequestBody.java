package com.company.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InitialGraphRequestBody {
    private int size;
    private int[] nodeWeights;
    private int[][] matrix;
}
