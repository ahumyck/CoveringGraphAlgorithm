package com.company.services.builders.graphBuilders;

import com.company.entities.Graph;

public interface GraphBuilder<N,E> {

    Graph build(N nodeInterpretation, E edgeInterpretation);

}
