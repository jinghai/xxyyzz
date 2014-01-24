/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.app;

import com.ipet.server.web.rest.v1.AccountRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Spring容器启动后初始化工作(暂无)
 *
 * @author xiaojinghai
 */
@Component
public class AppStartdHandler implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(AppStartdHandler.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        //判断根容器
        //e.getApplicationContext().getDisplayName().equals("Root WebApplicationContext");
    }

}
