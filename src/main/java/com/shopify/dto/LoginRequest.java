package com.shopify.dto;


import lombok.Data;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

@Data
public class LoginRequest {

    @NonNull
    private String email;

    @NonNull
    private String password;
}

