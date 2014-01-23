/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.server.app;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author xiaojinghai
 */
//@Component
public class StartEventHandler implements ApplicationListener<ContextStartedEvent> {

    @Override
    public void onApplicationEvent(ContextStartedEvent e) {
        System.out.println("ContextStartedEvent Received");
        System.out.println("ContextStartedEvent Received");
        System.out.println("ContextStartedEvent Received");
        System.out.println("ContextStartedEvent Received");
        System.out.println("ContextStartedEvent Received");
        System.out.println("ContextStartedEvent Received");
        System.out.println("ContextStartedEvent Received");
        System.out.println("ContextStartedEvent Received");
        System.out.println("ContextStartedEvent Received");
        System.out.println("ContextStartedEvent Received");
    }

}
