package com.company.services.graphBuilders;

import com.company.dto.GraphDTO;

public interface GraphDTOBuilder<T>
{
    GraphDTO build(T input);
}
