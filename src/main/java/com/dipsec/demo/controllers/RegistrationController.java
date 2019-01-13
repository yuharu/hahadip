package com.dipsec.demo.controllers;

import com.dipsec.demo.model.entities.UserCredential;
import com.dipsec.demo.model.entities.UserInfo;
import com.dipsec.demo.repositories.UserCredentialRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Named(value = "registrationController")
@SessionScope
@Getter
@Setter
public class RegistrationController {
    @Autowired
    private UserCredentialRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserCredential user;

    public void init(){
        user = new UserCredential();
        user.setInfo(new UserInfo());
    }

    public String forwardToRegister() {
        return "registration";
    }

    public String forwardToLogin() {
        return "login";
    }

    public String saveNewUser() {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return forwardToLogin();
    }

    public void validatePassword(ComponentSystemEvent event) {
        UIComponent components = event.getComponent();

        UIInput uiInputLogin = (UIInput) components.findComponent("login");
        String login = uiInputLogin.getLocalValue() == null ? ""
                : uiInputLogin.getLocalValue().toString();

        UIInput uiInputPassword = (UIInput) components.findComponent("password");
        String password = uiInputPassword.getLocalValue() == null ? ""
                : uiInputPassword.getLocalValue().toString();
        String passwordId = uiInputPassword.getClientId();

        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();

        if (login.isEmpty()) {
            showPasswordErrorMessage("Пожалуйста введите логин", passwordId);
            return;
        }

        if (password.isEmpty()) {
            return;
        }

        if (password.length() < 8) {
            showPasswordErrorMessage("Длина пароля должна быть не менее 8 символов", passwordId);
            return;
        }

        Pattern pattern = Pattern.compile(".*[A-Z]");
        Matcher matcher = pattern.matcher(password);

        if (!matcher.find()) {
            showPasswordErrorMessage("Пароль должен содержать хотя бы 1 заглавную букву", passwordId);
            return;
        }

        pattern = Pattern.compile(".*[A-Z,a-z]");
        matcher = pattern.matcher(password);

        if (!matcher.find()) {
            showPasswordErrorMessage("Пароль должен содержать хотя бы 1 букву", passwordId);
            return;
        }

        pattern = Pattern.compile(".*[0-9]");
        matcher = pattern.matcher(password);

        if (!matcher.find()) {
            showPasswordErrorMessage("Пароль должен содержать хотя бы 1 цифру", passwordId);
            return;
        }

        if (confirmPassword.isEmpty()) {
            showPasswordErrorMessage("Пожалуйста введите подтверждение пароля", passwordId);
            return;
        }

        if (!password.equals(confirmPassword)) {
            showPasswordErrorMessage("Пароли должны совпадать", passwordId);
        }
    }

    private void showPasswordErrorMessage(String message, String passwordId) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(message);
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        fc.addMessage(passwordId, msg);
        fc.renderResponse();
    }
}
