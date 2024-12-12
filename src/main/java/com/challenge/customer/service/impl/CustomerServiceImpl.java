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
import reactor.core.publisher.Flux;
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
                    return customerRepository.save(customer)
                            .map(c -> customerMapper.toCustomerPersonResponse(c, p));
                });
    }

    @Override
    public Mono<CustomerPersonResponse> updateCustomer(Integer id, CustomerPerson customerPerson) {
        return personRepository.findById(Long.valueOf(id))
                .flatMap(p -> {
                    Person updatedPerson = customerMapper.toPersonCreate(customerPerson);
                    updatedPerson.setPerson_id(p.getPerson_id());
                    return personRepository.save(updatedPerson)
                            .flatMap(savedPerson -> customerRepository.findByPersonId(p.getPerson_id())
                                    .flatMap(c -> {
                                        Customer customer = customerMapper.toCustomerUpdate(savedPerson, customerPerson, c);
                                        return customerRepository.save(customer)
                                                .map(savedCustomer -> customerMapper.toCustomerPersonResponse(savedCustomer, savedPerson));
                                    }));
                });
    }

    @Override
    public Flux<CustomerPersonResponse> getAllCustomers() {
        return personRepository.findAll()
                .flatMap(person -> customerRepository.findByPersonId(person.getPerson_id())
                        .map(customer -> customerMapper.toCustomerPersonResponse(customer, person)));
    }

    @Override
    public Mono<CustomerPersonResponse> getCustomerById(Integer id) {
        return personRepository.findById(Long.valueOf(id))
                .flatMap(person -> customerRepository.findByPersonId(person.getPerson_id())
                        .map(customer -> customerMapper.toCustomerPersonResponse(customer, person)));
    }

    @Override
    public Mono<Void> deleteCustomer(Integer id) {
        return personRepository.findById(Long.valueOf(id))
                .flatMap(person -> customerRepository.findByPersonId(person.getPerson_id())
                        .flatMap(customer -> customerRepository.delete(customer)
                                .then(personRepository.delete(person))
                        )
                );
    }


}
