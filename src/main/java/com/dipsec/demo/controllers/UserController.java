package com.dipsec.demo.controllers;

import com.dipsec.demo.model.entities.User;
import com.dipsec.demo.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

import javax.inject.Named;

@Named(value = "fhdReportController")
@SessionScope
@Getter
@Setter
public class UserController {
    @Autowired
    private UserRepository userRepository;

    public User getUserInfoByUsername(String username){
        return userRepository.findOneByUsername(username);
    }
}
