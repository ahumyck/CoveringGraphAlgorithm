package com.company.services.builders;

import com.company.dto.GraphDTO;

public interface GraphDTOBuilder<T>
{
    GraphDTO build(T input);
}
