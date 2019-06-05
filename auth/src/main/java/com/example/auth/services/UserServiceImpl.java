package com.example.auth.services;

import com.example.auth.entity.User;
import com.example.auth.exception.ResourceNotFoundException;
import com.example.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public User findSameUser(String name, String eMail) {

        User user = userRepo.findByUsername(name);

        if(user != null){
            return user;
        }else return userRepo.findByEmail(eMail);

    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User findUser(String name) {
        return userRepo.findByUsername(name);
    }

    @Override
    public User findById(Long id) throws ResourceNotFoundException {
        return userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }
}
