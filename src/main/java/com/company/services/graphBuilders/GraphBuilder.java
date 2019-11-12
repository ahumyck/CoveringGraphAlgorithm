package com.company.services.graphBuilders;

import com.company.entities.Graph;

public interface GraphBuilder<N,E> {

    Graph build(N nodeInterpretation, E edgeInterpretation);

}
