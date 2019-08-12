package com.xinyunjia.customerservice.controller;

import com.xinyunjia.customerservice.memory.ChatMemoryStore;
import com.xinyunjia.customerservice.entity.Message;
import com.xinyunjia.customerservice.entity.UserInfo;
import com.xinyunjia.customerservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class loginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录页
     */
    @RequestMapping("/index")
    public String index() {
        return "login";
    }

    /**
     * 点击登录
     * @param userInfo
     * @param attr
     * @return
     */
    @RequestMapping("/login")
    public String login(UserInfo userInfo, RedirectAttributes attr) {
        boolean check = loginService.check(userInfo);
        if (check) {
            attr.addAttribute("id", userInfo.getId());
            attr.addAttribute("name", userInfo.getName());
            return "redirect:/chatting";
        } else {
            return "login";
        }
    }

    /**
     * 用于加载聊天会话和客户列表
     *
     * @param userInfo
     * @param req
     * @return
     */
    @RequestMapping("/chatting")
    public String chatting(UserInfo userInfo, HttpServletRequest req) {
        /**
         * 1、获取和所有客户的会话列表，按照时间排序，并存入usersMap中
         * 2、
         */
        if (ChatMemoryStore.userMap.get(userInfo.getId()) == null) {
            ChatMemoryStore.userMap.put(userInfo.getId(), new HashMap<>());
        }
        /**
         * 保存当前登录客服
         */
        req.getSession().setAttribute("user", userInfo);
        /**
         * 保存用户会话列表
         */
        req.getSession().setAttribute("usersMap", ChatMemoryStore.userMap.get(userInfo.getId()));
        return "main";
    }
}
