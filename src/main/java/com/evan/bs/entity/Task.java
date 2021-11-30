package com.evan.bs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name = "task")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    int tid;
    
    String name;
    String publisher;
    String accepter;
    int state;  // 0: 未领取    1: 领取未完成   2: 已完成

    public Task(){
        
    }

    public Task(String name, String publisher){
        this.name = name;
        this.publisher = publisher;
        this.state = 0;
    }

    public int getTid(){
        return this.tid;
    }

    public void setTid(int tid){
        this.tid = tid;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPublisher(){
        return this.publisher;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public String getAccepter(){
        return this.accepter;
    }

    public void setAccepter(String accepter){
        this.accepter = accepter;
    }

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        this.state = state;
    }
}
