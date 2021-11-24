package com.evan.bs.dao;

import com.evan.bs.pojo.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User getByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    @Transactional
    void deleteByUsername(String username);
}
