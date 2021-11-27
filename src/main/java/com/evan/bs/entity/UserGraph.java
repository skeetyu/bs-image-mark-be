package com.evan.bs.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(UserGraphPK.class)
@Table(name = "user_graph")
public class UserGraph {
    @Id
    private String username;

    @Id
    private String graphname;

    public UserGraph(){ }
    public UserGraph(String username, String graphname){
        this.username = username;
        this.graphname = graphname;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getGraphname(){
        return this.graphname;
    }

    public void setGraphname(String graphname){
        this.graphname = graphname;
    }
}
