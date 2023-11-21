package com.ec.model;

import lombok.Data;

@Data
public class AddToCartRequest {
    private User user;
    private Product product;
    private int quantity;

}
