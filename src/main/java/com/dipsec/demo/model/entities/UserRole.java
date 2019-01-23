package com.dipsec.demo.model.entities;

import com.dipsec.demo.model.entities.enums.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<UserCredential> users;
}
