package com.aybuke.tokengateway.service;

import com.aybuke.tokengateway.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User saveUser(User user);

    Optional<User> findByUsername(String username);

    List<User> findAllUsers();
}
