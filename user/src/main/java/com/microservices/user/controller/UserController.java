package com.microservices.user.controller;

import com.microservices.user.dto.CreateUserRequest;
import com.microservices.user.dto.UpdateUserRequest;
import com.microservices.user.dto.UserDto;
import com.microservices.user.model.User;
import com.microservices.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

    @GetMapping("/byemail/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(this.userService.getUserByEmail(email));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest userRequest){
        return ResponseEntity.ok(this.userService.createUser(userRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody UpdateUserRequest userRequest){
        return ResponseEntity.ok(this.userService.updateUser(id, userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id){
        return ResponseEntity.ok(this.userService.deleteUser(id));
    }



}
