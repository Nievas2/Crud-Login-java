package com.agn.login.controller;

import com.agn.login.entity.User;
import com.agn.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers (){
        return userService.getUsers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") Long id){
        return userService.getUser(id);
    }
    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @PutMapping
    public ResponseEntity<Object> updateUser (@RequestBody User user){
        return userService.updateUser(user);
    }
}
