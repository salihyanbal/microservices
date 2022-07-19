package com.microservices.user.service;

import com.microservices.user.dto.CreateUserRequest;
import com.microservices.user.dto.UpdateUserRequest;
import com.microservices.user.dto.UserDto;
import com.microservices.user.model.User;

import java.util.List;

public interface UserService {

    UserDto createUser(CreateUserRequest userRequest);
    UserDto updateUser(String id,UpdateUserRequest userRequest);
    boolean deleteUser(String id);

    List<User> getAllUsers();
    User getUserById(String id);
    User getUserByEmail(String email);


}
