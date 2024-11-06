package com.mapstruct.demo.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Basic
    @Column(name = "email", nullable = false, length = 250)
    private String email;

    @Basic
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    private String name;

    @Basic
    @Column(name = "surname", nullable = true, length = 50)
    private String surname;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_book",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<>();
}
