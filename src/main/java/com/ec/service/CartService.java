package com.ec.service;

import com.ec.model.Cart;
import com.ec.model.CartItem;
import com.ec.model.Product;
import com.ec.model.User;
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
        Optional<User> user= Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id)));

        Optional<Product> product=productService.getProduct(productId);
        if (product == null) {
            throw new EntityNotFoundException("Product not found with id: " + productId);
        }

        CartItem cartItem=new CartItem();
        cartItem.setCart(user.get().getCart());
        cartItem.setProduct(user.get().getCart().getCartItems().get(id).getProduct());     // might be a wrong system to get this
        cartItem.setQuantity(quantity);


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
