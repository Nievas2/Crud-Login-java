package com.agn.login.controller;

import com.agn.login.controller.dto.UserDto;
import com.agn.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getUsers (){
        return userService.getUsers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id){
        return userService.getUser(id);
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws URISyntaxException {
        return userService.createUser(userDto);
    }
    @PutMapping
    public ResponseEntity<?> updateUser (@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }
    @PostMapping( "/test")
    public String welcome()
    {
        return "Welcome from secure endpoint";
    }
}
