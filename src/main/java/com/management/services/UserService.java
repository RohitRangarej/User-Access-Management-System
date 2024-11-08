package com.management.services;

import com.management.entities.Users;

public interface UserService {
    void registerUser(String username, String password, String role);
    Users authenticate(String username, String password);
    boolean exitsUser(String username);
}