package com.evan.bs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import com.evan.bs.entity.Graph;
import com.evan.bs.entity.UserGraph;
import com.evan.bs.result.Result;
import com.evan.bs.result.ResultGraph;
import com.evan.bs.result.ResultVideo;
import com.evan.bs.service.GraphService;
import com.evan.bs.util.StringUtils;
import com.evan.bs.util.VideoUtils;

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
    @PostMapping(value = "/api/uploadpictures")
    @ResponseBody
    public Result uploadPictures(MultipartFile file) throws Exception{
        /* 存储图片 */
        String folder = "D:/Coding/bs-image-mark-fe/src/assets/upload";
        File imageFolder = new File(folder);
        String graphname = StringUtils.getRandomString(6);
        while(graphService.existByGraphname(graphname)){
            graphname = StringUtils.getRandomString(6);
        }

        File f = new File(imageFolder, graphname + file.getOriginalFilename()
            .substring(file.getOriginalFilename().length() - 4).toLowerCase());
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
    @PostMapping(value = "/api/uploadvideo")
    @ResponseBody
    public ResultVideo uploadVideo(MultipartFile file) throws Exception{
        String folder = "D:/Coding/bs-image-mark-fe/src/assets/video";
        File videoFolder = new File(folder);
        File f = new File(videoFolder, file.getOriginalFilename());
        if(!f.getParentFile().exists()) f.getParentFile().mkdirs();
        try{
            file.transferTo(f);
        }catch(IOException e){
            e.printStackTrace();
            return new ResultVideo(400, null);
        }

        String videoUrl = folder + "/" + file.getOriginalFilename();
        String[] imgUrls = new String[10];
        String[] graphNames = new String[10];
        for(int i = 0; i < 10; i++){
            String graphname = StringUtils.getRandomString(6);
            while(graphService.existByGraphname(graphname)){
                graphname = StringUtils.getRandomString(6);
            }
            graphNames[i] = graphname;
            imgUrls[i] = "D:/Coding/bs-image-mark-fe/src/assets/temp/" + graphname + ".jpg"; 
        }
        
        VideoUtils.fetchFrames(videoUrl, imgUrls);
        return new ResultVideo(201, graphNames);
    }

    @CrossOrigin
    @PostMapping(value = "/api/videotographs")
    @ResponseBody
    public Result videoToGraphs(@RequestBody Map<String, String[]> graphsparam){
        String[] graphs = graphsparam.get("graphs");
        for(int i = 0; i < graphs.length; i++){
            try{
                FileInputStream fis = new FileInputStream("D:/Coding/bs-image-mark-fe/src/assets/temp/" + graphs[i] + ".jpg");
                FileOutputStream fos = new FileOutputStream("D:/Coding/bs-image-mark-fe/src/assets/upload/" + graphs[i] + ".jpg");
                byte[] b = new byte[fis.available()];
                fis.read(b);
                fos.write(b);
                fis.close();
                fos.close();
            }catch(IOException e){
                e.printStackTrace();
                continue;
            }
            
            /* 存储graph表 */
            Graph graph = new Graph(graphs[i], graphs[i] + ".jpg");
            graphService.addGraph(graph);

            /* 存储upload表 */
            String username = SecurityUtils.getSubject().getPrincipal().toString();
            UserGraph userGraph = new UserGraph(username, graphs[i]);
            graphService.addUserGraph(userGraph);
        }

        return new Result(200);
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
