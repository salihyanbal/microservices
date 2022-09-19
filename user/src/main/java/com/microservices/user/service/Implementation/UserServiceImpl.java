package com.microservices.user.service.Implementation;

import com.microservices.user.dto.CreateUserRequest;
import com.microservices.user.dto.UpdateUserRequest;
import com.microservices.user.dto.UserDto;
import com.microservices.user.exception.UserNotFoundException;
import com.microservices.user.model.User;
import com.microservices.user.repository.UserRepository;
import com.microservices.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(CreateUserRequest userRequest) {
        User user = this.modelMapper.map(userRequest, User.class);
        return this.modelMapper.map(this.userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto updateUser(String id, UpdateUserRequest userRequest) {
        User user = this.getUserById(id);
        User updatedUser = new User(
                user.getId(),
                userRequest.getUserName(),
                user.getPassword(),
                userRequest.getEmail(),
                userRequest.getFirstName(),
                userRequest.getLastName()
        );
        return this.modelMapper.map(this.userRepository.save(updatedUser), UserDto.class);
    }

    @Override
    public boolean deleteUser(String id) {
        this.userRepository.deleteById(id);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
        return user;
    }
}
