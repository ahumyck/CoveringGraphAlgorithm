package com.company.services.graphBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface StarPlanBuilder<T> {
    Map<Integer, ArrayList<Integer>> build(T input);
}
