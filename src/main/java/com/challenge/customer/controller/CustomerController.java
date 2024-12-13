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

    CustomerService customerService;

    @Override
    public Mono<ResponseEntity<CustomerPersonResponse>> createCustomer(CustomerPerson customerPerson, ServerWebExchange exchange) {
        return customerService.createCustomer(customerPerson)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteCustomer(Integer id, ServerWebExchange exchange) {
        return customerService.deleteCustomer(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }

    @Override
    public Mono<ResponseEntity<Flux<CustomerPersonResponse>>> getAllCustomers(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(customerService.getAllCustomers()));
    }

    @Override
    public Mono<ResponseEntity<CustomerPersonResponse>> getCustomerById(Integer id, ServerWebExchange exchange) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()));
    }

    @Override
    public Mono<ResponseEntity<CustomerPersonResponse>> updateCustomer(Integer id, CustomerPerson customerPerson, ServerWebExchange exchange) {
        return customerService.updateCustomer(id, customerPerson)
                .map(ResponseEntity::ok);
    }
}
