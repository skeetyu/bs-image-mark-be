package com.evan.bs.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TaskGraphPK.class)
@Table(name = "task_graph")
public class TaskGraph {
    @Id
    private String taskname;

    @Id
    private String graphname;

    public TaskGraph(){ }
    public TaskGraph(String taskname, String graphname){
        this.taskname = taskname;
        this.graphname = graphname;
    }

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
