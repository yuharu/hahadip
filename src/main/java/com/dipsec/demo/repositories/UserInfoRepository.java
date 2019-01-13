package com.dipsec.demo.repositories;

import com.dipsec.demo.model.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
}
