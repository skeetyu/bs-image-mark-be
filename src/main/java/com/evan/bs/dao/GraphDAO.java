package com.evan.bs.dao;

import com.evan.bs.pojo.Graph;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphDAO extends JpaRepository<Graph, Integer> {
    Graph findByName(String name);
}
