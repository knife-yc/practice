package com.yc.shiro.service.impl;

import com.yc.shiro.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getPasswordByName(String name) {
        return name;
    }
}
