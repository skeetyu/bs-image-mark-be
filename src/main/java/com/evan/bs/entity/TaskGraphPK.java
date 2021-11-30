package com.evan.bs.entity;

import java.io.Serializable;

public class TaskGraphPK implements Serializable{
    private String taskname;
    private String graphname;

    public String getTaskname(){
        return this.taskname;
    }

    public void setTaskname(String taskname){
        this.taskname = taskname;
    }

    public String getGraphname(){
        return this.graphname;
    }

    public void setGraphname(String graphname){
        this.graphname = graphname;
    }
}
