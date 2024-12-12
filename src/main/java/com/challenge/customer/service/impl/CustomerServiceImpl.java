package com.challenge.customer.service.impl;

import com.challenge.customer.domain.db.Customer;
import com.challenge.customer.domain.db.Person;
import com.challenge.customer.repository.CustomerRepository;
import com.challenge.customer.repository.PersonRepository;
import com.challenge.customer.server.models.CustomerPerson;
import com.challenge.customer.server.models.CustomerPersonResponse;
import com.challenge.customer.service.CustomerService;
import com.challenge.customer.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PersonRepository personRepository;
    @Autowired
    CustomerMapper customerMapper;


    @Override
    public Mono<CustomerPersonResponse> createCustomer(CustomerPerson customerPerson) {

        Person person = customerMapper.toPersonCreate(customerPerson);
        return personRepository.save(person)
                .flatMap(p -> {
                    Customer customer = customerMapper.toCustomerCreate(p, customerPerson);
                    return customerRepository.save(customer);
                })
                .map(c -> customerMapper.toCustomerPersonResponse(c, person));

    }
}
