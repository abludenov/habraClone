package com.javamentor.frontend.service;

import com.javamentor.frontend.dto.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class LoginService {

    @Value("${host}")
    private String host;

    private final TokenService tokenService;
    private final RestTemplate restTemplate;

    @Autowired
    public LoginService(TokenService tokenService, RestTemplate restTemplate) {
        this.tokenService = tokenService;
        this.restTemplate = restTemplate;
    }

    /**
     * метод отправляет на сервер UserLoginDto.
     * если пользователь успешно прошел аутентификацию и авторизацию,
     * то в ответ приходит токен, который сохраняется в LocalStorage
     *
     * @param userLoginDTO
     * @return boolean успешная авторизация
     */

    /* TODO:
        расширить количество получаемых статусов от сервера
        и изменить возвращаемое значение на полученный статус,
        чтобы можно было проверять ошибки:
        пользователь не найден,
        пользователь найден, но неверный пароль,
        пользователь заблокирован/не активен*/
    public boolean login(UserLoginDTO userLoginDTO) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<UserLoginDTO> entity = new HttpEntity<>(userLoginDTO, headers);
        ResponseEntity<String> response = null;

        try {
            response = restTemplate.exchange(host + "/login", HttpMethod.POST, entity, String.class);
            tokenService.saveTokenToLocalStorage(response.getBody());
            return response.getStatusCode() == HttpStatus.OK;

        } catch (HttpClientErrorException e) {
            return false;
        }
    }
}
