package com.evan.bs.model;

public class SubmitMark {
    int state;
    String task;
    String graph;
    String tag;
    String notation;
    String description;

    public SubmitMark() { }
    public SubmitMark(int state, String task, String graph, String tag, String notation, String description){
        this.state = state;
        this.task = task;
        this.graph = graph;
        this.tag = tag;
        this.notation = notation;
        this.description = description;
    }

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        this.state = state;
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

    public String getTag(){
        return this.tag;
    }

    public void setTag(String tag){
        this.tag = tag;
    }

    public String getNotation(){
        return this.notation;
    }

    public void setNotation(String notation){
        this.notation = notation;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
