package com.tj.edu.practice5.jpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsersServiceTest {

    @Autowired
    private UsersService usersService;
    @Test
    void transactionTest1() {
        usersService.put();
    }
}