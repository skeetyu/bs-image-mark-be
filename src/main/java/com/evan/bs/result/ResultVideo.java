package com.evan.bs.result;

public class ResultVideo {
    private int code;
    private String[] graphs = new String[10];

    public ResultVideo(){ }
    public ResultVideo(int code, String[] graphs){
        this.code = code;
        this.graphs = graphs;
    }

    public int getCode(){
        return this.code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public String[] getGraphs(){
        return this.graphs;
    }

    public void setGraphs(String[] graphs){
        this.graphs = graphs;
    }
}
