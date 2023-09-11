package com.example.usermicroservice.controller;

import com.example.usermicroservice.dto.UserRequestDTO;
import com.example.usermicroservice.model.User;
import com.example.usermicroservice.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {

        this.userService = userService;

    }

    /**
     * Performs Get ALL operation .
     *
     * @return All Users in Database
     */
    @QueryMapping
    public Iterable<User> getAllUsers() {
        return userService.getAll();
    }

    /**
     * Performs Get User operation .
     *
     * @param userName The name of the User.
     * @return User in Database
     */
    @QueryMapping
    public Optional<User> getUserByName(
            @Argument String userName) {
        return userService.getUserByUserName(userName);
    }

    /**
     * Performs Create User operation .
     *
     * @param user The User DTO Json
     * @return User
     */
    @MutationMapping
    User addUser(
            @Argument UserRequestDTO user) {
        return userService.addUser(user);
    }

    /**
     * Performs Update user operation .
     *
     * @param user The user DTO Json
     * @return user
     */
    @MutationMapping
    User updateUser(
            @Argument String userName,
            @Argument UserRequestDTO user) {
        return userService.updateUser(userName, user);
    }

    /**
     * Performs Delete user operation .
     *
     * @param userName The user DTO Json
     * @return userName
     */
    @MutationMapping
    String deleteUser(
            @Argument String userName) {
        return userService.deleteUser(userName);
    }


}
