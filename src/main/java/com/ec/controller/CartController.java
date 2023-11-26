package com.ec.controller;

import com.ec.model.Cart;
import com.ec.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/")
    public ResponseEntity addCart(@RequestBody Cart cart){
        try{
            cartService.createCart(cart);
            return ResponseEntity.status(HttpStatus.OK).body(cart);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't Create Cart");
        }
    }


    @PutMapping("/")
    public ResponseEntity UpdateCart(@RequestBody Cart cart){
        try{
            cartService.createCart(cart);
            return ResponseEntity.status(HttpStatus.OK).body(cart);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't Create Cart");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCart(@PathVariable  int id){
        try{
            cartService.deleteCart(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doesn't exists");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getCart( @PathVariable int id){
        try{
           Optional<Cart> cart= cartService.getCart(id);
            return ResponseEntity.status(HttpStatus.OK).body(cart);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }
////
    @GetMapping("/")
    public ResponseEntity getCarts(){
        try{
            List<Cart> cart= cartService.getCarts();
            return ResponseEntity.status(HttpStatus.OK).body(cart);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }
}
