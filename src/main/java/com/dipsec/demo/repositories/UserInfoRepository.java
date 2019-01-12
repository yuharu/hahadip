package com.dipsec.demo.repositories;

import com.dipsec.demo.model.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {

}
