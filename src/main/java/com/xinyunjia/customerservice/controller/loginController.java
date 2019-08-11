package com.xinyunjia.customerservice.controller;

import com.xinyunjia.customerservice.memory.ChatMemoryStore;
import com.xinyunjia.customerservice.entity.Message;
import com.xinyunjia.customerservice.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class loginController {
	/**
	 * 保存客户列表
	 */
	@RequestMapping("/index")
	public String index(){
		return "login";
	}

	@RequestMapping("/login")
	public String login(UserInfo user,HttpServletRequest req){
		/**
		 * 1、获取和所有客户的会话列表，按照时间排序，并存入usersMap中
		 * 2、
//		 */
//		Map<String, UserInfo> map = new HashMap<>();
//		UserInfo userInfo = new UserInfo();
//		userInfo.setId(1);
//		userInfo.setName("kehu1");
//		userInfo.setLoginTime(System.currentTimeMillis()+"");
//		ArrayList<Message> list = new ArrayList<>();
//		Message message = new Message();
//		message.setFromName("fromName1");
//		message.setToName("toName1");
//		message.setMessageId(1);
//		message.setMessageText("这是一条消息");
//		list.add(message);
//		userInfo.setMegs(list);
//		map.put(userInfo.getName(),userInfo);
////		ChatMemoryStore.userMap.put(user.getName(),map);
//
//		userInfo = new UserInfo();
//		userInfo.setId(2);
//		userInfo.setName("kehu2");
//		userInfo.setLoginTime(System.currentTimeMillis()+"");
//		list = new ArrayList<>();
//		message = new Message();
//		message.setFromName("fromName2");
//		message.setToName("toName2");
//		message.setMessageId(2);
//		message.setMessageText("这是一条消息");
//		list.add(message);
//		userInfo.setMegs(list);
//		map.put(userInfo.getName(),userInfo);
//		ChatMemoryStore.userMap.put(user.getName(),map);

		user.setId("customer_service");
		if(ChatMemoryStore.userMap.get(user.getId())==null){
			ChatMemoryStore.userMap.put(user.getId(),new HashMap<>());
		}
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("usersMap", ChatMemoryStore.userMap.get(user.getId()));
		return "main";
	}
}
