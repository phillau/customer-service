package com.xinyunjia.customerservice.memory;

import com.xinyunjia.customerservice.entity.UserInfo;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatMemoryStore {
    /**
     * 用来保存客服和客服维护的会话客户的关系
     * 外层的key代表客服id
     * 内层的key代表客户id
     */
    public static final Map<String, Map<String,UserInfo>> userMap = new ConcurrentHashMap<>();
}
