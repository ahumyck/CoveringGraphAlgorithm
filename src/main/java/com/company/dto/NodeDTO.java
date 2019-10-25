package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeDTO
{
    private String id;
    private String label;
    private DataDTO data;
//    private NodePositionDTO position;
//    private DimensionDTO dimension;
}
