package com.ec.service;

import com.ec.model.Product;
import com.ec.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);

    }

    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }

    public Optional<Product> getProduct(int id) {
        try {
           Optional<Product> p= productRepository.findById(id);
           return p;
        } catch (Exception e) {
            return null;
        }
    }
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product updateProduct(Product product){
        return productRepository.save(product);
    }


}
