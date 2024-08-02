package com.shopify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopifyApplication {

    //swagger =http://localhost:8080/v3/api-docs
    //swagger-ui=http://localhost:8080/swagger-ui/index.html#/

    public static void main(String[] args) {
        SpringApplication.run(ShopifyApplication.class, args);
    }

}
