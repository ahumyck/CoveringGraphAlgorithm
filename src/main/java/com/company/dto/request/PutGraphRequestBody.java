package com.company.dto.request;

public class PutGraphRequestBody {
    private String graphData;

    public PutGraphRequestBody() {
        graphData = "";
    }

    public PutGraphRequestBody(String graphData) {
        this.graphData = graphData;
    }

    public String getGraphData() {
        return graphData;
    }

    public void setGraphData(String graphData) {
        this.graphData = graphData;
    }
}
