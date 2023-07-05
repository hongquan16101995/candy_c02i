package com.example.candy.service.impl;

import com.example.candy.model.Role;
import com.example.candy.model.User;
import com.example.candy.model.UserPrinciple;
import com.example.candy.model.dto.UserDTO;
import com.example.candy.repository.IRoleRepository;
import com.example.candy.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;

    public User findByUsername(String username) {
        return iUserRepository.findAllByUsername(username);
    }

    public boolean add(User user) {
        iUserRepository.save(user);
        return true;
    }

    public UserDetails loadUserByUsername(String username) {
        List<User> users = iUserRepository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username)) {
                return UserPrinciple.build(user);
            }
        }
        return null;
    }
}
