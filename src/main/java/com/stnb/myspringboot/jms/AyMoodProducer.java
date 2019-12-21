package com.stnb.myspringboot.jms;

import com.stnb.myspringboot.domain.AyMood;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * 生产者
 * @author 11299
 */
@Service
public class AyMoodProducer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * destination:发送到队列 message:待发送的消息
     * @param destination
     * @param message
     */
    public void sendMessage(Destination destination, final String message) {
        jmsMessagingTemplate.convertAndSend(destination,message);
    }

    public void sendMessage(Destination destination, final AyMood message) {
        jmsMessagingTemplate.convertAndSend(destination,message);
    }
}
