package com.company.services.builders;

import com.company.entities.Graph;

public interface GraphBuilder<N,E> {

    Graph build(N nodeInterpretation, E edgeInterpretation);

}
