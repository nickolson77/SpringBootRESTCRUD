package com.example.demo.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder //User.builder().name("Vlad").age(33)
@AllArgsConstructor
@RequiredArgsConstructor //generates a constructor with 1 parameter for each field that requires special handling.
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
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
//    private List<Order> orders = new ArrayList<>();

    /*need for hibernate?
    public User() {
    }*/

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
