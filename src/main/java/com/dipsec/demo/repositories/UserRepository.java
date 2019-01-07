package com.dipsec.demo.repositories;

import com.dipsec.demo.model.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, String> {
}
