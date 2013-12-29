/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipet.server.web.ui.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author yneos
 */
@Component
public class MessageConverter implements Converter<String, Message> {

    @Autowired
    private MessageRepository MessageRepository;

    @Override
    public Message convert(String id) {
        return MessageRepository.findMessage(Long.valueOf(id));
    }
}
