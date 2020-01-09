package com.company.services.builders.galaxyBuilders;

import com.company.genetic.universe.Galaxy;

public interface GalaxyDTOBuilder<T,G> {
    Galaxy build(T input, G graph);
}
