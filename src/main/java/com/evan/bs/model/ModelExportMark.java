package com.evan.bs.model;

public class ModelExportMark {
    String task;
    String graph;
    String path;
    String width;
    String height;
    String type;

    public ModelExportMark(){ }
    public ModelExportMark(String task, String graph, String path, String width, String height, String type){
        this.task = task;
        this.graph = graph;
        this.path = path;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public String getTask(){
        return this.task;
    }

    public void setTask(String task){
        this.task = task;
    }

    public String getGraph(){
        return this.graph;
    }

    public void setGraph(String graph){
        this.graph = graph;
    }

    public String getPath(){
        return this.path;
    }

    public void setPath(String path){
        this.path = path;
    }

    public String getWidth(){
        return this.width;
    }

    public void setWidth(String width){
        this.width = width;
    }

    public String getHeight(){
        return this.height;
    }

    public void setHeight(String height){
        this.height = height;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }
}
