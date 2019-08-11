package com.xinyunjia.customerservice.mapper;

import com.xinyunjia.customerservice.model.ChatMessage;

import java.util.List;

public interface ChatMessageMapper {
    int insert(ChatMessage record);

    int insertSelective(ChatMessage record);

    ChatMessage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ChatMessage record);

    int updateByPrimaryKey(ChatMessage record);

    void batchUpdateMsgSigned(List<String> msgIdList);
}