package com.anta.currency_for_employer.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role_table")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
}
