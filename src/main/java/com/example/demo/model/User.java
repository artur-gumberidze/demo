package com.example.demo.model;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name dont empty")
    @Size(min = 2, max = 25, message = "Incorrect name")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "Lastname dont empty")
    @Size(min = 2, max = 25, message = "Incorrect lastname")
    private String last_name;

    @Column(name = "email")
    @Email(message = "Email invalid")
    @NotEmpty(message = "Email dont empty")
    private String email;

    public User( String name, String last_name, String email) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
    }

    public User() {

    }

}

