package com.evan.bs.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.evan.bs.entity.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TaskDAO extends JpaRepository<Task, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndPublisher(String name, String publisher);
    Task findByName(String name);
    List<Task> findAllByAccepter(String accepter);
    List<Task> findAllByPublisher(String publisher);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update task set accepter = ?1, state = 1 where name = ?2 ")
    void setAccepter(String username, String taskname);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update task set accepter = null, state = 0 where name = ?1 ")
    void clearAccepter(String taskname);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update task set state = ?1 where name = ?2 ")
    void setState(int state, String taskname);

}
