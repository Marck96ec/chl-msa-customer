package com.challenge.customer.service.mapper;

import com.challenge.customer.domain.db.Customer;
import com.challenge.customer.domain.db.Person;
import com.challenge.customer.server.models.CustomerPerson;
import com.challenge.customer.server.models.CustomerPersonResponse;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static lombok.AccessLevel.PROTECTED;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    Person toPersonCreate(CustomerPerson customerPerson);

    Customer toCustomerCreate(Person person, CustomerPerson customerPerson);

    @Mapping(source = "customer.customer_id", target = "customer_id")
    @Mapping(source = "person.person_id", target = "person_id")
    @Mapping(source = "customerPerson.password", target = "password")
    @Mapping(source = "customerPerson.status", target = "status")
    Customer toCustomerUpdate(Person person, CustomerPerson customerPerson, Customer customer);

    @Mapping(source = "person.person_id", target = "personId")
    @Mapping(source = "person.name", target = "name")
    @Mapping(source = "person.gender", target = "gender")
    @Mapping(source = "person.age", target = "age")
    @Mapping(source = "person.identification", target = "identification")
    @Mapping(source = "person.address", target = "address")
    @Mapping(source = "person.phone", target = "phone")
    @Mapping(source = "customer.status", target = "status")
    CustomerPersonResponse toCustomerPersonResponse(Customer customer, Person person);
}
