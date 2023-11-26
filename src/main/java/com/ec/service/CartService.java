package com.ec.service;

import com.ec.model.Cart;
import com.ec.model.Product;
import com.ec.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart createCart(Cart cart){
        return cartRepository.save(cart);

    }

    public void deleteCart(int id){
        cartRepository.deleteById(id);
    }

    public Optional<Cart> getCart(int id){
        return  cartRepository.findById(id);
    }

    public List<Cart> getCarts(){
        return cartRepository.findAll();
    }

    public Cart updateCart(Cart cart){
        return cartRepository.save(cart);
    }

}
