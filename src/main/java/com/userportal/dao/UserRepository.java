package com.userportal.dao;

import org.springframework.data.repository.Repository;

import com.userportal.entites.User;

import java.util.List;

public interface UserRepository extends Repository<User, Integer> {

    void delete(User user);

    List<User> findAll();

    User findOne(int id);

    User save(User user);
    
    public User findOneByUserName(String username);
   
}
