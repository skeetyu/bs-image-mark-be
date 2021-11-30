package com.evan.bs.controller;

import java.util.Map;

import com.evan.bs.entity.Task;
import com.evan.bs.entity.TaskGraph;
import com.evan.bs.model.CreateTask;
import com.evan.bs.result.Result;
import com.evan.bs.result.ResultTask;
import com.evan.bs.service.TaskService;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    @Autowired
    TaskService taskService;

    @CrossOrigin
    @PostMapping(value = "/api/createtask")
    @ResponseBody
    public Result createTask(@RequestBody CreateTask createTask){
        String publisher = SecurityUtils.getSubject().getPrincipal().toString();
        String taskname = createTask.getTaskname();

        /* 更新task表 */
        if(taskService.existsByName(taskname)) return new Result(400);
        Task task = new Task(taskname, publisher);
        taskService.addTask(task);

        /* 更新task_graph表 */
        for(String s: createTask.getGraphs()){
            taskService.addGraph(new TaskGraph(taskname, s));
        }
        return new Result(200);
    }

    @CrossOrigin
    @PostMapping(value = "/api/gettasks")
    @ResponseBody
    public ResultTask getTasks(){
        return new ResultTask(200, taskService.getTasks());
    }

    @CrossOrigin
    @PostMapping(value = "/api/accepttask")
    @ResponseBody
    public Result accept(@RequestBody Map<String, String> taskparam){
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        String taskname = taskparam.get("taskname");
        if(taskService.isPublisher(username, taskname)) return new Result(400);
        else taskService.acceptTask(username, taskname);
        return new Result(200);
    }

    @CrossOrigin
    @PostMapping(value = "/api/gettodotasks")
    @ResponseBody
    public ResultTask getTodoTasks(){
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        return new ResultTask(200, taskService.getTodoTasks(username));
    }

    @CrossOrigin
    @PostMapping(value = "/api/withdrawtask")
    @ResponseBody
    public Result withdraw(@RequestBody Map<String, String> taskparam){
        String taskname = taskparam.get("taskname");
        taskService.withdrawTask(taskname);
        return new Result(200);
    }

    @CrossOrigin
    @PostMapping(value = "/api/getpublishedtasks")
    @ResponseBody
    public ResultTask getPublishedTasks(){
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        return new ResultTask(200, taskService.getPublishedTasks(username));
    }

}
