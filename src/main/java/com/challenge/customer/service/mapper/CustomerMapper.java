package com.challenge.customer.service.mapper;

import com.challenge.customer.domain.db.Customer;
import com.challenge.customer.domain.db.Person;
import com.challenge.customer.server.models.CustomerPerson;
import com.challenge.customer.server.models.CustomerPersonResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    Person toPersonCreate(CustomerPerson customerPerson);

    Customer toCustomerCreate(Person person, CustomerPerson customerPerson);

    CustomerPersonResponse toCustomerPersonResponse(Customer customer, Person person);
}
