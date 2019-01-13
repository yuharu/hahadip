package com.dipsec.demo.controllers;

import com.dipsec.demo.model.entities.UserCredential;
import com.dipsec.demo.repositories.UserCredentialRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.util.UUID;

@Named(value = "registrationController")
@SessionScope
@Getter
@Setter
public class RegistrationController {
    @Autowired
    private UserCredentialRepository userRepository;

    private UserCredential user;

    public String forwardToRegister() {
        return "registration";
    }

    public void saveNewUser() {
        //userRepository.save(user);
        System.out.printf("123");
    }

    public void validatePassword(ComponentSystemEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();

        UIComponent components = event.getComponent();

        UIInput uiInputPassword = (UIInput) components.findComponent("password");
        String password = uiInputPassword.getLocalValue() == null ? ""
                : uiInputPassword.getLocalValue().toString();
        String passwordId = uiInputPassword.getClientId();

        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();

        if (password.isEmpty()) {
            return;
        }

        if (!password.equals(confirmPassword)) {
            FacesMessage msg = new FacesMessage("Пароли должны совпадать");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(passwordId, msg);
            fc.renderResponse();

            return;
        }
    }
}
