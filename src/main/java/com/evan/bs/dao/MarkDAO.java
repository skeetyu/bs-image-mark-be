package com.evan.bs.dao;

import com.evan.bs.entity.Mark;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

public interface MarkDAO extends JpaRepository<Mark, Integer> {
    boolean existsByTaskAndGraph(String task, String graph);
    Mark findByTaskAndGraph(String task, String graph);
    int countGraphsByTask(String task);
    // @Query(nativeQuery = true, value = "select count(graph) from mark where task = ?1 ")
    // int countMarkedGraphsByTask(String taskname);
}
