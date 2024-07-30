package com.shopify.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RewardPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private int month;
    private int year;
    private int points;
}

