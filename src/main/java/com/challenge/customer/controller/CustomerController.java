package com.challenge.customer.controller;


import com.challenge.customer.CustomersApi;
import com.challenge.customer.server.models.CustomerPerson;
import com.challenge.customer.server.models.CustomerPersonResponse;
import com.challenge.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static lombok.AccessLevel.PRIVATE;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequestMapping("/api/v1")
public class CustomerController implements CustomersApi {

    final CustomerService customerService;

    @Override
    public Mono<ResponseEntity<CustomerPersonResponse>> createCustomer(CustomerPerson customerPerson, ServerWebExchange exchange) {
        return customerService.createCustomer(customerPerson)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteCustomer(Integer id, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Flux<CustomerPersonResponse>>> getAllCustomers(ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<CustomerPersonResponse>> getCustomerById(Integer id, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<CustomerPersonResponse>> updateCustomer(Integer id, CustomerPerson customerPerson, ServerWebExchange exchange) {
        return null;
    }
}
