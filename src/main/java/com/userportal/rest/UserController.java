package com.userportal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public User create(@RequestBody User user){
    	System.out.println(user.getUserName() +" ***********************");
        return userService.create(user);
    }
    @PostMapping(path = "/create")
    public void createUser(@RequestBody UserVO user){
    	System.out.println(user.getUserName() +" ***********************");
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
    public List<User> findAll(){
        return userService.findAll();
    }
    
    @GetMapping(path = "/getUser")
    public  User getUserByName(String username) {
    	return userService.findByName(username);
    }
    
    @GetMapping(path = "/login")
	public Principal user(Principal principal) {
    	System.out.println(principal.getName());
		return principal;
	}
}
