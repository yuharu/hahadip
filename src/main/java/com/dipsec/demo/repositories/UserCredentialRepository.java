package com.dipsec.demo.repositories;

import com.dipsec.demo.model.entities.UserCredential;
import com.dipsec.demo.model.entities.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {
    UserCredential findByUsername(String username);
}
