package com.challenge.customer.domain.db;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("chl_customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    private Long customer_id;

    private String password;
    private String status;
}
