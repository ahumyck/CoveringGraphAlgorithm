package com.company.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coefficient {
    int star, satellite;
    int weight;


    public String string() {
        return "" + weight;
    }

    @Override
    public String toString(){
        return "[from: " + star + " ,to: " + satellite + ",weight: " + weight + "]";
    }
}
