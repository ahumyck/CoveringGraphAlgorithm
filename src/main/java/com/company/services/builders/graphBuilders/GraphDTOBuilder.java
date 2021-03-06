package com.company.services.builders.graphBuilders;

import com.company.dto.dtoEntites.GraphDTO;

public interface GraphDTOBuilder<T>
{
    GraphDTO build(T input);
}
