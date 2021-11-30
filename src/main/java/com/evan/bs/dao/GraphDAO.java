package com.evan.bs.dao;

import java.util.List;

import com.evan.bs.entity.Graph;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GraphDAO extends JpaRepository<Graph, Integer> {
    Graph findByName(String name);
    boolean existsByName(String name);

    @Query(nativeQuery = true, value = "select * from graph where name in (select graphname from user_graph where username = ?1 )")
    List<Graph> getAllGraphsByUsername(String username);

    @Query(nativeQuery = true, value = "select * from graph where name in (select graphname from task_graph where taskname = ?1 )")
    List<Graph> getAllGraphsByTaskname(String taskname);
}
