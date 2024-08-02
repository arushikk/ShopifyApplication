package com.shopify.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private boolean loggedIn;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CustomerTransaction> transactions;
}

