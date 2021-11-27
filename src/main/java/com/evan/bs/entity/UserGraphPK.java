package com.evan.bs.entity;

import java.io.Serializable;

public class UserGraphPK implements Serializable{
    private String username;
    private String graphname;

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
