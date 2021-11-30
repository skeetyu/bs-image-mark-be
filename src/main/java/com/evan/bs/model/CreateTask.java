package com.evan.bs.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class CreateTask {
    List<String> graphs;
    String taskname;

    public CreateTask() { }
    public CreateTask(List<String> graphs, String taskname){
        this.graphs = graphs;
        this.taskname = taskname;
    }

    public List<String> getGraphs(){
        return this.graphs;
    }

    public void setGraphs(List<String> graphs){
        this.graphs = graphs;
    }

    public String getTaskname(){
        return this.taskname;
    }

    public void setTaskname(String taskname){
        this.taskname = taskname;
    }
}
