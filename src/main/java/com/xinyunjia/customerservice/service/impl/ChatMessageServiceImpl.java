package com.xinyunjia.customerservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinyunjia.customerservice.entity.ChatMsg;
import com.xinyunjia.customerservice.entity.HistoryMessage;
import com.xinyunjia.customerservice.enums.MsgSignFlagEnum;
import com.xinyunjia.customerservice.mapper.ChatMessageMapper;
import com.xinyunjia.customerservice.model.ChatMessage;
import com.xinyunjia.customerservice.service.ChatMessageService;
import com.xinyunjia.customerservice.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("chatMessageService")
public class ChatMessageServiceImpl implements ChatMessageService {
    @Autowired
    ChatMessageMapper chatMessageMapper;

    /**
     * 消息持久化到数据库
     *
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
     *
     * @param msgIdList
     */
    @Override
    public void updateMsgSigned(List<String> msgIdList) {
        chatMessageMapper.batchUpdateMsgSigned(msgIdList);
    }

    /**
     * 根据客户id得到历史聊天记录
     * @param params
     * @return
     */
    @Override
    public List<HistoryMessage> getHistoryMessage(Map<String, String> params) {
        PageInfo<ChatMessage> chatMessagePageInfo = PageHelper.startPage(Integer.parseInt(params.get("pageNum")), Integer.parseInt(params.get("pageSize"))).doSelectPageInfo(() -> {
            chatMessageMapper.selectByCustomerId(params.get("customerId"));
        });
        List<HistoryMessage> historyMessageList = new ArrayList<>();
        chatMessagePageInfo.getList().forEach(
            chatMessage -> {
                HistoryMessage historyMessage = new HistoryMessage();
                historyMessage.setSend(chatMessage.getSendUserId());
                historyMessage.setMsg(chatMessage.getMsg());
                historyMessage.setLoc(chatMessage.getSendUserId().contains("customer_service_")?"r":"l");
                historyMessageList.add(historyMessage);
            }
        );
        return historyMessageList;
    }
}
