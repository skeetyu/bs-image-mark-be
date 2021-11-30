package com.evan.bs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name = "mark")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mid")
    int mid;
    
    String task;
    String graph;
    String tag;
    String notation;

    public Mark() { }
    public Mark(String task, String graph, String tag, String notation){
        this.task = task;
        this.graph = graph;
        this.tag = tag;
        this.notation = notation;
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

}
