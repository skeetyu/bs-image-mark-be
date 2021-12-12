package com.evan.bs.controller;

import java.util.Map;

import com.evan.bs.entity.Mark;
import com.evan.bs.entity.TaskGraph;
import com.evan.bs.model.ModelExportMark;
import com.evan.bs.model.ModelSubmitMark;
import com.evan.bs.result.Result;
import com.evan.bs.result.ResultMark;
import com.evan.bs.service.GraphService;
import com.evan.bs.service.MarkService;
import com.evan.bs.service.TaskService;
import com.evan.bs.util.AnnotationUtils;

// import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MarkController {
    @Autowired
    MarkService markService;
    @Autowired
    GraphService graphService;
    @Autowired
    TaskService taskService;

    @CrossOrigin
    @PostMapping(value = "/api/getmarkgraph")
    @ResponseBody
    public ResultMark getMarkGraph(@RequestBody TaskGraph taskGraph){
        String taskname = taskGraph.getTaskname();
        String graphname = taskGraph.getGraphname();
        String path = graphService.getPath(graphname);
        int state = taskService.getState(taskname);
        Mark mark = markService.getMarks(taskname, graphname);
        String notation, tag, description;
        if(mark == null){
            notation = "";
            tag = "";
            description = "";
            return new ResultMark(201, path, state, notation, tag, description);
        }else{
            notation = mark.getNotation();
            tag = mark.getTag();
            description = mark.getDescription();
            return new ResultMark(200, path, state, notation, tag, description);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/submitmark")
    @ResponseBody
    public Result submitMark(@RequestBody ModelSubmitMark submitMark){
        int state = submitMark.getState();
        String task = submitMark.getTask();
        if(state == 1){
            taskService.updateState(task, 2);
        }
        String graph = submitMark.getGraph();
        String tag = submitMark.getTag();
        String notation = submitMark.getNotation();
        String description = submitMark.getDescription();
        Mark mark = new Mark(task, graph, tag, notation, description);
        markService.addMark(mark);

        return new Result(200);
    }

    @CrossOrigin
    @PostMapping(value = "/api/submittask")
    @ResponseBody
    public Result submitTask(@RequestBody Map<String, String> taskparam){
        String task = taskparam.get("taskname");
        if(markService.countMarkedGraphsOfTask(task) != taskService.countGraphsOfTask(task)){
            return new Result(400);
        }else{
            taskService.updateState(task, 3);
            return new Result(200);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/exportmark")
    @ResponseBody
    public Result exportMark(@RequestBody ModelExportMark requestmark){
        String task = requestmark.getTask();
        String graph = requestmark.getGraph();
        Mark mark = markService.getMarks(task, graph);
        boolean flag = false;

        if(requestmark.getType().equals("PASCAL VOC(.xml)"))
            flag = AnnotationUtils.writeXML(mark.getNotation(), requestmark);
        else if(requestmark.getType().equals("COCO(.txt)"))
            flag = AnnotationUtils.writeCOCO(mark.getNotation(), requestmark);
        else
            flag = false;

        if(flag) return new Result(200);
        else return new Result(400);
    }
}
