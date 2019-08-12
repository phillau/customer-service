package com.xinyunjia.customerservice.service;

import com.xinyunjia.customerservice.entity.ChatMsg;
import com.xinyunjia.customerservice.entity.HistoryMessage;

import java.util.List;
import java.util.Map;

public interface ChatMessageService {
    String saveMsg(ChatMsg chatMsg);

    void updateMsgSigned(List<String> msgIdList);

    List<HistoryMessage> getHistoryMessage(Map<String,String> customerId);
}
