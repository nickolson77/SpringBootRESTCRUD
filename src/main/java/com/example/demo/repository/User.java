package com.example.demo.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// DB increment ID automatically
    private Long id;    //@Column(name = "name") - use it when db column name is different
    private String name;
    private String email;
    private LocalDate birth;
    private int age;

    //need for hibernate?
    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", age=" + age +
                '}';
    }
}
