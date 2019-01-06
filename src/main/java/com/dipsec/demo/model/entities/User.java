package com.dipsec.demo.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "user_info")
public class User implements Serializable {
    @Id
    @Column(unique = true)
    private String username;

    @Column
    private String name;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String email;

    @Column
    private String position;
}
