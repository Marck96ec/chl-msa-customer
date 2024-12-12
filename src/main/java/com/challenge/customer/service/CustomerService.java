package com.challenge.customer.service;

import com.challenge.customer.server.models.CustomerPerson;
import com.challenge.customer.server.models.CustomerPersonResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<CustomerPersonResponse> createCustomer(CustomerPerson customerPerson);

    Mono<CustomerPersonResponse> updateCustomer(Integer id, CustomerPerson customerPerson);

    Flux<CustomerPersonResponse> getAllCustomers();

    Mono<CustomerPersonResponse> getCustomerById(Integer id);

    Mono<Void> deleteCustomer(Integer id);
}
