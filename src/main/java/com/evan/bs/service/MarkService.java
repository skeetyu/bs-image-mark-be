package com.evan.bs.service;

import com.evan.bs.dao.MarkDAO;
import com.evan.bs.entity.Mark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkService {
    @Autowired
    MarkDAO markDAO;

    public Mark getMarks(String taskname, String graphname){
        if(markDAO.existsByTaskAndGraph(taskname, graphname))
            return markDAO.findByTaskAndGraph(taskname, graphname);
        else return null;
    }

    public void addMark(Mark mark){
        markDAO.save(mark);
    }

    public int countMarkedGraphsOfTask(String taskname){
        return markDAO.countGraphsByTask(taskname);
    }
}
