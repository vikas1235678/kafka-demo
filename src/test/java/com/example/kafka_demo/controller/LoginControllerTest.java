package com.example.kafka_demo.controller;

import com.example.kafka_demo.dao.AccountDao;
import com.example.kafka_demo.model.UserAccount;
import com.example.kafka_demo.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private AccountDao accountDao;

    private LoginController loginController;

    @BeforeEach
    void setup() {
        AccountService accountService = new AccountService(accountDao);
        this.loginController =new LoginController(accountService);
    }

    @DisplayName("User account is valid")
    @Test
    void testLoginAuthSuccess() {
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("1234");
        UserAccount userAccount = new UserAccount();
        when(accountDao.findUserAccount("admin", "1234")).thenReturn(userAccount);
        final var result = loginController.login(request);
        MatcherAssert.assertThat(result, CoreMatchers.is(CoreMatchers.equalTo("main")));
        Assertions.assertEquals("main", result);
    }

    @DisplayName("user account information is invalid")
    @Test
    void testLoginAuthFailure() {
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("1234");
        when(accountDao.findUserAccount("admin", "1234")).thenReturn(null);
        final var result = loginController.login(request);
        MatcherAssert.assertThat(result, CoreMatchers.is(CoreMatchers.equalTo("login")));
    }

    @DisplayName("database is crashed")
    @Test
    void testLoginAuthErrorDueToDatabaseCrashed() {
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("1234");
        UserAccount userAccount = new UserAccount();
        when(accountDao.findUserAccount(anyString(), anyString())).thenThrow(RuntimeException.class);
        final var result = loginController.login(request);
        MatcherAssert.assertThat(result, CoreMatchers.is(CoreMatchers.equalTo("5xx")));
    }
}
