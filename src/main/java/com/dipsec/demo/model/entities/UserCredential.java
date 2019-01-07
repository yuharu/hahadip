package com.dipsec.demo.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_credential")
public class UserCredential implements Serializable {
    @Id
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private String salt;

    @OneToOne(mappedBy = "userCredential", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private UserInfo info;
}
