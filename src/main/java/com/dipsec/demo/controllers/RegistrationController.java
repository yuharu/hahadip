package com.dipsec.demo.controllers;

import com.dipsec.demo.model.entities.Position;
import com.dipsec.demo.model.entities.UserCredential;
import com.dipsec.demo.model.entities.UserInfo;
import com.dipsec.demo.repositories.PositionRepository;
import com.dipsec.demo.repositories.UserInfoRepository;
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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Named(value = "registrationController")
@SessionScope
@Getter
@Setter
public class RegistrationController {
    @Autowired
    private UserInfoRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PositionRepository positionRepository;

    private UserInfo user;

    public void init(){
        user = new UserInfo();
        user.setUserCredential(new UserCredential());
    }

    public String forwardToRegister() {
        return "registration";
    }

    public String forwardToLogin() {
        return "login";
    }

    public String saveNewUser() {
        user.getUserCredential().setPassword(passwordEncoder.encode(user.getUserCredential().getPassword()));
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

        if (password.length() < 10) {
            showPasswordErrorMessage("Длина пароля должна быть не менее 10 символов", passwordId);
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

    public List<String> completePositions(String query) {
        List<Position> positions = positionRepository.findByNameLike(query);
        return positions.stream().map(Position::getName).collect(Collectors.toList());
    }

    private void showPasswordErrorMessage(String message, String passwordId) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(message);
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        fc.addMessage(passwordId, msg);
        fc.renderResponse();
    }
}
