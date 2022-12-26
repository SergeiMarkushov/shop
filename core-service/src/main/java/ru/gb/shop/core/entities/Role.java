package ru.gb.shop.core.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "roles", schema = "winter_shop")
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name_role")
    private String name;


}
