package com.xinyunjia.customerservice.service.impl;

import com.xinyunjia.customerservice.entity.UserInfo;
import com.xinyunjia.customerservice.mapper.UsersMapper;
import com.xinyunjia.customerservice.model.Users;
import com.xinyunjia.customerservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public boolean check(UserInfo user) {
        Users users = usersMapper.selectByUsername(user.getName());
        if(user.getPwd().equals(users.getPassword())){
            user.setId(users.getId());
            user.setName(users.getShowname());
            return true;
        }
        return false;
    }
}
