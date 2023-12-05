package com.ec.service;

import com.ec.model.*;
import com.ec.repository.CartRepository;
import com.ec.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;


    public void addProductTocart(int id,int productId,int quantity){
        User user= (userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id)));


       Product product=productService.getProduct(productId).orElseThrow(()->new EntityNotFoundException("Product not found with id"+id));
        if (product == null) {
            throw new EntityNotFoundException("Product not found with id: " + productId);
        }

        CartItem cartItem=new CartItem();
        cartItem.setCart(user.getCart());
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        if(user.getCart()==null){
            user.setCart(new Cart());
        }
        user.getCart().getCartItems().add(cartItem);

       userRepository.save(user);
    }

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
