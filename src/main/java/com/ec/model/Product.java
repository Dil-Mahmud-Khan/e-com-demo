package com.ec.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.event.EventListener;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String productName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
