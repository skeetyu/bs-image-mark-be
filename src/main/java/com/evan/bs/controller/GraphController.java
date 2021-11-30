package com.evan.bs.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.evan.bs.entity.Graph;
import com.evan.bs.entity.UserGraph;
import com.evan.bs.result.Result;
import com.evan.bs.result.ResultGraph;
import com.evan.bs.service.GraphService;
import com.evan.bs.util.StringUtils;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GraphController {
    @Autowired
    GraphService graphService;

    @CrossOrigin
    @PostMapping(value = "/api/upload")
    @ResponseBody
    public Result upload(MultipartFile file) throws Exception{
        /* 存储图片 */
        String folder = "D:/Coding/bs-image-mark-fe/src/assets/upload";
        File imageFolder = new File(folder);
        String graphname = StringUtils.getRandomString(6);
        while(graphService.existByGraphname(graphname)){
            graphname = StringUtils.getRandomString(6);
        }

        File f = new File(imageFolder, graphname + file.getOriginalFilename()
            .substring(file.getOriginalFilename().length() - 4));
        if(!f.getParentFile().exists()) f.getParentFile().mkdirs();
        try{
            /* 存储graph表 */
            file.transferTo(f);
            String imgURL = f.getName();
            Graph graph = new Graph(graphname, imgURL);
            graphService.addGraph(graph);

            /* 存储upload表 */
            String username = SecurityUtils.getSubject().getPrincipal().toString();
            UserGraph userGraph = new UserGraph(username, graphname);
            graphService.addUserGraph(userGraph);

            return new Result(200);
        }catch(IOException e){
            e.printStackTrace();
            return new Result(400);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/getgraphs")
    @ResponseBody
    public ResultGraph getgraphs(){
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        return new ResultGraph(200, graphService.getUserGraphs(username));
    }

    @CrossOrigin
    @PostMapping(value = "/api/gettaskgraphs")
    @ResponseBody
    public ResultGraph getTaskGraphs(@RequestBody Map<String, String> taskparam){
        String taskname = taskparam.get("taskname");
        return new ResultGraph(200, graphService.getTaskGraphs(taskname));
    }
}
