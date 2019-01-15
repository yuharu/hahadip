package com.dipsec.demo.model.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String code;

    @Column
    private String name;
}
