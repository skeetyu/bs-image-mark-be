package com.evan.bs.result;

import com.evan.bs.entity.Task;

import java.util.List;

public class ResultTask {
    private int code;
    private List<Task> tasks;

    public ResultTask() { }
    public ResultTask(int code, List<Task> tasks){
        this.code = code;
        this.tasks = tasks;
    }

    public int getCode(){
        return this.code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public List<Task> getTasks(){
        return this.tasks;
    }

    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
    }


}
