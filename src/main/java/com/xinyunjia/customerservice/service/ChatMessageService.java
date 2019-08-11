package com.xinyunjia.customerservice.service;

import com.xinyunjia.customerservice.entity.ChatMsg;

import java.util.List;

public interface ChatMessageService {
    String saveMsg(ChatMsg chatMsg);

    void updateMsgSigned(List<String> msgIdList);
}
