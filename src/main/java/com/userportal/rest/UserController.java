package com.userportal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.userportal.entites.Role;
import com.userportal.entites.User;
import com.userportal.service.UserService;
import com.userportal.vo.UserVO;

import java.security.Principal;
import java.util.List;

@CrossOrigin//(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/api"})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/createUser")
    public User create(@RequestBody UserVO userVO){
        return userService.create(userVO);
    }
    @PostMapping(path = "/create")
    public void createUser(@RequestBody UserVO user){
        // userService.create(user);
    }
    
    @GetMapping(path = {"/{id}"})
    public User findOne(@PathVariable("id") int id){
        return userService.findById(id);
    }

    @PutMapping
    public User update(@RequestBody User user){
        return userService.update(user);
    }

    @DeleteMapping(path ={"/{id}"})
    public User delete(@PathVariable("id") int id) {
        return userService.delete(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('Admin')")
    public List<User> findAll(){
        return userService.findAll();
    }
    
    @GetMapping(path = "/getUser")
    public  User getUserByName(String username) {
    	return userService.findByName(username);
    }
    
    @GetMapping(path = "/getRoles")
	public List<Role> getRoles(){
    	return userService.getAllRoles();
    }
}
