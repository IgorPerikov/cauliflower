package com.github.igorperikov.cauliflower.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class HashingUtility {
    public String hash(String unhashed) {
        return DigestUtils.md5DigestAsHex(unhashed.getBytes());
    }
}
