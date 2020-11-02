package com.dillon.restapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Table("USERS")
public class User {

    @Id
    private long id;
    private String firstName;
    private String lastName;

}
