package com.xinyunjia.customerservice.mapper;

import com.xinyunjia.customerservice.model.Users;

public interface UsersMapper {
    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users selectByUsername(String name);
}