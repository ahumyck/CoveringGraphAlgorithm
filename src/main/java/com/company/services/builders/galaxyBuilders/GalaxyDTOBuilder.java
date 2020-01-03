package com.company.services.builders.galaxyBuilders;

import com.company.universe.Galaxy;

public interface GalaxyDTOBuilder<T,G> {
    Galaxy build(T input, G graph);
}
