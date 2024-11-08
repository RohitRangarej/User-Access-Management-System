package com.management.services;

import com.management.repository.UserRepository;
import com.management.entities.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(String username, String password, String role) {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public Users authenticate(String username, String password) {
        Users user=userRepository.findByUsername(username);
        if(user==null) {
        	return null;
        }
        if(user.getPassword().equals(password)) {
        	return user;
        }
        else {
        	return null;
        }
    }

	@Override
	public boolean exitsUser(String username) {
		if(userRepository.findByUsername(username) != null) {
			return true;
		}
		else {
			return false;
		}
	}
}