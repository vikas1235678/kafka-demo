package com.example.kafka_demo.controller;

import com.example.kafka_demo.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final AccountService accountService;

    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    public String login(HttpServletRequest httpServletRequest) {
        final String username = httpServletRequest.getParameter("username");
        final String password = httpServletRequest.getParameter("password");
        try {
            final var userAccount = accountService.auth(username, password);
            if (userAccount == null) {
                return "login";
            } else {
                return "main";
            }
        } catch (Exception e) {
            return "5xx";
        }

    }
}
