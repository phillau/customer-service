package com.xinyunjia.customerservice.service.impl;

import com.xinyunjia.customerservice.entity.ChatMsg;
import com.xinyunjia.customerservice.enums.MsgSignFlagEnum;
import com.xinyunjia.customerservice.mapper.ChatMessageMapper;
import com.xinyunjia.customerservice.model.ChatMessage;
import com.xinyunjia.customerservice.service.ChatMessageService;
import com.xinyunjia.customerservice.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("chatMessageService")
public class ChatMessageServiceImpl implements ChatMessageService {
    @Autowired
    ChatMessageMapper chatMessageMapper;

    /**
     * 消息持久化到数据库
     * @param chatMsg
     * @return
     */
    @Override
    public String saveMsg(ChatMsg chatMsg) {
        String uuid = UUIDUtil.getUUID();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(uuid);
        chatMessage.setAcceptUserId(chatMsg.getReceiverId());
        chatMessage.setSendUserId(chatMsg.getSenderId());
        chatMessage.setCreateTime(new Date());
        chatMessage.setSignFlag(MsgSignFlagEnum.unsign.type);
        chatMessage.setMsg(chatMsg.getMsg());
        chatMessageMapper.insertSelective(chatMessage);
        return uuid;
    }

    /**
     * 批量将消息签收状态更新为已签收
     * @param msgIdList
     */
    @Override
    public void updateMsgSigned(List<String> msgIdList) {
        chatMessageMapper.batchUpdateMsgSigned(msgIdList);
    }
}
