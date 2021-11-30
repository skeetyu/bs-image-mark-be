package com.evan.bs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name = "graph")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Graph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gid")
    int gid;
    
    String name;
    String path;

    public Graph(){

    }

    public Graph(String name, String path){
        this.name = name;
        this.path = path;
    }

    public int getGid(){
        return this.gid;
    }

    public void setGid(int gid){
        this.gid = gid;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPath(){
        return this.path;
    }

    public void setPath(String path){
        this.path = path;
    }

}
