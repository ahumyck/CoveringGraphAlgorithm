package com.company.genetic.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Settings {

    public static final Settings DEFAULT = new Settings(
            false,20,4,10,true,1,false,100
    );

    private boolean isIncludeGreedy;
    private int totalStuffingSize;
    private int solvesStuffingSize;
    private int selectionSize;
    private boolean isMutation;
    private int reinitCount;
    private boolean isCheckValidSolves;
    private int roundCount;
}
