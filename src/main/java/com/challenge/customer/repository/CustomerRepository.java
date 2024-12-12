package com.challenge.customer.repository;

import com.challenge.customer.domain.db.Customer;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {

    @Query("SELECT * FROM chl_customer WHERE person_id = :personId")
    Mono<Customer> findByPersonId(Long personId);
}
