package com.challenge.customer.domain.db;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("chl_person")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {

    @Id
    private Long person_id;

    private String name;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phone;
}
