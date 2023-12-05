package com.ec.service;

import com.ec.model.Role;
import com.ec.model.User;
import com.ec.repository.UserRepository;
import jakarta.persistence.Access;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User updateUser(int id,User user){
        try {
            Optional<User> use = userRepository.findById(id);
            if (use == null) {
                throw new Exception();
            }
            user.setId(id);
            user.setFirstname(user.getFirstname());
            user.setLastname(user.getLastname());
            user.setUsername(user.getUsername());
            user.setEmail(user.getEmail());
            return user;
        }
        catch (Exception e){
            return  null;
        }
    }

    //promoting a user from USER to ADMIN

    public void promoteToAdmin(int id,User adminUser) throws AccessDeniedException {
        if(adminUser.getRole()!= Role.ADMIN){
            throw new AccessDeniedException("Only admin can make user Admin");
        }

        User promoteUser=userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User not found with id:"+id));
        promoteUser.setRole(Role.ADMIN);
        userRepository.save(promoteUser);
    }

    public void deleteUser(int id){

        try{
            userRepository.deleteById(id);
        }
        catch (Exception e){

        }
    }

    public Optional<User> getUser(int id){
        try{
            return userRepository.findById(id);
        }
        catch (Exception e){
            return  null;
        }

    }

    public List<User> getUsers(){
        try{
            return userRepository.findAll();
        }
        catch (Exception e){
            return  null;
        }
    }


    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new EntityNotFoundException("User not Found"));
    }
}
