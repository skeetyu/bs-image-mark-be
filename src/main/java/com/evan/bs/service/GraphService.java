package com.evan.bs.service;

import java.util.List;

import com.evan.bs.dao.GraphDAO;
import com.evan.bs.dao.TaskGraphDAO;
import com.evan.bs.dao.UserGraphDAO;
import com.evan.bs.entity.Graph;
import com.evan.bs.entity.UserGraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphService {
    @Autowired
    GraphDAO graphDAO;
    @Autowired
    UserGraphDAO userGraphDAO;
    @Autowired
    TaskGraphDAO taskGraphDAO;
 
    public boolean existByGraphname(String graphname){
        return graphDAO.existsByName(graphname);
    }

    public void addGraph(Graph graph){
        graphDAO.save(graph);
    }

    public void addUserGraph(UserGraph userGraph){
        userGraphDAO.save(userGraph);
    }

    public List<Graph> getUserGraphs(String username){
        return graphDAO.getAllGraphsByUsername(username);
    }

    public List<Graph> getTaskGraphs(String taskname){
        return graphDAO.getAllGraphsByTaskname(taskname);
    }
}
