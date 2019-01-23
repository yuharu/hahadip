package com.dipsec.demo.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user_credential")
public class UserCredential implements Serializable {
    @Id
    @Column(unique = true)
    private String username;
    @Column
    private String password;

    @OneToOne(mappedBy = "userCredential", fetch = FetchType.LAZY)
    private UserInfo info;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<UserRole> roles;
}
