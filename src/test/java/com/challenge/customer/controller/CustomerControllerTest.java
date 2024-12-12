package com.challenge.customer.controller;

import com.challenge.customer.server.models.CustomerPerson;
import com.challenge.customer.server.models.CustomerPersonResponse;
import com.challenge.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private ServerWebExchange exchange;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCustomer_createsCustomerSuccessfully() {
        CustomerPerson customerPerson = new CustomerPerson();
        CustomerPersonResponse response = new CustomerPersonResponse();

        when(customerService.createCustomer(any(CustomerPerson.class))).thenReturn(Mono.just(response));

        Mono<ResponseEntity<CustomerPersonResponse>> result = customerController.createCustomer(customerPerson, exchange);

        StepVerifier.create(result)
                .assertNext(res -> {
                    assertEquals(ResponseEntity.ok(response), res);
                })
                .verifyComplete();
    }

    @Test
    void deleteCustomer_deletesCustomerSuccessfully() {
        Integer id = 1;

        when(customerService.deleteCustomer(any(Integer.class))).thenReturn(Mono.empty());

        Mono<ResponseEntity<Void>> result = customerController.deleteCustomer(id, exchange);

        StepVerifier.create(result)
                .assertNext(res -> {
                    assertEquals(ResponseEntity.noContent().<Void>build(), res);
                })
                .verifyComplete();
    }

    @Test
    void getCustomerById_returnsCustomerById() {
        Integer id = 1;
        CustomerPersonResponse response = new CustomerPersonResponse();

        when(customerService.getCustomerById(any(Integer.class))).thenReturn(Mono.just(response));

        Mono<ResponseEntity<CustomerPersonResponse>> result = customerController.getCustomerById(id, exchange);

        StepVerifier.create(result)
                .assertNext(res -> {
                    assertEquals(ResponseEntity.ok(response), res);
                })
                .verifyComplete();
    }

    @Test
    void updateCustomer_updatesCustomerSuccessfully() {
        Integer id = 1;
        CustomerPerson customerPerson = new CustomerPerson();
        CustomerPersonResponse response = new CustomerPersonResponse();

        when(customerService.updateCustomer(any(Integer.class), any(CustomerPerson.class))).thenReturn(Mono.just(response));

        Mono<ResponseEntity<CustomerPersonResponse>> result = customerController.updateCustomer(id, customerPerson, exchange);

        StepVerifier.create(result)
                .assertNext(res -> {
                    assertEquals(ResponseEntity.ok(response), res);
                })
                .verifyComplete();
    }

}