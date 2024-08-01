package com.shopify.dto;

import com.shopify.entities.CustomerTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private Long id;

    private String name;
    private String email;

    private boolean loggedIn;
    private List<CustomerTransaction> transactions;

    }

