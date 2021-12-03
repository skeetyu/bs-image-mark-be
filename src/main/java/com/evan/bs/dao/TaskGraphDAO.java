package com.evan.bs.dao;

import com.evan.bs.entity.TaskGraph;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskGraphDAO extends JpaRepository<TaskGraph, Integer> {
    int countGraphnamesByTaskname(String taskname);
}
