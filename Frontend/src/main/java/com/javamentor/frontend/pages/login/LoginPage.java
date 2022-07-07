package com.javamentor.frontend.pages.login;

import com.javamentor.frontend.dto.UserLoginDTO;
import com.javamentor.frontend.service.LoginService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("/login")
public class LoginPage extends VerticalLayout {

    @Autowired
    private LoginService loginService;

    public LoginPage() {
        setAlignItems(Alignment.CENTER);
        LoginForm component = new LoginForm();
        component.setI18n(createRussianI18n());
        component.addLoginListener(event -> {
            if (loginService.login(new UserLoginDTO(event.getUsername(), event.getPassword()))) {
                UI.getCurrent().getPage().setLocation("/");
            } else {
                component.setError(true);
            }
        });
        add(component);
    }

    private LoginI18n createRussianI18n() {
        final LoginI18n i18n = LoginI18n.createDefault();

        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Nome do aplicativo");
        i18n.getHeader().setDescription("Descrição do aplicativo");
        i18n.getForm().setUsername("e-mail");
        i18n.getForm().setTitle("Войдите в систему");
        i18n.getForm().setSubmit("Войти");
        i18n.getForm().setPassword("пароль");
        i18n.getForm().setForgotPassword("Забыли пароль");
        i18n.getErrorMessage().setTitle("Неверный e-mail или пароль");
        i18n.getErrorMessage()
                .setMessage("Пожалуйста повторите попытку.");
        return i18n;
    }
}
