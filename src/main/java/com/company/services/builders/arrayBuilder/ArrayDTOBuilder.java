package com.company.services.builders.arrayBuilder;

import com.company.genetic.Array;

public interface ArrayDTOBuilder<T,G> {
    Array build(T input,G graph);
}
