package com.ec.controller;

import com.ec.jwt.service.AuthenticationService;
import com.ec.model.User;
import com.ec.service.CartService;
import com.ec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable  int id){
        try{
            Optional<User> user=userService.getUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }

    }

    @PostMapping("/promote/{id}")
    public ResponseEntity promoteToAdmin(@PathVariable int id, @AuthenticationPrincipal UserDetails userDetails) throws AccessDeniedException {
        if(userDetails !=null){
            User loggedInUser=userService.findByEmail(userDetails.getUsername());
            userService.promoteToAdmin(id,loggedInUser);
            return ResponseEntity.status(HttpStatus.OK).body("User promoted Succesfully");
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication required");
        }
    }

    @GetMapping
    public ResponseEntity getUsers(){
        try{
           List<User> users= userService.getUsers();
           return ResponseEntity.status(HttpStatus.FOUND).body(users);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }


    @PutMapping("{id}")
    public ResponseEntity updateUser(@PathVariable  int id,@RequestBody User user){
        try{
            userService.updateUser(id,user);
            return ResponseEntity.status(HttpStatus.OK).body(user);

        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't Uploaded");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(int id){
        try{

            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
    }

}
