package com.evan.bs.service;

import com.evan.bs.dao.UserDAO;
import com.evan.bs.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public boolean existByName(String username){
        return userDAO.existsByUsername(username);
    }

    public boolean existByEmail(String email){
        return userDAO.existsByEmail(email);
    }

    public User getByName(String username){
        return userDAO.findByUsername(username);
    }

    public User getLogin(String username, String password){
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public void add(User user){
        userDAO.save(user);
    }
    
    public void deleteByName(String username){
        userDAO.deleteByUsername(username);
    }
}
