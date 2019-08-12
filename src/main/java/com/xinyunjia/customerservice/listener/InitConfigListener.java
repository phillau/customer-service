package com.xinyunjia.customerservice.listener;

import cn.wildfirechat.sdk.ChatConfig;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitConfigListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            try {
                System.err.println("初始化和im服务器的连接地址");
                ChatConfig.initAdmin("http://localhost:18080", "123456");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
