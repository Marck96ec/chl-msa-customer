package com.challenge.customer.service.impl;

import com.challenge.customer.domain.db.Customer;
import com.challenge.customer.domain.db.Person;
import com.challenge.customer.repository.CustomerRepository;
import com.challenge.customer.repository.PersonRepository;
import com.challenge.customer.server.models.CustomerPerson;
import com.challenge.customer.server.models.CustomerPersonResponse;
import com.challenge.customer.service.mapper.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {


    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCustomer_createsCustomerSuccessfully() {
        CustomerPerson customerPerson = new CustomerPerson();
        Person person = new Person();
        Customer customer = new Customer();
        CustomerPersonResponse response = new CustomerPersonResponse();

        when(customerMapper.toPersonCreate(any(CustomerPerson.class))).thenReturn(person);
        when(personRepository.save(any(Person.class))).thenReturn(Mono.just(person));
        when(customerMapper.toCustomerCreate(any(Person.class), any(CustomerPerson.class))).thenReturn(customer);
        when(customerRepository.save(any(Customer.class))).thenReturn(Mono.just(customer));
        when(customerMapper.toCustomerPersonResponse(any(Customer.class), any(Person.class))).thenReturn(response);

        Mono<CustomerPersonResponse> result = customerServiceImpl.createCustomer(customerPerson);

        StepVerifier.create(result)
                .assertNext(res -> {
                    assertNotNull(res);
                    assertEquals(response, res);
                })
                .verifyComplete();
    }

    @Test
    void createCustomer_handlesPersonRepositorySaveError() {
        CustomerPerson customerPerson = new CustomerPerson();
        Person person = new Person();

        when(customerMapper.toPersonCreate(any(CustomerPerson.class))).thenReturn(person);
        when(personRepository.save(any(Person.class))).thenReturn(Mono.error(new RuntimeException("Save error")));

        Mono<CustomerPersonResponse> result = customerServiceImpl.createCustomer(customerPerson);

        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void createCustomer_handlesCustomerRepositorySaveError() {
        CustomerPerson customerPerson = new CustomerPerson();
        Person person = new Person();
        Customer customer = new Customer();

        when(customerMapper.toPersonCreate(any(CustomerPerson.class))).thenReturn(person);
        when(personRepository.save(any(Person.class))).thenReturn(Mono.just(person));
        when(customerMapper.toCustomerCreate(any(Person.class), any(CustomerPerson.class))).thenReturn(customer);
        when(customerRepository.save(any(Customer.class))).thenReturn(Mono.error(new RuntimeException("Save error")));

        Mono<CustomerPersonResponse> result = customerServiceImpl.createCustomer(customerPerson);

        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void updateCustomer_updatesCustomerSuccessfully() {
        Integer id = 1;
        CustomerPerson customerPerson = new CustomerPerson();
        Person person = new Person();
        person.setPerson_id(1L);
        Person updatedPerson = new Person();
        updatedPerson.setPerson_id(1L);
        Customer customer = new Customer();
        Customer updatedCustomer = new Customer();
        CustomerPersonResponse response = new CustomerPersonResponse();

        when(personRepository.findById(any(Long.class))).thenReturn(Mono.just(person));
        when(customerMapper.toPersonCreate(any(CustomerPerson.class))).thenReturn(updatedPerson);
        when(personRepository.save(any(Person.class))).thenReturn(Mono.just(updatedPerson));
        when(customerRepository.findByPersonId(any(Long.class))).thenReturn(Mono.just(customer));
        when(customerMapper.toCustomerUpdate(any(Person.class), any(CustomerPerson.class), any(Customer.class))).thenReturn(updatedCustomer);
        when(customerRepository.save(any(Customer.class))).thenReturn(Mono.just(updatedCustomer));
        when(customerMapper.toCustomerPersonResponse(any(Customer.class), any(Person.class))).thenReturn(response);

        Mono<CustomerPersonResponse> result = customerServiceImpl.updateCustomer(id, customerPerson);

        StepVerifier.create(result)
                .assertNext(res -> {
                    assertNotNull(res);
                    assertEquals(response, res);
                })
                .verifyComplete();
    }


    @Test
    void updateCustomer_handlesPersonRepositorySaveError() {
        Integer id = 1;
        CustomerPerson customerPerson = new CustomerPerson();
        Person person = new Person();
        person.setPerson_id(1L);
        Person updatedPerson = new Person();
        updatedPerson.setPerson_id(1L);

        when(personRepository.findById(any(Long.class))).thenReturn(Mono.just(person));
        when(customerMapper.toPersonCreate(any(CustomerPerson.class))).thenReturn(updatedPerson);
        when(personRepository.save(any(Person.class))).thenReturn(Mono.error(new RuntimeException("Save error")));

        Mono<CustomerPersonResponse> result = customerServiceImpl.updateCustomer(id, customerPerson);

        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void updateCustomer_handlesCustomerRepositorySaveError() {
        Integer id = 1;
        CustomerPerson customerPerson = new CustomerPerson();
        Person person = new Person();
        person.setPerson_id(1L);
        Person updatedPerson = new Person();
        updatedPerson.setPerson_id(1L);
        Customer customer = new Customer();
        Customer updatedCustomer = new Customer();

        when(personRepository.findById(any(Long.class))).thenReturn(Mono.just(person));
        when(customerMapper.toPersonCreate(any(CustomerPerson.class))).thenReturn(updatedPerson);
        when(personRepository.save(any(Person.class))).thenReturn(Mono.just(updatedPerson));
        when(customerRepository.findByPersonId(any(Long.class))).thenReturn(Mono.just(customer));
        when(customerMapper.toCustomerUpdate(any(Person.class), any(CustomerPerson.class), any(Customer.class))).thenReturn(updatedCustomer);
        when(customerRepository.save(any(Customer.class))).thenReturn(Mono.error(new RuntimeException("Save error")));

        Mono<CustomerPersonResponse> result = customerServiceImpl.updateCustomer(id, customerPerson);

        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void getAllCustomers_returnsAllCustomersSuccessfully() {
        Person person1 = new Person();
        person1.setPerson_id(1L);
        Person person2 = new Person();
        person2.setPerson_id(2L);
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        CustomerPersonResponse response1 = new CustomerPersonResponse();
        CustomerPersonResponse response2 = new CustomerPersonResponse();

        when(personRepository.findAll()).thenReturn(Flux.just(person1, person2));
        when(customerRepository.findByPersonId(1L)).thenReturn(Mono.just(customer1));
        when(customerRepository.findByPersonId(2L)).thenReturn(Mono.just(customer2));
        when(customerMapper.toCustomerPersonResponse(customer1, person1)).thenReturn(response1);
        when(customerMapper.toCustomerPersonResponse(customer2, person2)).thenReturn(response2);

        Flux<CustomerPersonResponse> result = customerServiceImpl.getAllCustomers();

        StepVerifier.create(result)
                .expectNext(response1)
                .expectNext(response2)
                .verifyComplete();
    }

    @Test
    void getAllCustomers_handlesEmptyPersonRepository() {
        when(personRepository.findAll()).thenReturn(Flux.empty());

        Flux<CustomerPersonResponse> result = customerServiceImpl.getAllCustomers();

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void getAllCustomers_handlesCustomerNotFound() {
        Person person = new Person();
        person.setPerson_id(1L);

        when(personRepository.findAll()).thenReturn(Flux.just(person));
        when(customerRepository.findByPersonId(1L)).thenReturn(Mono.empty());

        Flux<CustomerPersonResponse> result = customerServiceImpl.getAllCustomers();

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void getCustomerById_returnsCustomerSuccessfully() {
        Integer id = 1;
        Person person = new Person();
        person.setPerson_id(1L);
        Customer customer = new Customer();
        CustomerPersonResponse response = new CustomerPersonResponse();

        when(personRepository.findByIdentification(any(String.class))).thenReturn(Mono.just(person));
        when(customerRepository.findByPersonId(any(Long.class))).thenReturn(Mono.just(customer));
        when(customerMapper.toCustomerPersonResponse(any(Customer.class), any(Person.class))).thenReturn(response);

        Mono<CustomerPersonResponse> result = customerServiceImpl.getCustomerById(id);

        StepVerifier.create(result)
                .assertNext(res -> {
                    assertNotNull(res);
                    assertEquals(response, res);
                })
                .verifyComplete();
    }

    @Test
    void getCustomerById_handlesPersonNotFound() {
        Integer id = 1;

        when(personRepository.findByIdentification(any(String.class))).thenReturn(Mono.empty());

        Mono<CustomerPersonResponse> result = customerServiceImpl.getCustomerById(id);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void getCustomerById_handlesCustomerNotFound() {
        Integer id = 1;
        Person person = new Person();
        person.setPerson_id(1L);

        when(personRepository.findByIdentification(any(String.class))).thenReturn(Mono.just(person));
        when(customerRepository.findByPersonId(any(Long.class))).thenReturn(Mono.empty());

        Mono<CustomerPersonResponse> result = customerServiceImpl.getCustomerById(id);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void deleteCustomer_deletesCustomerSuccessfully() {
        Integer id = 1;
        Person person = new Person();
        person.setPerson_id(1L);
        Customer customer = new Customer();

        when(personRepository.findById(any(Long.class))).thenReturn(Mono.just(person));
        when(customerRepository.findByPersonId(any(Long.class))).thenReturn(Mono.just(customer));
        when(customerRepository.delete(any(Customer.class))).thenReturn(Mono.empty());
        when(personRepository.delete(any(Person.class))).thenReturn(Mono.empty());

        Mono<Void> result = customerServiceImpl.deleteCustomer(id);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void deleteCustomer_handlesPersonNotFound() {
        Integer id = 1;

        when(personRepository.findById(any(Long.class))).thenReturn(Mono.empty());

        Mono<Void> result = customerServiceImpl.deleteCustomer(id);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void deleteCustomer_handlesCustomerNotFound() {
        Integer id = 1;
        Person person = new Person();
        person.setPerson_id(1L);

        when(personRepository.findById(any(Long.class))).thenReturn(Mono.just(person));
        when(customerRepository.findByPersonId(any(Long.class))).thenReturn(Mono.empty());

        Mono<Void> result = customerServiceImpl.deleteCustomer(id);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void deleteCustomer_handlesCustomerRepositoryDeleteError() {
        Integer id = 1;
        Person person = new Person();
        person.setPerson_id(1L);
        Customer customer = new Customer();

        when(personRepository.findById(any(Long.class))).thenReturn(Mono.just(person));
        when(customerRepository.findByPersonId(any(Long.class))).thenReturn(Mono.just(customer));
        when(customerRepository.delete(any(Customer.class))).thenReturn(Mono.error(new RuntimeException("Delete error")));

        Mono<Void> result = customerServiceImpl.deleteCustomer(id);

        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void deleteCustomer_handlesPersonRepositoryDeleteError() {
        Integer id = 1;
        Person person = new Person();
        person.setPerson_id(1L);
        Customer customer = new Customer();

        when(personRepository.findById(any(Long.class))).thenReturn(Mono.just(person));
        when(customerRepository.findByPersonId(any(Long.class))).thenReturn(Mono.just(customer));
        when(customerRepository.delete(any(Customer.class))).thenReturn(Mono.empty());
        when(personRepository.delete(any(Person.class))).thenReturn(Mono.error(new RuntimeException("Delete error")));

        Mono<Void> result = customerServiceImpl.deleteCustomer(id);

        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
    }

}