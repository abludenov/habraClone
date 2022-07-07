package com.javamentor.frontend.service;

import com.vaadin.flow.component.UI;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public void saveTokenToLocalStorage(String token){
        UI.getCurrent().getPage().executeJs("localStorage.setItem('token', $0)", token);
    }
}
