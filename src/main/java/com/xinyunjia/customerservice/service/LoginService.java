package com.xinyunjia.customerservice.service;

import com.xinyunjia.customerservice.entity.UserInfo;

public interface LoginService {
    boolean check(UserInfo user);
}
