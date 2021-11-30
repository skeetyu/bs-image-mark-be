package com.evan.bs.service;

import java.util.List;

import com.evan.bs.dao.TaskDAO;
import com.evan.bs.dao.TaskGraphDAO;
import com.evan.bs.entity.Task;
import com.evan.bs.entity.TaskGraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    TaskDAO taskDAO;
    @Autowired
    TaskGraphDAO taskGraphDAO;

    public boolean existsByName(String taskname){
        return taskDAO.existsByName(taskname);
    }

    public void addTask(Task task){
        taskDAO.save(task);
    }

    public void addGraph(TaskGraph tg){
        taskGraphDAO.save(tg);
    }

    public List<Task> getTasks(){
        return taskDAO.findAll();
    }

    public boolean isPublisher(String username, String taskname){
        return taskDAO.existsByNameAndPublisher(taskname, username);
    }

    public void acceptTask(String username, String taskname){
        taskDAO.setAccepter(username, taskname);
    }

    public List<Task> getTodoTasks(String username){
        return taskDAO.findAllByAccepter(username);
    }

    public void withdrawTask(String taskname){
        taskDAO.clearAccepter(taskname);
    }

    public List<Task> getPublishedTasks(String username){
        return taskDAO.findAllByPublisher(username);
    }
}
