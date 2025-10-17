package com.example.kafka_demo.service;

import com.example.kafka_demo.dao.AccountDao;
import com.example.kafka_demo.model.UserAccount;

public class AccountService {

    private final AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public UserAccount auth(String username, String password) {
        return accountDao.findUserAccount(username, password);
    }

}
