package com.test.Test.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String address;
    private String email;
    private String firstName;
    private String lastName;


}
