package com.myproject.reacitve.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.reacitve.config.DatabaseConfig;
import com.myproject.reacitve.domain.Beer;
import com.myproject.reacitve.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataR2dbcTest
@Import(DatabaseConfig.class)
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testCreateJson() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

        System.out.println(objectMapper.writeValueAsString(getTestCustomer()));
    }

    @Test
    void saveNewBeer() {

        customerRepository.save(getTestCustomer())
                .subscribe(customer -> System.out.println(customer.toString()));
    }

    public static Customer getTestCustomer() {

        return Customer.builder()
                .customerName("Mike")
                .build();
    }
}