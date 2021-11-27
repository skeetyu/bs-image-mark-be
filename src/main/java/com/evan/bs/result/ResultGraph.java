package com.evan.bs.result;

import com.evan.bs.entity.Graph;

import java.util.List;

public class ResultGraph {
    private int code;
    private List<Graph> pictures;

    public ResultGraph() { }
    public ResultGraph(int code, List<Graph> pictures){
        this.code = code;
        this.pictures = pictures;
    }

    public int getCode(){
        return this.code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public List<Graph> getPictures(){
        return this.pictures;
    }

    public void setPictures(List<Graph> pictures){
        this.pictures = pictures;
    }


}
