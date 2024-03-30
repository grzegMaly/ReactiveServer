package com.myproject.reacitve.controllers;

import com.myproject.reacitve.domain.Customer;
import com.myproject.reacitve.model.CustomerDTO;
import com.myproject.reacitve.repositories.CustomerRepositoryTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOAuth2Login;

@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(1)
    void testListCustomers() {

        webTestClient.mutateWith(mockOAuth2Login())
                .get().uri(CustomerController.CUSTOMER_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type", "application/json")
                .expectBody().jsonPath("$.size()").isEqualTo(3);
    }



    @Test
    @Order(2)
    void testGetCustomerById() {

        webTestClient.mutateWith(mockOAuth2Login())
                .get().uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type", "application/json")
                .expectBody(CustomerDTO.class);
    }

    @Test
    @Order(3)
    void testGetCustomerByIdBadData() {

        webTestClient.mutateWith(mockOAuth2Login())
                .get().uri(CustomerController.CUSTOMER_PATH_ID, 999)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Order(4)
    void testCreateNewCustomer() {

        webTestClient.mutateWith(mockOAuth2Login())
                .post().uri(CustomerController.CUSTOMER_PATH)
                .body(Mono.just(CustomerRepositoryTest.getTestCustomer()), CustomerDTO.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().location("http://localhost:8080/api/v2/customer/4");
    }

    @Test
    @Order(5)
    void testCreateNewCustomerBadData() {

        Customer testCustomer = CustomerRepositoryTest.getTestCustomer();
        testCustomer.setCustomerName("");

        webTestClient.mutateWith(mockOAuth2Login())
                .post().uri(CustomerController.CUSTOMER_PATH)
                .body(Mono.just(testCustomer), CustomerDTO.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @Order(6)
    void testUpdateCustomerById() {

        webTestClient.mutateWith(mockOAuth2Login())
                .put().uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .body(Mono.just(CustomerRepositoryTest.getTestCustomer()), CustomerDTO.class)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    @Order(7)
    void testUpdateCustomerByIdBadData() {

        Customer testCustomer = CustomerRepositoryTest.getTestCustomer();
        testCustomer.setCustomerName("");

        webTestClient.mutateWith(mockOAuth2Login())
                .put().uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .body(Mono.just(testCustomer), CustomerDTO.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @Order(7)
    void testUpdateCustomerByIdNotFound() {

        webTestClient.mutateWith(mockOAuth2Login())
                .put().uri(CustomerController.CUSTOMER_PATH_ID, 99)
                .body(Mono.just(CustomerRepositoryTest.getTestCustomer()), CustomerDTO.class)
                .exchange()
                .expectStatus().isNotFound();
    }


    @Test
    @Order(998)
    void testDeleteCustomerById() {

        webTestClient.mutateWith(mockOAuth2Login())
                .delete().uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    @Order(999)
    void testDeleteCustomerByIdNotFound() {

        webTestClient.mutateWith(mockOAuth2Login())
                .delete().uri(CustomerController.CUSTOMER_PATH_ID, 99)
                .exchange()
                .expectStatus().isNotFound();
    }
}