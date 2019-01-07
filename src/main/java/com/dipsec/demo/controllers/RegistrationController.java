package com.dipsec.demo.controllers;

import com.dipsec.demo.model.entities.User;
import com.dipsec.demo.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import javax.inject.Named;

@Named(value = "registrationController")
@SessionScope
@Getter
@Setter
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    public User getUserInfoByUsername(String username){
        return userRepository.findOneByUsername(username);
    }
}
