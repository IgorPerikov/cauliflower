package com.github.igorperikov.cauliflower.auth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class HashingUtility {
    @Value("${auth.application.salt}")
    private String salt;

    public String hash(String unhashed) {
        String passwordWithSalt = unhashed + salt;
        return DigestUtils.md5DigestAsHex(passwordWithSalt.getBytes());
    }
}
