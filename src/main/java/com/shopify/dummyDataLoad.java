package com.shopify;

import com.shopify.entities.Customer;
import com.shopify.entities.CustomerTransaction;
import com.shopify.repositories.CustomerRepository;
import com.shopify.repositories.CustomerTransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

@Configuration
public class dummyDataLoad {

    @Bean
    CommandLineRunner initDatabase(CustomerRepository customerRepository, CustomerTransactionRepository transactionRepository) {
        return args -> {
            Customer customer1 = new Customer();
            customer1.setName("Arushi kri");
            customer1.setEmail("arushi@gmail.com");
            customer1.setPassword("password");
            customerRepository.save(customer1);


            Customer customer2 = new Customer();
            customer2.setName("Ayush k");
            customer2.setEmail("ayush@gmail.com");
            customer2.setPassword("password");
            customerRepository.save(customer2);



            Customer customer3 = new Customer();
            customer3.setName("gaurav solanki");
            customer3.setEmail("gaurav@gmail.com");
            customer3.setPassword("password");
            customerRepository.save(customer3);

            Customer customer4 = new Customer();
            customer4.setName("raya shil");
            customer4.setEmail("raya@gmail.com");
            customer4.setPassword("password");
            customerRepository.save(customer4);

            CustomerTransaction transaction1 = new CustomerTransaction();
            transaction1.setCustomer(customer1);
            transaction1.setSpentDetails("Purchase 1");
            transaction1.setAmount(120.0);
            transaction1.setDate(Date.from(LocalDate.of(2024, Month.JANUARY, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            transactionRepository.save(transaction1);

            CustomerTransaction transaction2 = new CustomerTransaction();
            transaction2.setCustomer(customer1);
            transaction2.setSpentDetails("Purchase 2");
            transaction2.setAmount(75.0);
            transaction2.setDate(Date.from(LocalDate.of(2024, Month.FEBRUARY, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            transactionRepository.save(transaction2);

            CustomerTransaction transaction3 = new CustomerTransaction();
            transaction3.setCustomer(customer1);
            transaction3.setSpentDetails("Purchase 3");
            transaction3.setAmount(200.0);
            transaction3.setDate(Date.from(LocalDate.of(2024, Month.MARCH, 20).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            transactionRepository.save(transaction3);


            CustomerTransaction transaction4 = new CustomerTransaction();
            transaction4.setCustomer(customer2);
            transaction4.setSpentDetails("Purchase 4");
            transaction4.setAmount(20.0);
            transaction4.setDate(Date.from(LocalDate.of(2024, Month.JANUARY, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            transactionRepository.save(transaction4);

            CustomerTransaction transaction5 = new CustomerTransaction();
            transaction5.setCustomer(customer2);
            transaction5.setSpentDetails("Purchase 5");
            transaction5.setAmount(750.0);
            transaction5.setDate(Date.from(LocalDate.of(2024, Month.FEBRUARY, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            transactionRepository.save(transaction5);

            CustomerTransaction transaction6 = new CustomerTransaction();
            transaction6.setCustomer(customer2);
            transaction6.setSpentDetails("Purchase 6");
            transaction6.setAmount(20.0);
            transaction6.setDate(Date.from(LocalDate.of(2024, Month.MARCH, 20).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            transactionRepository.save(transaction6);

            CustomerTransaction transaction7 = new CustomerTransaction();
            transaction7.setCustomer(customer3);
            transaction7.setSpentDetails("Purchase 7");
            transaction7.setAmount(320.0);
            transaction7.setDate(Date.from(LocalDate.of(2024, Month.JANUARY, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            transactionRepository.save(transaction7);

            CustomerTransaction transaction8 = new CustomerTransaction();
            transaction8.setCustomer(customer3);
            transaction8.setSpentDetails("Purchase 8");
            transaction8.setAmount(95.0);
            transaction8.setDate(Date.from(LocalDate.of(2024, Month.FEBRUARY, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            transactionRepository.save(transaction8);

            CustomerTransaction transaction9 = new CustomerTransaction();
            transaction9.setCustomer(customer3);
            transaction9.setSpentDetails("Purchase 9");
            transaction9.setAmount(99.0);
            transaction9.setDate(Date.from(LocalDate.of(2024, Month.MARCH, 20).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            transactionRepository.save(transaction9);

            CustomerTransaction transaction10 = new CustomerTransaction();
            transaction10.setCustomer(customer4);
            transaction10.setSpentDetails("Purchase 10");
            transaction10.setAmount(60.0);
            transaction10.setDate(Date.from(LocalDate.of(2024, Month.JANUARY, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            transactionRepository.save(transaction10);

            CustomerTransaction transaction11 = new CustomerTransaction();
            transaction11.setCustomer(customer4);
            transaction11.setSpentDetails("Purchase 11");
            transaction11.setAmount(45.0);
            transaction11.setDate(Date.from(LocalDate.of(2024, Month.FEBRUARY, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            transactionRepository.save(transaction11);

            CustomerTransaction transaction12 = new CustomerTransaction();
            transaction12.setCustomer(customer4);
            transaction12.setSpentDetails("Purchase 12");
            transaction12.setAmount(80.0);
            transaction12.setDate(Date.from(LocalDate.of(2024, Month.MARCH, 20).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            transactionRepository.save(transaction12);


        };
    }
}
