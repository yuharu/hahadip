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

    @OneToOne(mappedBy = "userCredential", fetch = FetchType.LAZY)
    private UserInfo info;
}
