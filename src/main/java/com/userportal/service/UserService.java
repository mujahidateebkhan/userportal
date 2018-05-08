package com.userportal.service;

import java.util.List;

import com.userportal.entites.Role;
import com.userportal.entites.User;
import com.userportal.vo.UserVO;

public interface UserService {

    User create(UserVO userVO);

    User delete(int id);

    List<User> findAll();

    User findById(int id);

    User update(User user);
    
    User findByName(String user);
    
    List<Role> getAllRoles();
}
