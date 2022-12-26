package ru.gb.shop.core.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
//@Table(name = "users", schema = "winter_shop")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = ("username"))
    private String username;

    @Column(name = ("password"))
    private String password;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;


}
