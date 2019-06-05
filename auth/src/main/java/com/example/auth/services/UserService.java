package com.example.auth.services;

import com.example.auth.entity.User;
import com.example.auth.exception.ResourceNotFoundException;

public interface UserService {

    User findSameUser(String name, String eMail);

    User saveUser(User user);

    User findUser(String name);

    User findById(Long id) throws ResourceNotFoundException;

}
