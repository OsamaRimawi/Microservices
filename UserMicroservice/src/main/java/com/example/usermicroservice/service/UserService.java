package com.example.usermicroservice.service;

import com.example.usermicroservice.dto.UserRequestDTO;
import com.example.usermicroservice.mapper.UserDTOMapper;
import com.example.usermicroservice.model.User;
import com.example.usermicroservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;


    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User addUser(UserRequestDTO user) {
        User newUser = userDTOMapper.userDTOToUser(user);
        return userRepository.save(newUser);
    }

    public User updateUser(String userName, UserRequestDTO user) {
        Optional<User> existingUser = userRepository.findByUserName(userName);
        userDTOMapper.updateUserFromDTO(user, existingUser.orElse(null));
        return userRepository.save(existingUser.orElse(null));
    }

    @Transactional
    public String deleteUser(String userName) {
        return userRepository.deleteByUserName(userName);
    }

}
