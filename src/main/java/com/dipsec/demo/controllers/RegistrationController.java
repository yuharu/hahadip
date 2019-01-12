package com.dipsec.demo.controllers;

import com.dipsec.demo.model.entities.UserInfo;
import com.dipsec.demo.repositories.UserInfoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import javax.inject.Named;
import java.util.UUID;

@Named(value = "registrationController")
@SessionScope
@Getter
@Setter
public class RegistrationController {
    @Autowired
    private UserInfoRepository userInfoRepository;
    private String name = "123";

    public UserInfo getUserInfoById(UUID id){
        return userInfoRepository.getOne(id);
    }
}
