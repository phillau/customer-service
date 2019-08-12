package com.xinyunjia.customerservice.controller;

import com.google.gson.JsonObject;
import com.xinyunjia.customerservice.entity.HistoryMessage;
import com.xinyunjia.customerservice.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

@Controller
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;

    @ResponseBody
    @RequestMapping("/history/message")
    public List<HistoryMessage> getHistoryMessage(@RequestBody Map<String,String> params){
        return chatMessageService.getHistoryMessage(params);
    }
}
