package com.example.kafka_demo.dao;

import com.example.kafka_demo.model.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class AccountDao {
    public UserAccount findUserAccount(String username, String password) {
        throw new UnsupportedOperationException();
    }
}
