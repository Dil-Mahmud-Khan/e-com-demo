package com.ec.service;

import com.ec.model.User;
import com.ec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    private final PasswordEncoder passwordEncoder;
//    public UserService(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

//    public User createUser(User user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }



    public User updateUser(User id){
        return userRepository.save(id);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

    public Optional<User> getUser(int id){
       return userRepository.findById(id);
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
//    public User updateUser(){
//        return (User) userRepository.findAll();
//    }

}
